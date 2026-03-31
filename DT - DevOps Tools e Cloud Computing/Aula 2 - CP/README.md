# Checkpoint 1 — Virtualização em Nuvem

## Contexto

A empresa **DimDim**, liderada por Esteves Jobs, está evoluindo sua infraestrutura após o sucesso das primeiras entregas utilizando máquinas virtuais on-premise e na nuvem.

Agora, a empresa precisa tornar sua aplicação Java acessível publicamente, utilizando infraestrutura em nuvem de forma **reprodutível, automatizada e versionada**.

Sua equipe foi contratada para projetar e implementar essa solução utilizando **Microsoft Azure**.

## Problema

A DimDim precisa disponibilizar uma aplicação Java existente em um ambiente Linux na nuvem, garantindo que:

- A infraestrutura possa ser recriada de forma automatizada
- A aplicação esteja acessível via internet
- Todo o processo seja reproduzível por terceiros
- O artefato da aplicação esteja versionado

A empresa não aceita soluções feitas manualmente via portal.

## Objetivo

Construir uma solução em nuvem que permita:

- Provisionamento completo da infraestrutura via **Azure CLI (`az`)**
- Execução de uma aplicação Java em uma máquina virtual Linux
- Acesso público à aplicação via navegador
- Reprodutibilidade total do ambiente a partir de scripts

## Saídas Esperadas

Ao final da atividade, a solução deve permitir que qualquer pessoa:

### 1. Reproduza a Infraestrutura

- Execute um script e tenha todos os recursos criados na Azure
- Os recursos seguem um padrão de nomenclatura consistente
- A VM esteja acessível remotamente após o provisionamento

### 2. Execute a Aplicação

- O artefato da aplicação (JAR) esteja versionado no repositório
- A aplicação possa ser transferida para a VM
- O ambiente esteja preparado para execução (Java instalado)
- A aplicação esteja em execução na VM

### 3. Acesse o Sistema

- A aplicação esteja acessível via navegador
- O acesso ocorra pelo IP público da VM e porta adequada
- O sistema responda corretamente

### 4. Represente a Arquitetura

- A equipe deve produzir um **diagrama da arquitetura da solução**
- O diagrama deve representar claramente os recursos utilizados e suas relações
- Deve ser possível compreender o fluxo de acesso à aplicação

## Requisitos Técnicos

A solução deve obrigatoriamente:

- Utilizar **Azure CLI (`az`)**
- Utilizar uma **máquina virtual Linux**
- Permitir acesso remoto (ex: SSH)
- Permitir acesso à aplicação via internet
- Não depender do portal Azure para criação manual de recursos

## Requisitos de Automação (OBRIGATÓRIO)

A solução deve permitir validação automatizada.

### Script principal

Arquivo obrigatório:

```bash
./infra/provision.sh
```

Requisitos:

- Executa **sem interação manual**
- Cria toda a infraestrutura necessária
- Pode ser executado do zero, em um ambiente sem recursos prévios
- Deve falhar com erro claro em caso de problema

### Premissas do ambiente de correção

Para a validação automatizada, considere que o avaliador terá:

- `az` instalado e funcional
- Sessão autenticada no Azure (`az login` já realizado)
- Permissão para criar recursos na subscription alvo
- Quota disponível na região utilizada pelo script
- Shell compatível com Bash para execução de `./infra/provision.sh`

O script será executado a partir da raiz do repositório.

---

### Saída padronizada

Ao final da execução, deve ser gerado:

```json
outputs.json
```

Contrato de saída:

- O arquivo deve estar na raiz do repositório
- Deve ser JSON válido (UTF-8), sem comentários
- Os campos abaixo são obrigatórios

Formato esperado:

```json
{
  "resource_group": "<nome>",
  "vm_name": "<nome>",
  "public_ip": "<ip>",
  "app_port": <porta>
}
```

Regras dos campos:

- `resource_group`: string não vazia
- `vm_name`: string não vazia
- `public_ip`: IPv4 público válido
- `app_port`: inteiro entre 1 e 65535

## Estrutura do Repositório

```
/
├── README.md              (NÃO ALTERAR)
├── REPORT.md              (resposta da equipe)
├── outputs.json           (gerado pelo script)
├── infra/
│   └── provision.sh
├── app/
│   └── app.jar
```

## Entrega

A entrega será feita via **GitHub Classroom**.

### REPORT.md

Deve conter:

- Identificação da equipe (nome completo + RM)
- Descrição da arquitetura
- Justificativas técnicas
- Diagrama da arquitetura (imagem ou link)
- Evidências da execução (prints ou logs)
- Informações de acesso à aplicação

## Rubrica de Avaliação (100 pontos)

- 30 pontos: Infraestrutura e automação (`infra/provision.sh` executa sem interação e cria os recursos esperados)
- 20 pontos: Execução da aplicação na VM (Java instalado, JAR transferido e processo em execução)
- 20 pontos: Acesso externo (aplicação acessível via `public_ip:app_port`)
- 15 pontos: Documentação técnica no `REPORT.md` (arquitetura, justificativas e evidências)
- 15 pontos: Qualidade da arquitetura e clareza da entrega (nomenclatura, organização e diagrama)

Critério de correção:

- Se o script principal falhar, a correção dos critérios dependentes de ambiente provisionado pode ser interrompida
- A avaliação de documentação (`REPORT.md`) e diagrama pode ser feita mesmo com falha de provisionamento

## Penalidades

- Uso do portal Azure: **-20 pontos**
- Script com interação manual obrigatória: **-20 pontos**
- Ausência ou formato inválido de `outputs.json`: **-10 pontos**
- JAR não versionado: **-20 pontos**

## Observação Final

Você não está sendo avaliado por seguir um tutorial.

Você está sendo avaliado por resolver um problema real com engenharia.

Se o script não funciona, a entrega fica tecnicamente incompleta e os critérios dependentes podem não ser validados.

Se não for possível validar automaticamente, a entrega está incorreta.
