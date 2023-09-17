package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ContaDTO;
import com.pizzaria.pizzaria.Entity.Conta;
import com.pizzaria.pizzaria.Repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaDTO cadastrar(ContaDTO contaDTO){

        Conta conta = contaRepository.save(toConta(contaDTO));

        Assert.isTrue(contaDTO.getEmail() != null,"INSIRA UM EMAIL");
        Assert.isTrue(contaDTO.getSenha() != null, "INSIRA UMA SENHA");
        return toContaDTO(conta);
    }

    public Conta toConta(ContaDTO contaDTO){
        Conta conta = new Conta();

        conta.setId(contaDTO.getId());
        conta.setEmail(contaDTO.getEmail());
        conta.setSenha(contaDTO.getSenha());

        return conta;
    }

    public ContaDTO toContaDTO(Conta conta){
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setId(conta.getId());
        contaDTO.setEmail(conta.getEmail());
        contaDTO.setSenha(conta.getSenha());

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

    public ContaDTO findById(Long id){

        Assert.isTrue(id != null, "ID INVALIDO");
        Conta contaBanco = this.contaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado!"));

        return  toContaDTO(contaBanco);
    }
}