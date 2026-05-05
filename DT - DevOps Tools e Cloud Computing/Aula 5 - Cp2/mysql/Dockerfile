FROM mysql:8.0

# variáveis obrigatórias
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=dimdin
ENV MYSQL_USER=dimdin_user
ENV MYSQL_PASSWORD=dimdin_pass

# Script roda automaticamente na primeira inicialização
COPY init.sql /docker-entrypoint-initdb.d/

# requisito do RM
RUN mkdir -p /etc/cp2 && echo "562341" > /etc/cp2/rm.txt
# Porta padrão
EXPOSE 3306