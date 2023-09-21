package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.ContaController;
import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.repository.ContaRepository;
import com.pizzaria.pizzaria.service.ContaService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class TesteConta {

    @MockBean
    ContaRepository contaRepository;
    @Autowired
    ContaController contaController;
    @Autowired
    ContaService contaService;

    @BeforeEach
    void injectData(){

        Conta contas = new Conta(1L,"admin", "admin");

        when(contaRepository.save(contas)).thenReturn(contas);
        when(contaRepository.findById(1L)).thenReturn(Optional.of(contas));

    }

    @Test
    void cadastrarTeste(){

        ContaDTO contas = new ContaDTO(1L,"admin", "admin");
        var data = contaController.cadastrar(contas);
        Assert.assertEquals("Conta, cadastrado com sucessoadmin", data.getBody());
    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> contaController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){

        var conta  = contaController.editar(1L, new ContaDTO(1L,"editarAdmin","editarAdmin"));
        Assert.assertEquals("1 editado com sucessoAlterado com sucesso", conta.getBody());
    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> contaController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void deletarTeste(){

        var data = contaController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->contaController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){

        contaController.cadastrar(new ContaDTO(1L,"IdAdmin", "IdAdmin"));
        var conta = contaController.findById(1L);
        Assert.assertEquals(conta.getBody().getEmail(), contaController.findById(1L).getBody().getEmail());
    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->contaController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }
}
