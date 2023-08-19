package com.pizzaria.pizzaria.Controller;
import com.pizzaria.pizzaria.DTO.PedidoDTO;
import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.Service.PedidoService;
import com.pizzaria.pizzaria.Service.SaborService;
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
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody final PedidoDTO pedidoDTO){
        try {
            //pedidoService.cadastrar(pedidoDTO);
            return ResponseEntity.ok(pedidoService.cadastrar(pedidoDTO));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestParam("id") Long id, @RequestBody PedidoDTO pedidoDTO){
        try {
            pedidoService.editar(id,pedidoDTO);
            return ResponseEntity.ok(pedidoDTO.getNome() + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
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
            return ResponseEntity.ok(pedidoService.findAllPedido());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}