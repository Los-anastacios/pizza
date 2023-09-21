package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.ClienteDTO;
import com.pizzaria.pizzaria.dto.EnderecoDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.Endereco;
import com.pizzaria.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public EnderecoDTO cadastrar(EnderecoDTO enderecoDTO){

        Assert.notNull(enderecoDTO.getRua(), "Informe o nome da rua");

        Endereco endereco = toEndereco(enderecoDTO);
        this.enderecoRepository.save(endereco);
        return toEnderecoDTO(endereco);
    }

    @Transactional(rollbackFor = Exception.class)
    public String editar(Long id,EnderecoDTO enderecoDTO) {

        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);
        Assert.isTrue(enderecoBanco != null, "Endereco nao encontrado");

        enderecoBanco.setId(enderecoDTO.getId());
        enderecoBanco.setRua(enderecoDTO.getRua());
        enderecoBanco.setCep(enderecoDTO.getCep());
        enderecoBanco.setBairro(enderecoDTO.getBairro());
        enderecoBanco.setComplemento(enderecoDTO.getComplemento());
        enderecoBanco.setNumero(enderecoDTO.getNumero());

        this.enderecoRepository.save(enderecoBanco);

        return enderecoDTO.getRua() + "editado com sucesso";
    }
    @Transactional(rollbackFor = Exception.class)
    public String deletar(Long id){
        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);

        Assert.isTrue(enderecoBanco!=null,"id nao encontrado");
        this.enderecoRepository.delete(enderecoBanco);

        return "Endereco deletado com sucesso";
    }

    public List<EnderecoDTO> findAllEndereco(){
        List<Endereco> enderecoBanco = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOBanco = new ArrayList<>();

        for (int i =0; i < enderecoBanco.size(); i++){
            enderecoDTOBanco.add(toEnderecoDTO(enderecoBanco.get(i)));
        }

        return enderecoDTOBanco;
    }

    public EnderecoDTO findById(Long id){

        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);
        return toEnderecoDTO(enderecoBanco);
    }

    public EnderecoDTO toEnderecoDTO(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();


        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setNumero(endereco.getNumero());
        enderecoDTO.setCliente(endereco.getCliente());

        return enderecoDTO;
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();

        endereco.setId(enderecoDTO.getId());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setNumero(enderecoDTO.getNumero());
        endereco.setCliente(enderecoDTO.getCliente());

        return endereco;
    }


}