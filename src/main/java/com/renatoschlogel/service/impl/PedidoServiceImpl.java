package com.renatoschlogel.service.impl;

import com.renatoschlogel.api.rest.dto.ItemPedidoDTO;
import com.renatoschlogel.api.rest.dto.PedidoDTO;
import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.entity.ItemPedido;
import com.renatoschlogel.domain.entity.Pedido;
import com.renatoschlogel.domain.entity.Produto;
import com.renatoschlogel.domain.repository.ClienteRepository;
import com.renatoschlogel.domain.repository.ItemPedidoRepository;
import com.renatoschlogel.domain.repository.PedidoRepository;
import com.renatoschlogel.domain.repository.ProdutoRepository;
import com.renatoschlogel.exception.RegraNegocioException;
import com.renatoschlogel.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Pedido salvar(PedidoDTO pedidoDTO) {

        Pedido pedido = new Pedido();
        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setData(LocalDate.now());

        Cliente cliente = clienteRepository.findById(pedidoDTO.getIdCliente())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado!"));

        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = convertItens(pedido, pedidoDTO.getItens());
        pedido = pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
       // return pedidoRepository.findByIdFetchItens(id);
        return Optional.empty();
    }

    private List<ItemPedido> convertItens(Pedido pedido, List<ItemPedidoDTO> itensPedidoDTO) {
        if (itensPedidoDTO.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem itens!");
        }

        return itensPedidoDTO
                .stream()
                .map(dto -> {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setPedido(pedido);
                    itemPedido.setQuantidade(dto.getQuantidade());
                    Produto produto = produtoRepository.findById(dto.getIdProduto())
                            .orElseThrow(() -> new RegraNegocioException("Produto não encontrado!"));
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}






