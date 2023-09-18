package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.ClienteDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO cadastrar(ClienteDTO clienteDTO){

        Assert.isTrue(clienteDTO.getNome() !=  null, "Insira um nome!");
        Assert.isTrue(clienteDTO.getCpf() != null, "Insira um Cpf válido");

        Cliente cliente = toUsuario(clienteDTO);

        this.clienteRepository.save(cliente);

        return toUsuarioDTO(cliente);
    }

    public String editar(Long id, ClienteDTO clienteDTO){

        Cliente clienteBanco = clienteRepository.findById(id).orElse(null);
        Assert.isTrue(clienteBanco != null, "Cliente nao encontrado");

        this.clienteRepository.save(toUsuario(clienteDTO));

        return clienteDTO.getNome() + " Cliente Editado!";
    }

    public String deletar(Long id){

        Cliente clienteBanco = this.clienteRepository.findById(id).orElse(null);
        Assert.isTrue(clienteBanco != null, "Cliente nao encontrado");

        this.clienteRepository.delete(clienteBanco);

        return  "Cliente Deletado!";
    }

    public List<ClienteDTO> findAllUsuario(){

        return clienteRepository.findAll().stream().map(this::toUsuarioDTO).toList();
    }

    public ClienteDTO findById(Long id){

        Assert.isTrue(id != null, "ID INVALIDO");

        Cliente clienteBanco = this.clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

        return toUsuarioDTO(clienteBanco);
    }

    public Cliente toUsuario(ClienteDTO clienteDTO){

        Cliente cliente = new Cliente();

        cliente.setId(clienteDTO.getId());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setNome(clienteDTO.getNome());
        cliente.setConta(clienteDTO.getConta());
        cliente.setEnderecos(clienteDTO.getEnderecos());
        return cliente;
    }

    public ClienteDTO toUsuarioDTO(Cliente cliente){

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setCpf(cliente.getCpf());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setConta(cliente.getConta());
        clienteDTO.setEnderecos(cliente.getEnderecos());

        return clienteDTO;
    }
}