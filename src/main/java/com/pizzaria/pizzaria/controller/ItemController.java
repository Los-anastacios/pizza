package com.pizzaria.pizzaria.controller;

import com.pizzaria.pizzaria.dto.ItemDTO;
import com.pizzaria.pizzaria.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final ItemDTO itemDTO){
        try {
            return ResponseEntity.ok("cadastrado com sucesso" + itemService.cadastrar(itemDTO).getNome());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody ItemDTO itemDTO){
        try {
            return ResponseEntity.ok(itemService.editar(id, itemDTO));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable("id") Long id){
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
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ItemDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.itemService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
