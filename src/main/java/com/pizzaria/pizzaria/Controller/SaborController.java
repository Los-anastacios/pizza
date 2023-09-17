package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.Service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sabor")
public class SaborController {

    @Autowired
    private SaborService saborService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final SaborDTO saborDTO){
        try {
            return ResponseEntity.ok("Sabor, cadastrado com sucesso" + saborService.cadastrar(saborDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody SaborDTO saborDTO){
        try {
            saborService.editar(id,saborDTO);
            return ResponseEntity.ok(saborService.editar(id,saborDTO) + "Alterado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
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
    public ResponseEntity<List<SaborDTO>> findAllSabor(){
        return  ResponseEntity.ok(saborService.findAllSabor());
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
