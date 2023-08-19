package com.pizzaria.pizzaria.Service;

import com.pizzaria.pizzaria.DTO.PedidoDTO;
import com.pizzaria.pizzaria.Repository.PedidoRepository;
import com.pizzaria.pizzaria.Entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public PedidoDTO cadastrar(PedidoDTO pedidoDTO){

        // Assert.isTrue(pedidoDTO.getIdUsuario() == null, "Informe o Cliente");
        // Assert.isTrue(pedidoDTO.getNome() == null, "Informe o nome do Pedido");
        // Assert.isTrue(pedidoDTO.getEstado() == null, "Informe o Estado");

        Pedido pedido = this.pedidoRepository.save(toPedido(pedidoDTO));

        return toPedidoDTO(pedido);

    }

    public String editar(Long id, PedidoDTO pedidoDTO){

        Pedido pedidoBanco = this.pedidoRepository.findById(id).orElse(null);
        //  Assert.isTrue(pedidoBanco != null, "Pedido nao encontrado");

        // Assert.isTrue(pedidoDTO.getIdUsuario() == null, "Informe o Cliente");
        // Assert.isTrue(pedidoDTO.getNome() == null, "Informe o nome do Pedido");
        // Assert.isTrue(pedidoDTO.getEstado() == null, "Informe o Estado");

        pedidoBanco.setNome(pedidoDTO.getNome());
        pedidoBanco.setObs(pedidoDTO.getObs());
        pedidoBanco.setIdUsuario(pedidoDTO.getIdUsuario());
        pedidoBanco.setEstado(pedidoDTO.getEstado());

        this.pedidoRepository.save(pedidoBanco);

        return pedidoDTO.getNome() + " editado com sucesso";
    }

    public String deletar(Long id){

        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);
        // Assert.isTrue(pedido != null, "Pedido nao encontrado");

        this.pedidoRepository.delete(pedido);

        return "Pedido deletado com sucesso";
    }

    public PedidoDTO findById(Long id){

        Pedido pedidoBanco = pedidoRepository.findById(id).orElse(null);

        return toPedidoDTO(pedidoBanco);
    }

    public List<PedidoDTO> findAllPedido(){
        List<Pedido> pedidosBanco = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();

        for(int i = 0; i < pedidosBanco.size(); i++){
            pedidoDTOList.add(toPedidoDTO(pedidosBanco.get(i)));
        }

        return pedidoDTOList;
    }

    public PedidoDTO toPedidoDTO(Pedido pedido){
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setNome(pedido.getNome());
        pedidoDTO.setObs(pedido.getObs());
        pedidoDTO.setIdUsuario(pedido.getIdUsuario());
        pedidoDTO.setEstado(pedido.getEstado());

        return pedidoDTO;
    }

    public Pedido toPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();

        pedido.setId(pedidoDTO.getId());
        pedido.setNome(pedidoDTO.getNome());
        pedido.setObs(pedidoDTO.getObs());
        pedido.setIdUsuario(pedidoDTO.getIdUsuario());
        pedido.setEstado(pedidoDTO.getEstado());

        return pedido;
    }
}