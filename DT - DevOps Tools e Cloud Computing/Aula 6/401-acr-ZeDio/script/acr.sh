RG_NAME=rg-rm562341-acr
LOCATION=eastus

az group create --name $RG_NAME --location $LOCATION

# az provider register --namespace Microsoft.ContainerRegistry

az acr create \
    --resource-group $RG_NAME \
    --name 2tdsarm562341 \
    --sku Basic \
    --location $LOCATION \
    --public-network-enabled true \
    --admin-enabled true
