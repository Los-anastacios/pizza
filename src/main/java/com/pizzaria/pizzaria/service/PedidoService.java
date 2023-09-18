package com.pizzaria.pizzaria.service;

import com.pizzaria.pizzaria.dto.PedidoDTO;
import com.pizzaria.pizzaria.repository.PedidoRepository;
import com.pizzaria.pizzaria.entity.Pedido;
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


        Pedido pedido= toPedido(pedidoDTO);
        this.pedidoRepository.save(pedido);

        return toPedidoDTO(pedido);
    }

    public String editar(Long id, PedidoDTO pedidoDTO){

        Pedido pedidoBanco = this.pedidoRepository.findById(id).orElse(null);
        Assert.isTrue(pedidoBanco != null,"pedido nao encontrado");

        this.pedidoRepository.save(toPedido(pedidoDTO));

        return "editado com sucesso";
    }

    public String deletar(Long id){

        Pedido pedido = this.pedidoRepository.findById(id).orElse(null);
        Assert.isTrue(pedido!=null,"id nao encontrado");

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
        pedidoDTO.setObs(pedido.getObs());
        pedidoDTO.setIdCliente(pedido.getIdCliente());
        pedidoDTO.setEstado(pedido.getEstado());

        return pedidoDTO;
    }

    public Pedido toPedido(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido();

        pedido.setId(pedidoDTO.getId());
        pedido.setObs(pedidoDTO.getObs());
        pedido.setIdCliente(pedidoDTO.getIdCliente());
        pedido.setEstado(pedidoDTO.getEstado());

        return pedido;
    }
}