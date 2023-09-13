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

        // itemBanco.setNome(itemDTO.getNome());
        // Assert.isTrue(itemDTO.getTamanho() == null, "Insira um tamanho valido");

        Item item = this.itemRepository.save(toItem(itemDTO));

        return toItemDTO(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public String editar(final Long id, final  ItemDTO itemDTO){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);

        // Assert.isTrue(itemDTO.getIdPedido() == null, "Insira um Pedido");
        // Assert.isTrue(itemDTO.getTamanho() == null, "Insira um tamanho valido");

        itemBanco.setNome(itemDTO.getNome());
        itemBanco.setTamanho(itemDTO.getTamanho());

        this.itemRepository.save(itemBanco);

        return itemDTO + ": editado com sucesso";
    }

    @Transactional(rollbackFor = Exception.class)
    public String deletar(final Long id){

        final Item itemBanco = this.itemRepository.findById(id).orElse(null);
        //  Assert.isTrue(itemBanco != null, "Registro nao encontrado");

        this.itemRepository.delete(itemBanco);

        return "Item deletado com sucesso";

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
        // Assert.isTrue(itemBanco != null, "Item Inválido");
        return toItemDTO(itemBanco);
    }

    public ItemDTO toItemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(item.getId());
        itemDTO.setTamanho(item.getTamanho());

        return itemDTO;
    }

    public Item toItem(ItemDTO itemDTO){
        Item item = new Item();

        item.setId(itemDTO.getId());
        item.setTamanho(itemDTO.getTamanho());

        return item;
    }
}