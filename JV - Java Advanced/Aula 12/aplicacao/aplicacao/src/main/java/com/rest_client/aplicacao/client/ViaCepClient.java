package com.rest_client.aplicacao.client;

import com.rest_client.aplicacao.config.FeignConfig;
import com.rest_client.aplicacao.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
Interface que definir o cliente REST usando Feign
O Feign cria automaticamente a Implementação desta interface
*/
@FeignClient(
        name = "viacep", //Nome do Cliente
        url = "https://viacep.com.br/ws",
        configuration = FeignCongfig.class
)
public interface ViaCepClient {
    /*
    Metodo que faz uma requisição GET para consultar um CEP
    */

    @GetMapping("/(cep)/json/")
    EnderecoDTO buscarEnderecoPorCep(@PathVariable("cep")String cep);
}
