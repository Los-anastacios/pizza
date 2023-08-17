package com.pizzariaBackEnd.pizzariaBackEnd.Service;

import com.pizzariaBackEnd.pizzariaBackEnd.DTO.SaborDTO;
import com.pizzariaBackEnd.pizzariaBackEnd.Entity.Sabor;
import com.pizzariaBackEnd.pizzariaBackEnd.Repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final SaborDTO saborDTO){

        Assert.isTrue(saborDTO.getNome() == null, "Informe o nome");
        Assert.isTrue(saborDTO.getItem() == null, "Informe o Item");

        this.saborRepository.save(toSabor(saborDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    public void editar(final Long id, final SaborDTO saborDTO){

        final Sabor saborBanco = this.saborRepository.findById(id).orElse(null);

        Assert.isTrue(saborDTO.getNome() == null, "Informe o nome");
        Assert.isTrue(saborDTO.getItem() == null, "Informe o Item");

        saborBanco.setNome(saborDTO.getNome());
        saborBanco.setItem(saborDTO.getItem());

        this.saborRepository.save(saborBanco);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final Long id){

        final Sabor saborBanco = this.saborRepository.findById(id).orElse(null);
        Assert.isTrue(saborBanco != null, "Registro nao encontrado");
        this.saborRepository.delete(saborBanco);
    }

    public List<SaborDTO> findAllSabor(){
        List<Sabor> saborBanco = saborRepository.findAll();
        List<SaborDTO> saborDTOBanco = new ArrayList<>();

        for (int i=0;i<saborBanco.size();i++){
            saborDTOBanco.add(toSaborDTO(saborBanco.get(i)));
        }

        return saborDTOBanco;
    }

    public SaborDTO findById(Long id){
        Sabor saborBanco = this.saborRepository.findById(id).orElse(null);
        Assert.isTrue(saborBanco != null, "Sabor Inválido");
        return toSaborDTO(saborBanco);
    }

    public Sabor toSabor(SaborDTO saborDTO){
        Sabor sabor = new Sabor();

        sabor.setNome(saborDTO.getNome());
        sabor.setItem(saborDTO.getItem());
        return sabor;
    }

    public SaborDTO toSaborDTO(Sabor sabor){
        SaborDTO saborDTO = new SaborDTO();

        saborDTO.setNome(sabor.getNome());
        saborDTO.setItem(sabor.getItem());
        return saborDTO;
    }
}
