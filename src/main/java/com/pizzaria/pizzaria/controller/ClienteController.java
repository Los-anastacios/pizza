package com.pizzaria.pizzaria.controller;

import com.pizzaria.pizzaria.dto.ClienteDTO;
import com.pizzaria.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final ClienteDTO clienteDTO){
        try {
            //: " + clienteService.cadastrar(clienteDTO)
            return ResponseEntity.ok("Cliente Cadastrado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO){
        try {
            return ResponseEntity.ok(clienteService.editar(id, clienteDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            clienteService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ClienteDTO>> findAllUsuario(){
        return  ResponseEntity.ok(clienteService.findAllUsuario());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.clienteService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}