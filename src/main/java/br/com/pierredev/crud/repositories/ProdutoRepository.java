package br.com.pierredev.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pierredev.crud.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
