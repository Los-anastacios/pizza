package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ClienteDTO;
import com.pizzaria.pizzaria.Entity.Cliente;
import com.pizzaria.pizzaria.Repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO cadastrar(ClienteDTO clienteDTO){

        Assert.isTrue(clienteDTO.getNome() ==  null, "Insira um nome!");
        Assert.isTrue(clienteDTO.getCPF() == null, "Insira um Cpf válido");
        //Assert.isTrue(clienteDTO.getEnderecos() == null, "Insira um Endereço válido");

        return toUsuarioDTO(clienteRepository.save(toUsuario(clienteDTO)));
    }

    public String editar(Long id, ClienteDTO clienteDTO){

        Cliente clienteBanco = clienteRepository.findById(id).orElse(null);
        Assert.isTrue(clienteBanco != null, "Cliente nao encontrado");

        this.clienteRepository.save(toUsuario(clienteDTO));

        return clienteDTO.getNome() + " editado com sucesso";
    }

    public String deletar(Long id){

        final Cliente clienteBanco = clienteRepository.findById(id).orElse(null);
        Assert.isTrue(clienteBanco != null, "Cliente nao encontrado");

        clienteRepository.delete(clienteBanco);

        return "usuario deletado";
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
        cliente.setCpf(clienteDTO.getCPF());
        cliente.setNome(clienteDTO.getNome());
        cliente.setConta(clienteDTO.getConta());
        cliente.setEnderecos(clienteDTO.getEnderecos());
        return cliente;
    }

    public ClienteDTO toUsuarioDTO(Cliente cliente){

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setId(cliente.getId());
        clienteDTO.setCPF(cliente.getCpf());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setConta(cliente.getConta());
        clienteDTO.setEnderecos(cliente.getEnderecos());

        return clienteDTO;
    }
}