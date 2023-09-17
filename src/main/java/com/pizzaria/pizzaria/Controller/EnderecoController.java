package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.EnderecoDTO;
import com.pizzaria.pizzaria.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody @Validated EnderecoDTO enderecoDTO){
        try {
            //era string<>
            return  ResponseEntity.ok("Endereço cadastrado com sucesso" + enderecoService.cadastrar(enderecoDTO));

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@RequestParam("id") Long id, @RequestBody EnderecoDTO enderecoDTO){
        try{

            return ResponseEntity.ok(enderecoService.editar(id, enderecoDTO) + "Alterado com sucesso");
        }catch (Exception e){
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            enderecoService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<EnderecoDTO>> findAllEndereco(){
        try {
            return ResponseEntity.ok(enderecoService.findAllEndereco());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.enderecoService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}