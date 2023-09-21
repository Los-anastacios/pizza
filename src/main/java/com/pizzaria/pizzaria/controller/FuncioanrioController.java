package com.pizzaria.pizzaria.controller;

import com.pizzaria.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncioanrioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final FuncionarioDTO funcionarioDTO){
        try {
            // era string<>
            return ResponseEntity.ok("Cliente Cadastrado com sucesso: " + funcionarioService.cadastrar(funcionarioDTO).getNome());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody FuncionarioDTO funcionarioDTO){
        try {
            return ResponseEntity.ok(funcionarioService.editar(id, funcionarioDTO) + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable("id") Long id){
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<FuncionarioDTO>> findAllUsuario(){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAllFuncionario());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.funcionarioService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
