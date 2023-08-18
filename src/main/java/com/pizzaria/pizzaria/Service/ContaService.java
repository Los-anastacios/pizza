package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ContaDTO;

import com.pizzaria.pizzaria.Entity.Conta;
import com.pizzaria.pizzaria.Repository.ContaRepository;
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


        return toContaDTO(conta);
    }

    public List<ContaDTO> buscarTodos(){
        List<Conta> contaListBanco = contaRepository.findAll();
        List<ContaDTO> contaDTOList = new ArrayList<>();

        for(int i = 0; i< contaListBanco.size(); i++){
            contaDTOList.add(toContaDTO(contaListBanco.get(i)));
        }

        return contaDTOList;
    }

    public Conta toConta(ContaDTO userDTO){
        Conta conta = new Conta();

        conta.setEmail(contaDTO.getEmail());
        conta.setSenha(contaDTO.getSenha());
        conta.setUsuario(contaDTO.getUsuario());

        return conta;
    }


    public ContaDTO toContaDTO(Conta conta){
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setEmail(conta.getEmail());
        contaDTO.setSenha(conta.getSenha());
        contaDTO.setUsuario(conta.getUsuario());

        return contaDTO;
    }

    public String editar(Long id, ContaDTO contaDTO){
        Conta conta = this.contaRepository.findById(id).orElse(null);

        Assert.isTrue(conta != null, "conta nao encontrado");
        //fazer verificacoes

        this.contaRepository.save(toConta(contaDTO));

        return contaDTO.getId() + " editado";
    }

    public String deletar(Long id){
        Conta contaBanco = contaRepository.findById(id).orElse(null);

        Assert.isTrue(contaBanco != null, "Conta nao encontrado");
        contaRepository.delete(contaBanco);

        return "conta deletado";
    }

    public List<ContaDTO> findAllConta(){
        List<Conta> contaBanco = contaRepository.findAll();
        List<ContaDTO> contaDTOBanco = new ArrayList<>();

        for (int i=0; i< contaBanco.size();i++){
            contaDTOBanco.add(toContaDTO(contaBanco.get(i)));
        }

        return  contaDTOBanco;
    }

}