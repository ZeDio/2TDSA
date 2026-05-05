# Usa uma imagem oficial do Node
FROM node:18-alpine

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia apenas os arquivos de dependência primeiro (melhora cache)
COPY package*.json ./

# Instala as dependências
RUN npm install --production

# Copia o restante do código
COPY . .

# o arquivo RM.txt
RUN mkdir -p /etc/cp2 && echo "562341" > /etc/cp2/rm.txt

# Expõe a porta (ajuste se seu app usar outra)
EXPOSE 3000

# Comando para rodar a aplicação
CMD ["npm", "start"]