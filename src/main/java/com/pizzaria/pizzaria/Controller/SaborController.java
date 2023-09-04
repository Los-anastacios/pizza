package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.DTO.UsuarioDTO;
import com.pizzaria.pizzaria.Service.SaborService;
import com.pizzaria.pizzaria.Service.UsuarioService;
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
    public ResponseEntity<?> cadastrar(@RequestBody final SaborDTO saborDTO){
        try {
            //era string<>
            return ResponseEntity.ok("Sabor, cadastrado com sucesso" + saborService.cadastrar(saborDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(@RequestParam("id") Long id, @RequestBody SaborDTO saborDTO){
        try {
            saborService.editar(id,saborDTO);
            return ResponseEntity.ok(saborService.editar(id,saborDTO) + "Alterado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            saborService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<SaborDTO>> findAllSabor(){
        try {
            return ResponseEntity.ok(saborService.findAllSabor());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
