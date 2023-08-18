package com.pizzaria.pizzaria.Controller;

import com.pizzaria.pizzaria.DTO.EnderecoDTO;
import com.pizzaria.pizzaria.DTO.UsuarioDTO;
import com.pizzaria.pizzaria.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody final UsuarioDTO usuarioDTO){
        try {
            usuarioService.cadastrar(usuarioDTO);
            return ResponseEntity.ok("Usuario, cadastrado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editar(@RequestParam("id") Long id, @RequestBody UsuarioDTO usuarioDTO){
        try {
            usuarioService.editar(id,usuarioDTO);
            return ResponseEntity.ok(usuarioDTO.getNome() + "Alterado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{id}")
    public ResponseEntity<String> deleta(@RequestParam("id") Long id){
        try {
            usuarioService.deletar(id);
            return ResponseEntity.ok("Deletado com sucesso");
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioDTO>> findAllUsuario(@RequestBody final UsuarioDTO usuarioDTO){
        try {
            return ResponseEntity.ok(usuarioService.findAllUsuario());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.usuarioService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}