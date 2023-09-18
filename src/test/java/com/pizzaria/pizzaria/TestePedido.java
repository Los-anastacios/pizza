package com.pizzaria.pizzaria;

import com.pizzaria.pizzaria.controller.PedidoController;
import com.pizzaria.pizzaria.dto.PedidoDTO;
import com.pizzaria.pizzaria.entity.Cliente;
import com.pizzaria.pizzaria.entity.Funcionario;
import com.pizzaria.pizzaria.entity.Item;
import com.pizzaria.pizzaria.entity.Pedido;
import com.pizzaria.pizzaria.entity.enums.Estado;
import com.pizzaria.pizzaria.repository.PedidoRepository;
import com.pizzaria.pizzaria.service.PedidoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TestePedido {

    @MockBean
    PedidoRepository pedidoRepository;
    @Autowired
    PedidoController pedidoController;
    @Autowired
    PedidoService pedidoService;

    @BeforeEach
    void injectData(){

        List<Item> items = new ArrayList<>();
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();

        Pedido pedido = new Pedido(1L,"obs",cliente,funcionario,false, Estado.ANDAMENTO,items);

        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
    }

    @Test
    void cadastrarTeste(){

        List<Item> items = new ArrayList<>();
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();

        PedidoDTO pedidoDTO = new PedidoDTO(1L,"obs",cliente,funcionario,false,Estado.ANDAMENTO);

        var data = pedidoService.cadastrar(pedidoDTO);
        Assert.assertEquals("obs",data.getObs());
    }

    @Test
    void editarTeste(){

        List<Item> items = new ArrayList<>();
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();

        var pedido = pedidoController.editar(1L, new PedidoDTO(1L,"obs",cliente,funcionario,false,Estado.ANDAMENTO));
        Assert.assertEquals("editado com sucessoAlterado com sucesso", pedido.getBody());
    }

    @Test
    void  deletarTeste(){

        String data =pedidoService.deletar(1L);
        Assert.assertEquals("Pedido deletado com sucesso", data);
    }

    @Test
    void findIdTeste(){

        List<Item> items = new ArrayList<>();
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();
        pedidoController.cadastrar(new PedidoDTO(1L,"obs",cliente,funcionario,false,Estado.ANDAMENTO));
        var pedido = pedidoController.findById(1L);
        Assert.assertEquals(pedido.getBody().getObs(), pedidoController.findById(1L).getBody().getObs());
    }
}
