package com.pizzaria.pizzaria.controller;
import com.pizzaria.pizzaria.dto.PedidoDTO;
import com.pizzaria.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final PedidoDTO pedidoDTO){
        try {
            return ResponseEntity.ok("Cadastraco com sucesso " + pedidoService.cadastrar(pedidoDTO).getObs());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable("id") Long id, @RequestBody PedidoDTO pedidoDTO){
        try {
            return ResponseEntity.ok(pedidoService.editar(id, pedidoDTO) + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@PathVariable("id") Long id){
        try {
            pedidoService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<PedidoDTO>> findAllPedido(){
       try {
           return  ResponseEntity.ok(pedidoService.findAllPedido());
       }catch (Exception e){
           throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage());
       }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.pedidoService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}