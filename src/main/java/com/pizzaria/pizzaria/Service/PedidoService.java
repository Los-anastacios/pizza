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
        //fazer verificacoes
        Pedido pedido = this.pedidoRepository.save(toPedido(pedidoDTO));

        return toPedidoDTO(pedido);
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

    public String editar(Long id, PedidoDTO pedidoDTO){
        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

        Assert.isTrue(pedido != null, "Pedido nao encontrado");
        //fazer verificacoes

        this.pedidoRepository.save(toPedido(pedidoDTO));

        return pedidoDTO.getNome() + " editado";
    }

    public String deletar(Long id){
        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);

        Assert.isTrue(pedido != null, "Pedido nao encontrado");

        this.pedidoRepository.delete(pedido);

        return "Pedido deletado";
    }

    public PedidoDTO toPedidoDTO(Pedido pedido){
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setNome(pedido.getNome());
        pedidoDTO.setObs(pedido.getObs());
        pedidoDTO.setUsuario(pedido.getUsuario());

        return pedidoDTO;
    }

    public Pedido toPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();

        pedido.setNome(pedidoDTO.getNome());
        pedido.setObs(pedidoDTO.getObs());
        pedido.setUsuario(pedidoDTO.getUsuario());

        return pedido;
    }
}