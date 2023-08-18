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
    public void cadastrar(final ItemDTO itemDTO){

        Assert.isTrue(itemDTO.getIdPedido() == null, "Insira um Pedido");
        Assert.isTrue(itemDTO.getTamanho() == null, "Insira um tamanho valido");

        this.itemRepository.save(toItem(itemDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final  ItemDTO itemDTO){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);

        Assert.isTrue(itemDTO.getIdPedido() == null, "Insira um Pedido");
        Assert.isTrue(itemDTO.getTamanho() == null, "Insira um tamanho valido");

        itemBanco.setIdPedido(itemDTO.getIdPedido());
        itemBanco.setTamanho(itemDTO.getTamanho());

        this.itemRepository.save(itemBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);
        Assert.isTrue(itemBanco != null, "Registro nao encontrado");
        this.itemRepository.delete(itemBanco);
    }

    public List<ItemDTO> findAllItem(){
        List<Item> itemBanco = itemRepository.findAll();
        List<ItemDTO> itemDTOBanco = new ArrayList<>();

        for (int i=0; i<itemBanco.size();i++){
            itemDTOBanco.add(toItemDTO(itemBanco.get(i)));
        }
        return itemDTOBanco;
    }

    public ItemDTO findByID(Long id){

        Item itemBanco = this.itemRepository.findById(id).orElse(null);
        Assert.isTrue(itemBanco != null, "Item Inválido");
        return toItemDTO(itemBanco);
    }

    public ItemDTO toItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setEntrega(item.getEntrega());
        itemDTO.setTamanho(item.getTamanho());
        itemDTO.setIdPedido(item.getIdPedido());

        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO){
        Item item = new Item();

        item.setEntrega(itemDTO.getEntrega());
        item.setTamanho(itemDTO.getTamanho());
        item.setIdPedido(itemDTO.getIdPedido());

        return item;
    }
}