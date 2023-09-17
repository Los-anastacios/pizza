package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.ContaDTO;
import com.pizzaria.pizzaria.Service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody final ContaDTO contaDTO){
        try {
            return ResponseEntity.ok("Conta, cadastrado com sucesso" + contaService.cadastrar(contaDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody ContaDTO contaDTO){
        try {

            return ResponseEntity.ok(contaService.editar(id, contaDTO)+ "Alterado com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable("id") Long id){
        try {
            contaService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ContaDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.contaService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
