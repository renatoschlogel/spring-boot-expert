package com.renatoschlogel.domain.repository;

import com.renatoschlogel.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    @Query(value = " select c from Cliente c" +
                   "  where c.nome like :nome")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query(value = " select * from Cliente c" +
                   "  where c.nome like '%:nome%'", nativeQuery = true)
    List<Cliente> encontrarPorNomeNativeQuery(@Param("nome") String nome);

    boolean existsByNome(String nome);

}
