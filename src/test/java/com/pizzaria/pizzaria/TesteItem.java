package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.ItemController;
import com.pizzaria.pizzaria.dto.ItemDTO;
import com.pizzaria.pizzaria.entity.Item;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.entity.enums.Tamanho;
import com.pizzaria.pizzaria.repository.ItemRepository;
import com.pizzaria.pizzaria.service.ItemService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TesteItem {

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

        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
    }

    @Test
    void cadastrarTeste(){
        List<Sabor> sabor = new ArrayList<>();
        ItemDTO item = new ItemDTO(1L,Tamanho.G,"ItemCadastrar", sabor);

        var data = itemService.cadastrar(item);

        Assert.assertEquals("ItemCadastrar", data.getNome());
    }

    @Test
    void editarTeste(){
        List<Sabor> sabor = new ArrayList<>();
        Item items = new Item(1L,"ItemEditar",Tamanho.G, sabor);
        var item = itemController.editar(1L, new ItemDTO(1L, Tamanho.G,"ItemEditar",sabor));
        Assert.assertEquals("editado com sucesso", item.getBody());
    }

    @Test
    void deletarTeste(){
        String data = itemService.deletar(1L);
        Assert.assertEquals("Item deletado com sucesso", data);
    }

    @Test
    void findIdTeste(){
        List<Sabor> sabor = new ArrayList<>();
        itemController.cadastrar(new ItemDTO(1L,Tamanho.G,"findIdNome",sabor));
        var item = itemController.findById(1L);
        Assert.assertEquals(item.getBody().getNome(), itemController.findById(1L).getBody().getNome());
    }
}
