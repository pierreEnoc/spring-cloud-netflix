package br.com.pierredev.crud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pierredev.crud.data.vo.ProdutoV0;
import br.com.pierredev.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		super();
		this.produtoRepository = produtoRepository;
	}
	
	public ProdutoV0 create(ProdutoV0 produtoV0) {
		return null;
	}

}
