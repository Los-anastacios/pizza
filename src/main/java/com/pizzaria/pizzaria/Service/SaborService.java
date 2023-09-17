package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.SaborDTO;
import com.pizzaria.pizzaria.Entity.Sabor;
import com.pizzaria.pizzaria.Repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    public SaborDTO cadastrar(SaborDTO saborDTO){

        // Assert.isTrue(saborDTO.getNome() == null, "Informe o nome");
        // Assert.isTrue(saborDTO.getItem() == null, "Informe o Item");

        Sabor sabor = this.saborRepository.save(toSabor(saborDTO));

        return toSaborDTO(sabor);
    }

    public String editar(Long id, SaborDTO saborDTO){

        Sabor saborBanco = this.saborRepository.findById(id).orElse(null);
        // Assert.isTrue(saborBanco != null, "sabor nao encontrado");

        // Assert.isTrue(saborDTO.getNome() == null, "Informe o nome");
        // Assert.isTrue(saborDTO.getItem() == null, "Informe o Item");

        this.saborRepository.save(saborBanco);

        return saborDTO.getNome() + "editado com sucesso";
    }

    public String deletar(Long id){
        Sabor sabor = this.saborRepository.findById(id).orElse(null);

        // Assert.isTrue(sabor != null, "Sabor nao encontrado");

        this.saborRepository.delete(sabor);

        return "sabor deletado com sucesso";
    }

    public SaborDTO findById(Long id){
        Sabor saborBanco = saborRepository.findById(id).orElse(null);

        return toSaborDTO(saborBanco);
    }

    public List<SaborDTO> findAllSabor(){
        List<Sabor> saborBanco = saborRepository.findAll();
        List<SaborDTO> saborDTOBanco = new ArrayList<>();

        for(int i = 0; i < saborBanco.size(); i++){
            saborDTOBanco.add(toSaborDTO(saborBanco.get(i)));
        }

        return saborDTOBanco;
    }

    public SaborDTO toSaborDTO(Sabor sabor){
        SaborDTO saborDTO = new SaborDTO();

        saborDTO.setId(sabor.getId());
        saborDTO.setNome(sabor.getNome());
        saborDTO.setItem(sabor.getItem());
        saborDTO.setId(sabor.getId());

        return saborDTO;
    }

    public Sabor toSabor(SaborDTO saborDTO){
        Sabor sabor = new Sabor();

        sabor.setId(saborDTO.getId());
        sabor.setItem(saborDTO.getItem());
        sabor.setNome(saborDTO.getNome());
        sabor.setId(saborDTO.getId());

        return sabor;
    }

}