package br.com.pierredev.crud.services;

import java.util.Optional;

import br.com.pierredev.crud.message.ProdutoSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pierredev.crud.data.vo.ProdutoVO;
import br.com.pierredev.crud.entity.Produto;
import br.com.pierredev.crud.exception.ResourceNotFoundException;
import br.com.pierredev.crud.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	private final ProdutoSendMessage produtoSendMessage;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
		this.produtoRepository = produtoRepository;
		this.produtoSendMessage = produtoSendMessage;
	}
	
	public ProdutoVO create(ProdutoVO produtoV0) {
		ProdutoVO proVoRetorno = ProdutoVO.create(produtoRepository.save(Produto.create(produtoV0)));
		produtoSendMessage.sendMessage(produtoV0);
		return proVoRetorno;
	}
	
	public Page<ProdutoVO> findAll(Pageable pageble) {
		var page = produtoRepository.findAll(pageble);
		return page.map(this::convertToProdutov0);
		
	}

	private ProdutoVO convertToProdutov0(Produto produto) {
		return ProdutoVO.create(produto);
	}
	
	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no records found for this Id"));
		return ProdutoVO.create(entity);		
	}
	
	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> optionalProduto = produtoRepository.findById(produtoVO.getId());
		
		if(!optionalProduto.isPresent()) {
			new ResourceNotFoundException("No records found for this ID");
		}
		
		return ProdutoVO.create(produtoRepository.save(Produto.create(produtoVO)));
	}
	
	public void delete(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no records found for this Id"));
		produtoRepository.delete(entity);	
	}
}


