package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.FuncioanrioController;
import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.pizzaria.entity.ContaFuncionario;
import com.pizzaria.pizzaria.entity.Funcionario;
import com.pizzaria.pizzaria.repository.FuncionarioRepository;
import com.pizzaria.pizzaria.service.FuncionarioService;
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
class TesteFuncionario {

    @MockBean
    FuncionarioRepository funcionarioRepository;
    @Autowired
    FuncioanrioController funcioanrioController;
    @Autowired
    FuncionarioService funcionarioService;

    @BeforeEach
    void injectData(){
        ContaFuncionario conta = new ContaFuncionario(1L,"admin","admin");

        Funcionario funcionario = new Funcionario(1L, "Emilio", "303.303.303-30", conta);

        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(funcionario);

        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);
    }

    @Test
    void cadastrarTeste(){

        ContaFuncionario contaFuncionario = new ContaFuncionario(1L, "admin","admin");
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L,"cadastrarAdmin","cadastrarAdmin",contaFuncionario);

        var data = funcioanrioController.cadastrar(funcionarioDTO);

        Assert.assertEquals("Cliente Cadastrado com sucesso: cadastrarAdmin", data.getBody());

    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> funcioanrioController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){
        ContaFuncionario contaFuncionario = new ContaFuncionario(1L,"editarAdmin","editarAdmin");
        var funcionario = funcioanrioController.editar(1L, new FuncionarioDTO(1L,"FuncionarioEditar","cpfEditar",contaFuncionario));
        Assert.assertEquals("FuncionarioEditareditado com sucessoAlterado com sucesso", funcionario.getBody());

    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> funcioanrioController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void deletarTeste(){

        var data = funcioanrioController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->funcioanrioController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){
        ContaFuncionario contaFuncionario = new ContaFuncionario(1L,"IdAdmin","IdAdmin");
        funcioanrioController.cadastrar(new FuncionarioDTO(1L,"findIdNome", "findIdCpf", contaFuncionario));

        var funcionario = funcioanrioController.findById(1L);
        Assert.assertEquals(funcionario.getBody().getNome(), funcioanrioController.findById(1L).getBody().getNome());
    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->funcioanrioController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<FuncionarioDTO> funcionarios = funcioanrioController.findAllUsuario().getBody();
        Assert.assertEquals(HttpStatus.OK,funcioanrioController.findAllUsuario().getStatusCode());
        Assert.assertEquals(1,funcionarios.size());
    }
}
