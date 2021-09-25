package com.renatoschlogel.domain.repository;

import com.renatoschlogel.domain.entity.Cliente;
import com.renatoschlogel.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    Optional<Pedido> findFetchItemsById(Integer id);
}
