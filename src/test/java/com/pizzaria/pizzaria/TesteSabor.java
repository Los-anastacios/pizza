package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.SaborController;
import com.pizzaria.pizzaria.dto.EnderecoDTO;
import com.pizzaria.pizzaria.dto.SaborDTO;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.repository.SaborRepository;
import org.junit.Assert;
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
class TesteSabor {

    @MockBean
    SaborRepository saborRepository;
    @Autowired
    SaborController saborController;

    @BeforeEach
    void injectData(){
        Sabor sabor = new Sabor(1L,"sabor");

        List<Sabor> sabors = new ArrayList<>();
        sabors.add(sabor);

        Mockito.when(saborRepository.save(sabor)).thenReturn(sabor);
        Mockito.when(saborRepository.findById(1L)).thenReturn(Optional.of(sabor));
        Mockito.when(saborRepository.findAll()).thenReturn(sabors);
    }

    @Test
    void cadastrarTeste(){

        SaborDTO sabor = new SaborDTO(1L,"sabor");
        var data = saborController.cadastrar(sabor);

        Assert.assertEquals("Sabor, cadastrado com sucessosabor", data.getBody());
        System.out.println(data.getBody());
        System.out.println(sabor.getNome());
    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> saborController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){
        var sabor = saborController.editar(1L, new SaborDTO(1L, "saborEditado"));
        Assert.assertEquals("saborEditadoeditado com sucessoAlterado com sucesso", sabor.getBody());
    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> saborController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }


    @Test
    void deletarTeste(){
        var data = saborController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());

    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->saborController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){
        saborController.cadastrar(new SaborDTO(1L,"Idsabor"));
        var sabor = saborController.findById(1L);
        Assert.assertEquals(sabor.getBody().getNome(), saborController.findById(1L).getBody().getNome());
    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->saborController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<SaborDTO> enderecos = saborController.findAllSabor().getBody();
        Assert.assertEquals(HttpStatus.OK,saborController.findAllSabor().getStatusCode());
        Assert.assertEquals(1,enderecos.size());
    }
}
