package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.UsuarioDTO;
import com.pizzaria.pizzaria.Entity.Usuario;
import com.pizzaria.pizzaria.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrar(UsuarioDTO usuarioDTO){
        this.usuarioRepository.save(toUsuario(usuarioDTO));

    }

    public List<UsuarioDTO> findByNome(String nome){
        List<Usuario> usuarioBanco = this.usuarioRepository.findPessoaByNome(nome);
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

        for(int i = 0; i < usuarioBanco.size(); i++){
            usuarioDTOList.add(toUsuarioDTO(usuarioBanco.get(i)));
        }

        return usuarioDTOList;
    }


    public List<UsuarioDTO> findAllUsuarios(){
        List<Usuario> usuariosBanco = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

        for(int i = 0; i < usuariosBanco.size(); i++){
            usuarioDTOList.add(toUsuarioDTO(usuariosBanco.get(i)));
        }

        return usuarioDTOList;
    }

    public String editar(Long id,UsuarioDTO usuarioDTO){
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        Assert.isTrue(usuarioBanco != null, "Usuario nao encontrado");
        usuarioRepository.save(toUsuario(usuarioDTO));

        return usuarioDTO.getNome() + " editado com sucesso";
    }

    public String deletar(Long id){
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        Assert.isTrue(usuarioBanco != null, "Usuario nao encontrado");
        usuarioRepository.delete(usuarioBanco);

        return "usuario deletado";
    }

    public List<UsuarioDTO> findAllUsuario(){
        List<Usuario> usuarioBanco = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDTOBanco = new ArrayList<>();

        for (int i=0; i< usuarioBanco.size();i++){
            usuarioDTOBanco.add(toUsuarioDTO(usuarioBanco.get(i)));
        }

        return  usuarioDTOBanco;
    }

    public UsuarioDTO findById(Long id){
        Usuario usuarioBanco = this.usuarioRepository.findById(id).orElse(null);
        Assert.isTrue(usuarioBanco != null, "Usuario Inválido");
        return toUsuarioDTO(usuarioBanco);
    }

    public Usuario toUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();

        usuario.setCpf(usuarioDTO.getCPF());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEnderecos(usuarioDTO.getEnderecos());
        return usuario;
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario){

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setCPF(usuario.getCpf());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setEnderecos(usuario.getEnderecos());

        return usuarioDTO;
    }
}