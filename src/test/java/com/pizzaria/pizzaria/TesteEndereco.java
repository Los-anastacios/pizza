package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.EnderecoController;
import com.pizzaria.pizzaria.dto.EnderecoDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.Endereco;
import com.pizzaria.pizzaria.repository.EnderecoRepository;
import com.pizzaria.pizzaria.service.EnderecoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class TesteEndereco {

    @MockBean
    EnderecoRepository enderecoRepository;
    @Autowired
    EnderecoController enderecoController;
    @Autowired
    EnderecoService enderecoService;

    @BeforeEach
    void injectData(){
        Cliente clientes = new Cliente();

        Endereco endereco = new Endereco(1L,"rua",10,"bairro","cep","complemento",clientes);

        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
    }

    @Test
    void cadastrarTeste(){
        Cliente clientes = new Cliente();
        EnderecoDTO enderecoDTO = new EnderecoDTO(1L,"rua",10,"bairro","cep","complemento", clientes);

        var data = enderecoService.cadastrar(enderecoDTO);
        Assert.assertEquals("rua", data.getRua());
    }

    @Test
    void editarTeste(){

        Cliente clientes = new Cliente();
        Endereco endereco = new Endereco(1L,"rua1",10,"bairro1","cep1","complemento1",clientes);
        var enderecos = enderecoController.editar(1L, new EnderecoDTO(1L,"rua1",10,"bairro1","cep1","complemento1",clientes));
        Assert.assertEquals("rua1editado com sucessoAlterado com sucesso", enderecos.getBody());
    }

    @Test
    void deletarTeste(){

        String data = enderecoService.deletar(1L);
        Assert.assertEquals("Endereco deletado com sucesso",  data);
    }
    @Test
    void findIdTeste(){

        Cliente clientes = new Cliente();
        enderecoController.cadastrar(new EnderecoDTO(1L,"rua",10,"bairro","cep","complemento",clientes));
        var endereco = enderecoController.findById(1L);
        Assert.assertEquals(endereco.getBody().getRua(),enderecoController.findById(1L).getBody().getRua());
    }

}
