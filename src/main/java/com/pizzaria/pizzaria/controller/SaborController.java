package com.pizzaria.pizzaria.controller;

import com.pizzaria.pizzaria.dto.SaborDTO;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sabor")
@CrossOrigin(origins = "*")
public class SaborController {

    @Autowired
    private SaborService saborService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final SaborDTO saborDTO){
        try {
            return ResponseEntity.ok("Sabor, cadastrado com sucesso" + saborService.cadastrar(saborDTO).getNome());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody SaborDTO saborDTO){
        try {
            saborService.editar(id,saborDTO);
            return ResponseEntity.ok(saborService.editar(id,saborDTO) + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable("id") Long id){
        try {
            saborService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Sabor>> findAllSabor(){
        try {
            return  ResponseEntity.ok(saborService.findAllSabor());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<SaborDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.saborService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
