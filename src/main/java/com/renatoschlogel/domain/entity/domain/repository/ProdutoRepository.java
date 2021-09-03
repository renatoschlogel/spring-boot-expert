package com.renatoschlogel.domain.entity.domain.repository;

import com.renatoschlogel.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
