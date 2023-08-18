package com.pizzaria.pizzaria.Service;

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

        Assert.isTrue(enderecoDTO.getRua() == null, "Informe o nome da rua");
        Assert.isTrue(enderecoDTO.getBairro() == null, "informe o bairro");
        Assert.isTrue(enderecoDTO.getCep() == null, "Informe o Cep");
        Assert.isTrue(enderecoDTO.getNumero() <= 0, "Informe o numero");
        Assert.isTrue(enderecoDTO.getComplemento() == null, "Informe o Complemento");
        Assert.isTrue(enderecoDTO.getUsuario() == null, "Informe o Usuario");

        this.enderecoRepository.save(toEndereco(enderecoDTO));
    }


    public void editar(Long id,EnderecoDTO enderecoDTO){

        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);


        Assert.isTrue(enderecoDTO.getRua() == null, "Informe o nome da rua");
        Assert.isTrue(enderecoDTO.getBairro() == null, "informe o bairro");
        Assert.isTrue(enderecoDTO.getCep() == null, "Informe o Cep");
        Assert.isTrue(enderecoDTO.getNumero() <= 0, "Informe o numero");
        Assert.isTrue(enderecoDTO.getComplemento() == null, "Informe o Complemento");
        Assert.isTrue(enderecoDTO.getUsuario() == null, "Informe o Usuario");

        enderecoBanco.setRua(enderecoDTO.getRua());
        enderecoBanco.setCep(enderecoDTO.getCep());
        enderecoBanco.setBairro(enderecoDTO.getBairro());
        enderecoBanco.setComplemento(enderecoDTO.getComplemento());
        enderecoBanco.setNumero(enderecoDTO.getNumero());

        Assert.isTrue(enderecoBanco != null, "Endereco nao encontrado");
        this.enderecoRepository.save(enderecoBanco);
    }

    public void deletar(Long id){
        Endereco enderecoBanco = this.enderecoRepository.findById(id).orElse(null);

        Assert.isTrue(enderecoBanco != null, "Endereco nao encontrado");
        this.enderecoRepository.delete(enderecoBanco);
    }

    public List<EnderecoDTO> findAllEnderecos(){
        List<Endereco> enderecosBanco = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();

        for(int i = 0; i < enderecosBanco.size(); i++){
            enderecoDTOList.add(toEnderecoDTO(enderecosBanco.get(i)));
        }

        return enderecoDTOList;
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
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        endereco.setRua(enderecoDTO.getRua());
        endereco.setCep(enderecoDTO.getCep());
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setComplemento(enderecoDTO.getComplemento());
        endereco.setNumero(enderecoDTO.getNumero());

        return enderecoDTO;
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();

        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setComplemento(endereco.getComplemento());
        enderecoDTO.setNumero(endereco.getNumero());

        return endereco;
    }
}