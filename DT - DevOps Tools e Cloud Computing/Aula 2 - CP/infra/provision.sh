#!/bin/bash

# --- 1. Configuração de Caminhos Relativos ---
# Pega o caminho absoluto de onde o script está e sobe um nível para a raiz do projeto
BASE_DIR=$(dirname "$(dirname "$(readlink -f "$0")")")
JAR_FILE="$BASE_DIR/app/app.jar"
OUTPUT_FILE="$BASE_DIR/outputs.json"

# --- Variáveis de Configuração Azure ---
RG_NAME="rg-dimdim-java-prod"
LOCATION="centralus"
VM_NAME="vm-dimdim"
IMAGE="Ubuntu2404"
SIZE="Standard_D2_v4"
ADMIN_USER="admlnx"
ADMIN_PASS="Fiap@2tdsvms"
VNET_NAME="vnet-dimdim"
SUBNET_NAME="snet-app"
NSG_NAME="nsg-dimdim"
PIP_NAME="pip-dimdim"
APP_PORT="8080"

# Cores para output
BLUE='\033[0;34m'
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m'

# Validação: O arquivo JAR existe na pasta /app?
if [ ! -f "$JAR_FILE" ]; then
    echo -e "${RED}Erro: Arquivo app.jar não encontrado em $JAR_FILE${NC}"
    exit 1
fi

echo -e "${BLUE}=== Iniciando Deploy com Estrutura de Pastas: app/app.jar ===${NC}"

# --- 2. Seleção de Assinatura ---
mapfile -t SUBS_LIST < <(az account list --query "[].{id:id, name:name}" -o tsv)
for i in "${!SUBS_LIST[@]}"; do echo "[$i] $(echo "${SUBS_LIST[$i]}" | cut -f2)"; done
read -p "Selecione o índice da assinatura: " SUB_INDEX
SELECTED_SUB=$(echo "${SUBS_LIST[$SUB_INDEX]}" | cut -f1)
az account set --subscription "$SELECTED_SUB"

# --- 3. Infraestrutura ---
az group create --name "$RG_NAME" --location "$LOCATION"

echo -e "${BLUE}Configurando Rede e Firewall...${NC}"
az network vnet create -g "$RG_NAME" -n "$VNET_NAME" --subnet-name "$SUBNET_NAME"
az network nsg create -g "$RG_NAME" -n "$NSG_NAME"

az network nsg rule create -g "$RG_NAME" --nsg-name "$NSG_NAME" -n AllowJavaPort \
    --priority 1000 --destination-port-ranges "$APP_PORT" --access Allow --protocol Tcp
az network nsg rule create -g "$RG_NAME" --nsg-name "$NSG_NAME" -n AllowSSH \
    --priority 1001 --destination-port-ranges 22 --access Allow --protocol Tcp

# --- 4. Criação da VM (Instalação automática do JRE) ---
echo -e "${BLUE}Criando VM e instalando Java...${NC}"
az vm create \
  --resource-group "$RG_NAME" \
  --name "$VM_NAME" \
  --image "$IMAGE" \
  --size "$SIZE" \
  --admin-username "$ADMIN_USER" \
  --admin-password "$ADMIN_PASS" \
  --authentication-type password \
  --vnet-name "$VNET_NAME" \
  --subnet "$SUBNET_NAME" \
  --nsg "$NSG_NAME" \
  --public-ip-address "$PIP_NAME" \
  --custom-data "#cloud-config
package_update: true
packages:
  - openjdk-17-jre-headless"

# --- 5. Upload e Execução do JAR ---
PUBLIC_IP=$(az vm show -d -g "$RG_NAME" -n "$VM_NAME" --query publicIps -o tsv)

echo -e "${BLUE}Aguardando 30s para o SSH estabilizar...${NC}"
sleep 30

# Requer sshpass instalado localmente
echo -e "${BLUE}Enviando o arquivo do repositório para a VM...${NC}"
sshpass -p "$ADMIN_PASS" scp -o StrictHostKeyChecking=no "$JAR_FILE" "$ADMIN_USER@$PUBLIC_IP:/home/$ADMIN_USER/app.jar"

echo -e "${BLUE}Iniciando a aplicação...${NC}"
sshpass -p "$ADMIN_PASS" ssh -o StrictHostKeyChecking=no "$ADMIN_USER@$PUBLIC_IP" "nohup java -jar /home/$ADMIN_USER/app.jar > /home/$ADMIN_USER/app.log 2>&1 &"

# --- 6. Conclusão e Relatório ---
echo -e "\n${GREEN}✔ Deploy realizado com sucesso!${NC}"
echo "------------------------------------------------"
echo "IP Público: $PUBLIC_IP"
echo "URL: http://$PUBLIC_IP:$APP_PORT"
echo "------------------------------------------------"

# Salva os outputs na raiz conforme solicitado
echo "{\"public_ip\": \"$PUBLIC_IP\", \"app_url\": \"http://$PUBLIC_IP:$APP_PORT\", \"vm_user\": \"$ADMIN_USER\"}" > "$OUTPUT_FILE"
echo -e "${BLUE}Arquivo outputs.json gerado na raiz do projeto.${NC}"