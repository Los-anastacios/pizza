package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.DTO.EnderecoDTO;
import com.pizzaria.pizzaria.Entity.Endereco;
import com.pizzaria.pizzaria.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public void cadastrar(EnderecoDTO enderecoDTO){
        //fazer validacoes antes de salvar

        this.enderecoRepository.save(toEndereco(enderecoDTO));
    }

    public List<EnderecoDTO> findAllEnderecos(){
        List<Endereco> enderecosBanco = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();

        for(int i = 0; i < enderecosBanco.size(); i++){
            enderecoDTOList.add(toEnderecoDTO(enderecosBanco.get(i)));
        }

        return enderecoDTOList;
    }

    public void editar(Long id,EnderecoDTO enderecoDTO){
        //fazer validacoes entes d esalvar
        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);

        Assert.isTrue(enderecoBanco != null, "Endereco nao encontrado");
        this.enderecoRepository.save(toEndereco(enderecoDTO));
    }

    public void deletar(Long id){
        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);

        Assert.isTrue(enderecoBanco != null, "Endereco nao encontrado");
        this.enderecoRepository.delete(enderecoBanco);
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
        Assert.isTrue(enderecoBanco != null, "Produto Inválido");
        return toEnderecoDTO(enderecoBanco);
    }

    public EnderecoDTO toEnderecoDTO(Endereco endereco){
        EnderecoDTO enderecoDTO1 = new EnderecoDTO();

        enderecoDTO1.setRua(endereco.getRua());
        enderecoDTO1.setNumero(endereco.getNumero());

        return enderecoDTO1;
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();

        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());

        return endereco;
    }
}