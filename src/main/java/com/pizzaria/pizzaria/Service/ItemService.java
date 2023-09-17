package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ItemDTO;
import com.pizzaria.pizzaria.Entity.Item;
import com.pizzaria.pizzaria.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional(rollbackFor = Exception.class)
    public ItemDTO cadastrar(final ItemDTO itemDTO){


        return toItemDTO(itemRepository.save(toItem(itemDTO)));
    }

    @Transactional(rollbackFor = Exception.class)
    public String editar(final Long id, final  ItemDTO itemDTO){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);
        Assert.isTrue(itemBanco != null, "Item nao encontrado");

        this.itemRepository.save(itemBanco);

        return itemDTO + ": editado com sucesso";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deletar(final Long id){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);
        Assert.isTrue(itemBanco != null, "item nao encontrado");

        this.itemRepository.delete(itemBanco);

        return "Item deletado com sucesso";

    }

    public List<ItemDTO> findAllItem(){

        return  itemRepository.findAll().stream().map(this::toItemDTO).toList();
    }

    public ItemDTO findById(Long id){

         Assert.isTrue(id != null, "Item Inválido");

        Item itemBanco = this.itemRepository.findById(id).orElse(null);

        return toItemDTO(itemBanco);
    }

    public ItemDTO toItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setNome(item.getNome());
        itemDTO.setTamanho(item.getTamanho());
        itemDTO.setSabor(item.getSabor());

        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO){
        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setNome(itemDTO.getNome());
        item.setTamanho(itemDTO.getTamanho());
        item.setSabor(itemDTO.getSabor());

        return item;
    }
}