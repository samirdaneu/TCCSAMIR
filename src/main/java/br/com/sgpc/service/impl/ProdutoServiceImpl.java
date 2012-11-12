package br.com.sgpc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.sgpc.dao.ProdutoDao;
import br.com.sgpc.dao.impl.GenericDaoImpl;
import br.com.sgpc.model.Produto;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.ProdutoService;

@Service( value = "produtoService" )
public class ProdutoServiceImpl extends GenericDaoImpl<Produto, Integer> implements ProdutoService {

	private static final long serialVersionUID = 5135114915374432327L;
	
	@Resource( name = "messageBundleService" )
	private MessageBundleService messageBundleService;
	
	@Resource( name = "produtoDao" )
	private ProdutoDao produtoDao;
	
	@Override
	public List<Produto> buscarParcialPorDescricao(final String descricao) {
		
		if(descricao == null) {
			throw new IllegalArgumentException( messageBundleService.recoveryMessage("descricao_nula", 1) );
		}
		
		return produtoDao.buscarParcialPorDescricao( descricao );
	}

	public void setMessageBundleService(MessageBundleService messageBundleService) {
		this.messageBundleService = messageBundleService;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}

	@Override
	public Produto buscarUnicoPorDescricao(String descricao) {
		
		if(descricao == null) {
			throw new IllegalArgumentException( messageBundleService.recoveryMessage("descricao_nula", 1) );
		}
		
		return produtoDao.buscarUnicoPorDescricao(descricao);
	}
}
