package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ItemDTO;
import com.pizzaria.pizzaria.Entity.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    public ItemDTO toItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setEntrega(item.getEntrega());
        itemDTO.setTamanho(item.getTamanho());
        itemDTO.setPedido(item.getPedido());

        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO){
        Item item = new Item();

        item.setEntrega(itemDTO.getEntrega());
        item.setTamanho(itemDTO.getTamanho());
        item.setPedido(itemDTO.getPedido());

        return item;
    }
}