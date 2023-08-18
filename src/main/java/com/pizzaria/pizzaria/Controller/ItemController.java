package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.ItemDTO;
import com.pizzaria.pizzaria.DTO.PedidoDTO;
import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.Service.ItemService;
import com.pizzaria.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final ItemDTO itemDTO){
        try {
            itemService.cadastrar(itemDTO);
            return ResponseEntity.ok("Item, cadastrado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestParam("id") Long id, @RequestBody ItemDTO itemDTO){
        try {
            itemService.editar(id,itemDTO);
            return ResponseEntity.ok(itemDTO.getIdPedido() + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            itemService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ItemDTO>> findAllItem(){
        try {
            return ResponseEntity.ok(itemService.findAllItem());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
