package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.ItemController;
import com.pizzaria.pizzaria.dto.ItemDTO;
import com.pizzaria.pizzaria.entity.Item;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.entity.enums.Tamanho;
import com.pizzaria.pizzaria.repository.ItemRepository;
import com.pizzaria.pizzaria.service.ItemService;
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
class TesteItem {

    @MockBean
    ItemRepository itemRepository;
    @Autowired
    ItemController itemController;
    @Autowired
    ItemService itemService;

    @BeforeEach
    void injectData(){
        List<Sabor> sabor = new ArrayList<>();

        Item item = new Item(1L,"pizza", Tamanho.G, sabor);

        List<Item> items = new ArrayList<>();
        items.add(item);

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        Mockito.when(itemRepository.findAll()).thenReturn(items);
    }

    @Test
    void cadastrarTeste(){
        List<Sabor> sabor = new ArrayList<>();
        ItemDTO item = new ItemDTO(1L,Tamanho.G,"ItemCadastrar", sabor);

        var data = itemController.cadastrar(item);

        Assert.assertEquals("cadastrado com sucessoItemCadastrar", data.getBody());
    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> itemController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void editarTeste(){
        List<Sabor> sabor = new ArrayList<>();
        Item items = new Item(1L,"ItemEditar",Tamanho.G, sabor);
        var item = itemController.editar(1L, new ItemDTO(1L, Tamanho.G,"ItemEditar",sabor));
        Assert.assertEquals("editado com sucesso", item.getBody());
    }

    @Test
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> itemController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void deletarTeste(){
        var data = itemController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->itemController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findIdTeste(){
        List<Sabor> sabor = new ArrayList<>();
        itemController.cadastrar(new ItemDTO(1L,Tamanho.G,"findIdNome",sabor));
        var item = itemController.findById(1L);
        Assert.assertEquals(item.getBody().getNome(), itemController.findById(1L).getBody().getNome());
    }

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->itemController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<ItemDTO> enderecos = itemController.findAllItem().getBody();
        Assert.assertEquals(HttpStatus.OK,itemController.findAllItem().getStatusCode());
        Assert.assertEquals(1,enderecos.size());
    }
}
