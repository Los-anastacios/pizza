package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.ContaController;
import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.repository.ContaRepository;
import com.pizzaria.pizzaria.service.ContaService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class TesteConta {

    @MockBean
    ContaRepository contaRepository;
    @Autowired
    ContaController contaController;
    @Autowired
    ContaService contaService;

    @BeforeEach
    void injectData(){

        Conta contas = new Conta(1L,"admin", "admin");

        Mockito.when(contaRepository.save(contas)).thenReturn(contas);
        Mockito.when(contaRepository.findById(1L)).thenReturn(Optional.of(contas));

    }

    @Test
    void cadastrarTeste(){

        ContaDTO contas = new ContaDTO(1L,"admin", "admin");
        var data = contaService.cadastrar(contas);
        Assert.assertEquals("admin", data.getEmail());
    }

    @Test
    void editarTeste(){

        Conta contas = new Conta(1L,"editarAdmin", "editarAdmin");
        var conta  = contaController.editar(1L, new ContaDTO(1L,"editarAdmin","editarAdmin"));
        Assert.assertEquals("1 editado com sucessoAlterado com sucesso", conta.getBody());
    }

    @Test
    void deletarTeste(){

        String data = contaService.deletar(1L);
        Assert.assertEquals("conta deletada com sucesso", data);
    }

    @Test
    void findIdTeste(){

        contaController.cadastrar(new ContaDTO(1L,"IdAdmin", "IdAdmin"));
        var conta = contaController.findById(1L);
        Assert.assertEquals(conta.getBody().getEmail(), contaController.findById(1L).getBody().getEmail());
    }


}
