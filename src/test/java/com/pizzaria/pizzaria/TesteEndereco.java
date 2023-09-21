package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.EnderecoController;
import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.dto.EnderecoDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.Endereco;
import com.pizzaria.pizzaria.repository.EnderecoRepository;
import com.pizzaria.pizzaria.service.EnderecoService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TesteEndereco {

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

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco);

        Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecos);
    }

    @Test
    void cadastrarTeste(){
        Cliente clientes = new Cliente();
        EnderecoDTO enderecoDTO = new EnderecoDTO(1L,"rua",10,"bairro","cep","complemento", clientes);

        var data = enderecoController.cadastrar(enderecoDTO);
        Assert.assertEquals("Endereço cadastrado com sucessorua", data.getBody());
    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> enderecoController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){

        Cliente clientes = new Cliente();
        Endereco endereco = new Endereco(1L,"rua1",10,"bairro1","cep1","complemento1",clientes);
        var enderecos = enderecoController.editar(1L, new EnderecoDTO(1L,"rua1",10,"bairro1","cep1","complemento1",clientes));
        Assert.assertEquals("rua1editado com sucessoAlterado com sucesso", enderecos.getBody());
    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> enderecoController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void deletarTeste(){

        var data = enderecoController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso",  data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->enderecoController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){

        Cliente clientes = new Cliente();
        enderecoController.cadastrar(new EnderecoDTO(1L,"rua",10,"bairro","cep","complemento",clientes));
        var endereco = enderecoController.findById(1L);
        Assert.assertEquals(endereco.getBody().getRua(),enderecoController.findById(1L).getBody().getRua());
    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->enderecoController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<EnderecoDTO> enderecos = enderecoController.findAllEndereco().getBody();
        Assert.assertEquals(HttpStatus.OK,enderecoController.findAllEndereco().getStatusCode());
        Assert.assertEquals(1,enderecos.size());
    }

}
