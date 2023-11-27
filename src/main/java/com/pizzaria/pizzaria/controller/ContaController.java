package com.pizzaria.pizzaria.controller;

import com.pizzaria.pizzaria.config.security.TokenService;
import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.dto.security.AuthenticationDTO;
import com.pizzaria.pizzaria.dto.security.LoginResponseDTO;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;


    @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Conta conta = new Conta();
        BeanUtils.copyProperties(auth,conta);
        var token = tokenService.gerarToken(conta);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<Conta> cadastrar(@RequestBody final ContaDTO contaDTO){
        try {
            return ResponseEntity.ok(contaService.cadastrar(contaDTO));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<Conta> editar(@PathVariable("id") final Long id,@Valid @RequestBody final ContaDTO contaDTO){
        try {
            return ResponseEntity.ok(contaService.editar(id, contaDTO));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Conta> findById(@PathVariable("id") final Long id){
        try {
            return ResponseEntity.ok(this.contaService.findById(id));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Conta>> listar(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(contaService.listar());

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Error listar, " + e.getMessage());
        }
    }

}
