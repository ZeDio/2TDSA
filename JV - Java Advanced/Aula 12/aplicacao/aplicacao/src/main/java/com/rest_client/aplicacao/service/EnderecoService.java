package com.rest_client.aplicacao.service;
/*
Service responsavel pela logica de negocio relacionada a endereços.
Essa camada usa o cliente Feign para buscar os dados
*/

import com.rest_client.aplicacao.client.ViaCepClient;
import com.rest_client.aplicacao.dto.EnderecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
    //Injeção de dependência do cliente Feign
    @Autowired
    private ViaCepClient viaCepClient;

    /*
    Busca um endereço pelo CEP usando o Cliente Feign
    */

    public EnderecoDTO buscarEnderecoPorCep(String cep){
        //Remove caracteres não numericos do cep
        String cepLimpo = cep.replaceAll("\\D", "");
        //Valida se o CEP tem 8 digitos
        if(cepLimpo.length() != 8){
            throw new RuntimeException("CEP invalido!!! Deve conter 8 digitos...");
        }
        try{
            //Faz a chamada para a Api ViaCEP usando o Feign
            EnderecoDTO endereco = viaCepClient.buscarEnderecoPorCep(cepLimpo);

            //Verifica se o CEP foi Encontrado (ViaCep retorna um Json com erro)
            if(endereco == null || endereco.getLocalidade()==null){
                throw new RuntimeException("Cep não encontrado!!!");
            }
            return endereco;
        }catch (Exception e){
            //Trata qualquer erro na comunicação com a API
            throw new RuntimeException("Erro ao complinar o CEP: "+ e.getMessage(), e);
        }
    }
}
