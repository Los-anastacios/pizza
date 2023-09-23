package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.pizzaria.entity.Funcionario;
import com.pizzaria.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioDTO cadastrar(FuncionarioDTO funcionarioDTO){

        Assert.isTrue(funcionarioDTO.getNome() != null,"INSIRA UM NOME VALIDO!");
        Assert.isTrue(funcionarioDTO.getCpf() != null, "INSIRA UM CPF VALIDO");

        Funcionario funcionario = toFuncionario(funcionarioDTO);

        this.funcionarioRepository.save(funcionario);

        return  toFuncionarioDTO(funcionario);
    }

    public String editar(Long id, FuncionarioDTO funcionarioDTO){
        Funcionario funcionarioBanco = funcionarioRepository.findById(id).orElse(null);
        Assert.isTrue(funcionarioBanco != null, "FUNCIONARIO NAO ENCONTRADO");

        this.funcionarioRepository.save(toFuncionario(funcionarioDTO));

        return funcionarioDTO.getNome() + "editado com sucesso";
    }

    public String deletar(Long id){
        final Funcionario funcionarioBanco = funcionarioRepository.findById(id).orElse(null);
        Assert.isTrue(funcionarioBanco != null, "FUNCIONARIO NAO ENCONTRADO");
        funcionarioRepository.delete(funcionarioBanco);

        return "usuario deletado com sucesso";
    }

    public List<FuncionarioDTO> findAllFuncionario(){
        return funcionarioRepository.findAll().stream().map(this::toFuncionarioDTO).toList();
    }

    public FuncionarioDTO findById(Long id){

        Assert.isTrue(id != null, "ID INVALIDO");

        Funcionario funcionarioBanco = this.funcionarioRepository.findById(id).orElse(null);

        return toFuncionarioDTO(funcionarioBanco);
    }

    public Funcionario toFuncionario(FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = new Funcionario();

        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setContaFuncionario(funcionarioDTO.getContaFuncionario());
        return funcionario;
    }

    public FuncionarioDTO toFuncionarioDTO(Funcionario funcionario){
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        funcionarioDTO.setCpf(funcionario.getCpf());
        funcionarioDTO.setContaFuncionario(funcionario.getContaFuncionario());
        return funcionarioDTO;
    }
}
