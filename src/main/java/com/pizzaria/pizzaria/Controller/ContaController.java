package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.ContaDTO;
import com.pizzaria.pizzaria.DTO.PedidoDTO;
import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.Service.ContaService;
import com.pizzaria.pizzaria.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final ContaDTO contaDTO){
        try {
            contaService.cadastrar(contaDTO);
            return ResponseEntity.ok("Conta, cadastrado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestParam("id") Long id, @RequestBody ContaDTO contaDTO){
        try {
            contaService.editar(id,contaDTO);
            return ResponseEntity.ok(contaDTO.getId() + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            contaService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<ContaDTO>> findAllConta(@RequestBody final ContaDTO contaDTO){
        try {
            return ResponseEntity.ok(contaService.findAllConta());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
