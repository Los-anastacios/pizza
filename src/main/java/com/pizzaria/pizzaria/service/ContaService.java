package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;


@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaDTO cadastrar(ContaDTO contaDTO){

        Assert.isTrue(contaDTO.getEmail() != null,"INSIRA UM EMAIL");
        Assert.isTrue(contaDTO.getSenha() != null, "INSIRA UMA SENHA");

        Conta conta = toConta(contaDTO);
        this.contaRepository.save(conta);
        return toContaDTO(conta);

    }

    public Conta toConta(ContaDTO contaDTO){
        Conta conta = new Conta();

        conta.setId(contaDTO.getId());
        conta.setEmail(contaDTO.getEmail());
        conta.setSenha(contaDTO.getSenha());
        conta.setCliente(contaDTO.getCliente());

        return conta;
    }

    public ContaDTO toContaDTO(Conta conta){
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setId(conta.getId());
        contaDTO.setEmail(conta.getEmail());
        contaDTO.setSenha(conta.getSenha());
        contaDTO.setCliente(conta.getCliente());

        return contaDTO;
    }

    public String editar(Long id, ContaDTO contaDTO){

        Conta conta = this.contaRepository.findById(id).orElse(null);
        Assert.isTrue(conta != null, "conta nao encontrado");

        this.contaRepository.save(toConta(contaDTO));

        return contaDTO.getId() + " editado com sucesso";
    }

    public String deletar(Long id){

        Conta contaBanco = contaRepository.findById(id).orElse(null);

        Assert.isTrue(contaBanco != null, "Conta nao encontrado");
        contaRepository.delete(contaBanco);

        return "conta deletada com sucesso";
    }

    public List<ContaDTO> findAllConta(){
        return contaRepository.findAll().stream().map(this::toContaDTO).toList();
    }

    public ContaDTO findById(Long id){

        //Assert.isTrue(id != null, "ID INVALIDO");

        Conta contaBanco = this.contaRepository.findById(id).orElse(null);

        return  toContaDTO(contaBanco);
    }
}