package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.ClienteController;
import com.pizzaria.pizzaria.dto.ClienteDTO;
import com.pizzaria.pizzaria.dto.EnderecoDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.entity.Endereco;
import com.pizzaria.pizzaria.repository.ClienteRepository;
import com.pizzaria.pizzaria.service.ClienteService;
import com.sun.net.httpserver.HttpsServer;
import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TestesCliente {

    @MockBean
    ClienteRepository clienteRepository;
    @Autowired
    ClienteController clienteController;
    @Autowired
    ClienteService clienteService;

    @BeforeEach
    void injectData(){

        List<Endereco> enderecos = new ArrayList<>();

        Cliente clienteConta = new Cliente();
        Conta contas = new Conta(1L,"admin", "admin", clienteConta);

        Cliente cliente = new Cliente(1L, "Emilio", "101.101.101-10", contas, enderecos);
        Cliente cliente1 = new Cliente(2L, "Professor", "202.202.202-20", contas, enderecos);

        List<Cliente> clientes =new ArrayList<>();
        clientes.add(cliente);

        Mockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        Mockito.when(clienteRepository.save(cliente1)).thenReturn(cliente1);
        Mockito.when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
    }

    @Test
    void cadastrarTeste(){
        List<Endereco> enderecos = new ArrayList<>();
        Cliente clienteConta = new Cliente();

        Conta contas = new Conta(1L,"cadastrarAdmin", "cadastrarAdmin",clienteConta);
        ClienteDTO cliente = new ClienteDTO(1L,"ClienteCadastrar", "cpfCadastrar",enderecos,contas);

        var data = clienteController.cadastrar(cliente);

        Assert.assertEquals("Cliente Cadastrado com sucesso ClienteCadastrar", data.getBody());

    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> clienteController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){
        List<Endereco> enderecos = new ArrayList<>();
        Cliente clienteConta = new Cliente();
        Conta contas = new Conta(1L,"editarAdmin", "editarAdmin", clienteConta);
        var cliente = clienteController.editar(1L, new ClienteDTO(1L, "ClienteEditar", "cpfEditar", enderecos, contas));
        Assert.assertEquals("ClienteEditar Cliente Editado!", cliente.getBody());
    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> clienteController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void deletarTeste(){

        var data = clienteController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->clienteController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){
        List<Endereco> enderecos = new ArrayList<>();
        Cliente clienteConta = new Cliente();
        Conta contas = new Conta(1L,"IdAdmin", "IdAdmin", clienteConta);
        clienteController.cadastrar(new ClienteDTO(1L,"findIdNome", "findIdCpf", enderecos, contas));
        var cliente = clienteController.findById(1L);
        Assert.assertEquals(cliente.getBody().getNome(), clienteController.findById(1L).getBody().getNome());

    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->clienteController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<ClienteDTO> clienteDTOS = clienteController.findAllUsuario().getBody();
        Assert.assertEquals(HttpStatus.OK,clienteController.findAllUsuario().getStatusCode());
        Assert.assertEquals(1,clienteDTOS.size());
    }

}
