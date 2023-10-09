package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.SaborDTO;
import com.pizzaria.pizzaria.entity.Sabor;
import com.pizzaria.pizzaria.repository.SaborRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    public SaborDTO cadastrar(SaborDTO saborDTO){

        Assert.isTrue(saborDTO.getNome() != null, "Informe o nome");
        Sabor sabor = toSabor(saborDTO);

        Assert.isTrue(sabor.getNome() != null, "Informe o nome");
        this.saborRepository.save(sabor);
        return toSaborDTO(sabor);
    }

    public String editar(Long id, SaborDTO saborDTO){

        Sabor saborBanco = this.saborRepository.findById(id).orElse(null);
        Assert.isTrue(saborBanco != null, "sabor nao encontrado");

        this.saborRepository.save(toSabor(saborDTO));

        return saborDTO.getNome() + "editado com sucesso";
    }

    public String deletar(Long id){

        Sabor sabor = this.saborRepository.findById(id).orElse(null);
        Assert.isTrue(sabor != null, "Sabor nao encontrado");

        this.saborRepository.delete(sabor);

        return "sabor deletado com sucesso";
    }

    public SaborDTO findById(Long id){

        Assert.isTrue(id != null, "ID INVALIDO");

        Sabor saborBanco = this.saborRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Sabor não encontrado!"));

        return toSaborDTO(saborBanco);
    }

    public List<SaborDTO> findAllSabor(){

        return saborRepository.findAll().stream().map(this::toSaborDTO).toList();
    }

    public SaborDTO toSaborDTO(Sabor sabor){
        SaborDTO saborDTO = new SaborDTO();

        saborDTO.setId(sabor.getId());
        saborDTO.setNome(sabor.getNome());

        return saborDTO;
    }

    public Sabor toSabor(SaborDTO saborDTO){
        Sabor sabor = new Sabor();

        sabor.setId(saborDTO.getId());
        sabor.setNome(saborDTO.getNome());

        return sabor;
    }

}