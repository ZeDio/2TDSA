package com.rest_client.aplicacao.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class feignConfig {
    /*
    Configurar o nivel de Log do faign
    Niveis de Log:
    - NOME - Sem logs (padrão)
    - BASIC - Apenas metodos, URLS e status
    - HEADERS - BASIC + cabeçalho
    - FULL - HEADERS + corpo da requisição e resposta
    */

    @Bean
    public Logger.Level feignLoggerLevel(){
        return feign.Logger.Level.FULL;
    }
    /*
    Interceptador para adicionar cabçalho como padrão em todas as requisições
    Útil para adicionar tokens de autenticação, headers personalizador, etc...
    */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            //Adiciona um header personalizado em todas as requisições
                requestTemplate.header("User-agent","SpringBoot-Feign-Client");
                //Se precisar de autenticação:
                //RequestTemplate.header("Authorization","Bearer"+token);
        };
    }
}
