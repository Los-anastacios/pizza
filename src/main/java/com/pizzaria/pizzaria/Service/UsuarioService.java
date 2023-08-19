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

    public UsuarioDTO cadastrar(UsuarioDTO usuarioDTO){

        Assert.isTrue(usuarioDTO.getNome() ==  null, "Insira um nome!");
        Assert.isTrue(usuarioDTO.getTelefone() == null, "Insira um telefone válido");
        Assert.isTrue(usuarioDTO.getCpf() == null, "Insira um Cpf válido");
        Assert.isTrue(usuarioDTO.getEnderecos() == null, "Insira um Endereço válido");

        Usuario usuario = this.usuarioRepository.save(toUsuario(usuarioDTO));
        return toUsuarioDTO(usuario);
    }

    public String editar(Long id,UsuarioDTO usuarioDTO){

        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
        Assert.isTrue(usuarioBanco != null, "Usuario nao encontrado");

        Assert.isTrue(usuarioDTO.getNome() ==  null, "Insira um nome!");
        Assert.isTrue(usuarioDTO.getTelefone() == null, "Insira um telefone válido");
        Assert.isTrue(usuarioDTO.getCpf() == null, "Insira um Cpf válido");
        Assert.isTrue(usuarioDTO.getEnderecos() == null, "Insira um Endereço válido");

        usuarioBanco.setNome(usuarioDTO.getNome());
        usuarioBanco.setTelefone(usuarioDTO.getTelefone());
        usuarioBanco.setCpf(usuarioDTO.getCpf());
        usuarioBanco.setEnderecos(usuarioDTO.getEnderecos());

        this.usuarioRepository.save(toUsuario(usuarioDTO));

        return usuarioDTO.getNome() + " editado com sucesso";
    }

    public String deletar(Long id){

        final Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);
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

    public List<UsuarioDTO> findByNome(String nome){
        List<Usuario> usuarioBanco = this.usuarioRepository.findPessoaByNome(nome);
        List<UsuarioDTO> usuarioDTOList = new ArrayList<>();

        for(int i = 0; i < usuarioBanco.size(); i++){
            usuarioDTOList.add(toUsuarioDTO(usuarioBanco.get(i)));
        }

        return usuarioDTOList;
    }

    public Usuario toUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();

        usuario.setId(usuarioDTO.getId());
        usuario.setCpf(usuarioDTO.getCpf());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setTelefone(usuarioDTO.getTelefone());
        usuario.setEnderecos(usuarioDTO.getEnderecos());
        return usuario;
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario){

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setCpf(usuario.getCpf());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setTelefone(usuario.getTelefone());
        usuarioDTO.setEnderecos(usuario.getEnderecos());

        return usuarioDTO;
    }
}