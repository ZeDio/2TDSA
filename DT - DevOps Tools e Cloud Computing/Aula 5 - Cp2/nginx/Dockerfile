FROM nginx:alpine

# Remove config padrão
RUN rm /etc/nginx/conf.d/default.conf

# Copia sua config customizada
COPY default.conf /etc/nginx/conf.d/

# requisito do RM
RUN mkdir -p /etc/cp2 && echo "562341" > /etc/cp2/rm.txt

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]