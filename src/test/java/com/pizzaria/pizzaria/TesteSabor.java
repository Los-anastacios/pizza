package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.SaborController;
import com.pizzaria.pizzaria.dto.SaborDTO;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.repository.SaborRepository;
import com.pizzaria.pizzaria.service.SaborService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class TesteSabor {

    @MockBean
    SaborRepository saborRepository;
    @Autowired
    SaborController saborController;
    @Autowired
    SaborService saborService;

    @BeforeEach
    void injectData(){
        Sabor sabor = new Sabor(1L,"sabor");

        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
    }

    @Test
    void cadastrarTeste(){

        SaborDTO sabor = new SaborDTO(1L,"sabor");
        var data = saborService.cadastrar(sabor);

        Assert.assertEquals("sabor", data.getNome());
    }

    @Test
    void editarTeste(){
        var sabor = saborController.editar(1L, new SaborDTO(1L, "saborEditado"));
        Assert.assertEquals("saborEditadoeditado com sucessoAlterado com sucesso", sabor.getBody());
    }

    @Test
    void deletarTeste(){
        String data = saborService.deletar(1L);
        Assert.assertEquals("sabor deletado com sucesso", data);

    }

    @Test
    void findIdTeste(){
        saborController.cadastrar(new SaborDTO(1L,"Idsabor"));
        var sabor = saborController.findById(1L);
        Assert.assertEquals(sabor.getBody().getNome(), saborController.findById(1L).getBody().getNome());
    }
}
