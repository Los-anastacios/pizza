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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class TestePedido {

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

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        Mockito.when(pedidoRepository.save(pedido)).thenReturn(pedido);
        Mockito.when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        Mockito.when(pedidoRepository.findAll()).thenReturn(pedidos);
    }

    @Test
    void cadastrarTeste(){

        List<Item> items = new ArrayList<>();
        Cliente cliente = new Cliente();
        Funcionario funcionario = new Funcionario();

        PedidoDTO pedidoDTO = new PedidoDTO(1L,"obs",cliente,funcionario,false,Estado.ANDAMENTO);

        var data = pedidoController.cadastrar(pedidoDTO);
        Assert.assertEquals("Cadastraco com sucesso obs",data.getBody());
    }

    @Test
    void errorCadastrarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()-> pedidoController.cadastrar(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
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
    void errorEditarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class, ()-> pedidoController.editar(null,null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
    }

    @Test
    void  deletarTeste(){

        var data =pedidoController.deleta(1L);
        Assert.assertEquals("Deletado com sucesso", data.getBody());
    }

    @Test
    void errorDeletarTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->pedidoController.deleta(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
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

    @Test
    void erroFindIdTeste(){
        final ResponseStatusException e = Assertions.assertThrows(ResponseStatusException.class,()->pedidoController.findById(null));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST,e.getStatusCode());
    }

    @Test
    void findAllTeste(){
        List<PedidoDTO> pedidoDTOS = pedidoController.findAllPedido().getBody();
        Assert.assertEquals(HttpStatus.OK,pedidoController.findAllPedido().getStatusCode());
        Assert.assertEquals(1,pedidoDTOS.size());
    }
}
