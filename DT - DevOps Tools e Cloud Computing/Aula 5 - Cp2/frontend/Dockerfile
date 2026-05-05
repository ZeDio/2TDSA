# Etapa 1: build da aplicação
FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

# Etapa 2: servidor web (nginx)
FROM nginx:alpine

# Remove config padrão
RUN rm -rf /usr/share/nginx/html/*

# Copia build do Vite
COPY --from=build /app/dist /usr/share/nginx/html

# REQUISITO DO RM
RUN mkdir -p /etc/cp2 && echo "562341" > /etc/cp2/rm.txt

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]