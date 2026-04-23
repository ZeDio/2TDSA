FROM alpine:latest

RUN apk add neovim ruby

RUN adduser -h /home/appuser -s /bin/sh -D appuser

RUN gem install webrick

RUN mkdir /app

USER appuser

WORKDIR /app

COPY . .

ENV TURMA=2TDS

EXPOSE 3000

CMD ["ruby", "server.rb"]