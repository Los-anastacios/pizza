package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.ContaDTO;
import com.pizzaria.pizzaria.entity.Conta;
import com.pizzaria.pizzaria.repository.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;


@Service
public class ContaService implements UserDetailsService {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserDetails userDetails = contaRepository.findByUsername(username);

        return  new User(userDetails.getUsername(), userDetails.getPassword(), true,true,true,true,userDetails.getAuthorities());
    }



    @Transactional
    public Conta cadastrar(ContaDTO contaDTO){
        Conta conta = new Conta();

        BeanUtils.copyProperties(contaDTO,conta);

        return contaRepository.save(conta);
    }

    public Conta findById(Long id){
        Optional<Conta> conta = contaRepository.findById(id);

        if(conta.isEmpty()){
            throw new NotFoundException("nao foi possivel achar o user por esse id");
        }

        return  conta.get();
    }

    public List<Conta> listar(){
        List<Conta> userEntities = contaRepository.findAll();

        if(userEntities.isEmpty()){
            throw new NotFoundException("nao foi possivel achar nenhum user cadastrado");
        }
        return userEntities;
    }

    public Conta editar(Long id, ContaDTO contaDTO){
        Conta conta = this.findById(id);

        if(contaDTO.getId() == 0 || contaDTO.getId().equals(null)){
            throw new NotFoundException("nao foi possivel achar o user por esse id");
        }
        conta.setPassword(conta.getPassword());
        return contaRepository.save(conta);
    }

    /*
    public Conta toConta(ContaDTO contaDTO){
        Conta conta = new Conta();

        conta.setId(contaDTO.getId());
        conta.setEmail(contaDTO.getEmail());
        conta.setSenha(contaDTO.getSenha());
        conta.setCliente(contaDTO.getCliente());

        return conta;
    }

    public ContaDTO toContaDTO(Conta conta){
        ContaDTO contaDTO = new ContaDTO();

        contaDTO.setId(conta.getId());
        contaDTO.setEmail(conta.getEmail());
        contaDTO.setSenha(conta.getSenha());
        contaDTO.setCliente(conta.getCliente());

        return contaDTO;
    }
*/

    /*public String editar(Long id, ContaDTO contaDTO){

        Conta conta = this.contaRepository.findById(id).orElse(null);
        Assert.isTrue(conta != null, "conta nao encontrado");

        this.contaRepository.save(toConta(contaDTO));

        return contaDTO.getId() + " editado com sucesso";
    }*/

    /*public List<ContaDTO> findAllConta(){
        return contaRepository.findAll().stream().map(this::toContaDTO).toList();
    }*/

}