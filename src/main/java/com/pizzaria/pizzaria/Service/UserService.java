package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.UserDTO;
import com.pizzaria.pizzaria.Entity.Conta;
import com.pizzaria.pizzaria.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO cadastrar(UserDTO userDTO){

        Conta conta = userRepository.save(toUser(userDTO));

        return toUserDTO(conta);
    }


    public List<UserDTO> buscarTodos(){
        List<Conta> contaListBanco = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for(int i = 0; i< contaListBanco.size(); i++){
            userDTOList.add(toUserDTO(contaListBanco.get(i)));
        }

        return userDTOList;
    }

    public Conta toUser(UserDTO userDTO){
        Conta conta = new Conta();

        conta.setEmail(userDTO.getEmail());
        conta.setSenha(userDTO.getSenha());
        conta.setUsuario(userDTO.getUsuario());

        return conta;
    }

    public UserDTO toUserDTO(Conta conta){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(conta.getEmail());
        userDTO.setSenha(conta.getSenha());
        userDTO.setUsuario(conta.getUsuario());

        return userDTO;
    }


}