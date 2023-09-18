package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.FuncioanrioController;
import com.pizzaria.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.pizzaria.entity.ContaFuncionario;
import com.pizzaria.pizzaria.entity.Funcionario;
import com.pizzaria.pizzaria.repository.FuncionarioRepository;
import com.pizzaria.pizzaria.service.FuncionarioService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class TesteFuncionario {

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

        Mockito.when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);
        Mockito.when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
    }

    @Test
    void cadastrarTeste(){

        ContaFuncionario contaFuncionario = new ContaFuncionario(1L, "admin","admin");
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(1L,"cadastrarAdmin","cadastrarAdmin",contaFuncionario);

        var data = funcionarioService.cadastrar(funcionarioDTO);

        Assert.assertEquals("cadastrarAdmin", data.getNome());

    }

    @Test
    void editarTeste(){
        ContaFuncionario contaFuncionario = new ContaFuncionario(1L,"editarAdmin","editarAdmin");
        var funcionario = funcioanrioController.editar(1L, new FuncionarioDTO(1L,"FuncionarioEditar","cpfEditar",contaFuncionario));
        Assert.assertEquals("FuncionarioEditareditado com sucessoAlterado com sucesso", funcionario.getBody());

    }

    @Test
    void deletarTeste(){

        String data = funcionarioService.deletar(1L);
        Assert.assertEquals("usuario deletado com sucesso", data);
    }

    @Test
    void findIdTeste(){
        ContaFuncionario contaFuncionario = new ContaFuncionario(1L,"IdAdmin","IdAdmin");
        funcioanrioController.cadastrar(new FuncionarioDTO(1L,"findIdNome", "findIdCpf", contaFuncionario));

        var funcionario = funcioanrioController.findById(1L);
        Assert.assertEquals(funcionario.getBody().getNome(), funcioanrioController.findById(1L).getBody().getNome());
    }
}
