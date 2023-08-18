package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.ContaDTO;
import com.pizzaria.pizzaria.Entity.Conta;
import com.pizzaria.pizzaria.Repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaDTO cadastrar(ContaDTO contaDTO){

        Conta conta = contaRepository.save(toUser(contaDTO));

        return toUserDTO(conta);
    }

    public List<ContaDTO> buscarTodos(){
        List<Conta> contaListBanco = contaRepository.findAll();
        List<ContaDTO> contaDTOList = new ArrayList<>();

        for(int i = 0; i< contaListBanco.size(); i++){
            contaDTOList.add(toUserDTO(contaListBanco.get(i)));
        }

        return contaDTOList;
    }

    public Conta toUser(ContaDTO contaDTO){
        Conta conta = new Conta();

        conta.setEmail(contaDTO.getEmail());
        conta.setSenha(contaDTO.getSenha());
        conta.setUsuario(contaDTO.getUsuario());

        return conta;
    }

    public ContaDTO toUserDTO(Conta conta){
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setEmail(conta.getEmail());
        contaDTO.setSenha(conta.getSenha());
        contaDTO.setUsuario(conta.getUsuario());

        return contaDTO;
    }


}