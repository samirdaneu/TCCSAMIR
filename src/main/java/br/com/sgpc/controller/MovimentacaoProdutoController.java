package br.com.sgpc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.stereotype.Controller;

import br.com.sgpc.model.MovimentacaoProduto;
import br.com.sgpc.model.Produto;
import br.com.sgpc.model.Usuario;
import br.com.sgpc.service.MessageBundleService;
import br.com.sgpc.service.MovimentacaoProdutoService;
import br.com.sgpc.service.ProdutoService;
import br.com.sgpc.service.UsuarioService;
import br.com.sgpc.util.EnviaEmail;
import br.com.sgpc.util.FacesUtil;

@Controller(value = "movimentacaoProdutoController")
@RequestScoped
public class MovimentacaoProdutoController implements AlphaController {

	private static final long serialVersionUID = -4751958106939130845L;
	
	private static final String ENTRADA = "Entrada";
	private static final String SAIDA = "Sa�da";
	
	private Usuario usuario;
	
	private boolean enviarEmail = false;
	
	private String descricaoProduto;

	private String tipoMovimentacaoSelecionado;

	private List<String> listaTipoMovimentacao;

	private Produto produto;

	private MovimentacaoProduto movimentacaoProduto;

	@Resource(name = "produtoService")
	private ProdutoService produtoService;

	@Resource(name = "movimentacaoProdutoService")
	private MovimentacaoProdutoService movimentacaoProdutoService;
	
	@Resource(name = "usuarioService")
	private UsuarioService usuarioService;

	@Resource(name = "messageBundleService")
	private MessageBundleService messageBundleService;

	private DataModel<MovimentacaoProduto> model;

	@Override
	@PostConstruct
	public void inicio() {
		movimentacaoProduto = new MovimentacaoProduto();
		produto = new Produto();
		setModel(listarMovimentacoes());
		setListaTipoMovimentacao(new ArrayList<String>());
		getListaTipoMovimentacao().add(ENTRADA);
		getListaTipoMovimentacao().add(SAIDA);

	}

	public List<String> buscarProdutos(final String query) {
		final List<Produto> produtos = produtoService
				.buscarParcialPorDescricao(query);
		final List<String> descricoes = new ArrayList<String>();

		for (Produto produto : produtos) {
			descricoes.add(produto.getDescricao());
		}

		return descricoes;
	}

	public DataModel<MovimentacaoProduto> listarMovimentacoes() {
		setModel(new ListDataModel<MovimentacaoProduto>(
				this.movimentacaoProdutoService.buscarTodos()));
		return getModel();
	}
	
	private boolean validaQuantidadeSaida(String tipoMovimentacao, int quantidadeProduto, int quantidadeMovimentacao){
		boolean retorno = false; 
		if(tipoMovimentacaoSelecionado.equals(SAIDA) && (getProduto().getQuantidade() - movimentacaoProduto.getQuantidade() < 0)){
			retorno = true;
		}
		 return retorno;
	}
	
	private boolean verificaSeQuantidadeLimiteUltrapassou(){
		boolean retorno = false;
		if((getProduto().getQuantidade() - movimentacaoProduto.getQuantidade()) <= getProduto().getQuantidadeLimite()){
			retorno = true;
		}		
		return retorno;
	}
	
	public String salvarMovimentacao() {
		try {
			
			setProduto(produtoService.buscarUnicoPorDescricao(descricaoProduto));
			
			if(!validaQuantidadeSaida(tipoMovimentacaoSelecionado, getProduto().getQuantidade(), movimentacaoProduto.getQuantidade())){			

			if (tipoMovimentacaoSelecionado.equals(ENTRADA)) {
				movimentacaoProduto
						.setTipoMovimentacao((MovimentacaoProduto.TipoMovimentacao.ENTRADA));
			} else {
				movimentacaoProduto
						.setTipoMovimentacao((MovimentacaoProduto.TipoMovimentacao.SAIDA));
				setEnviarEmail(verificaSeQuantidadeLimiteUltrapassou());
			}

			movimentacaoProduto.setDataMovimentacao(new Date());
			getProduto().setQuantidade(produto.atualizarQuantidadePorMovimentacao(
					movimentacaoProduto.getQuantidade(),
					produto.getQuantidade(),
					movimentacaoProduto.getTipoMovimentacao()));
			if(enviarEmail){
				new EnviaEmail(usuarioService.buscarAdministradoresAtivos(), getProduto());
			}
			produtoService.atualizar(getProduto());
			movimentacaoProduto.setProduto(getProduto());
			movimentacaoProdutoService.salvar(movimentacaoProduto);
						
			FacesUtil.mensagemInformacao(messageBundleService
							.recoveryMessage("movimentacao_produto_cadastrado_sucesso"));
			} else {
				FacesUtil.mensagemErro(messageBundleService
						.recoveryMessage("movimentacao_saida_erro"));
			}

		} catch (Exception e) {
			FacesUtil.mensagemErro(messageBundleService
					.recoveryMessage("movimentacao_produto_salvar_erro"));
			e.printStackTrace();
		}

		return "ok";
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public MovimentacaoProduto getMovimentacaoProduto() {
		return movimentacaoProduto;
	}

	public void setMovimentacaoProduto(MovimentacaoProduto movimentacaoProduto) {
		this.movimentacaoProduto = movimentacaoProduto;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public MovimentacaoProdutoService getMovimentacaoProdutoService() {
		return movimentacaoProdutoService;
	}

	public void setMovimentacaoProdutoService(
			MovimentacaoProdutoService movimentacaoProdutoService) {
		this.movimentacaoProdutoService = movimentacaoProdutoService;
	}

	public void setModel(DataModel<MovimentacaoProduto> model) {
		this.model = model;
	}

	public DataModel<MovimentacaoProduto> getModel() {
		return model;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setTipoMovimentacaoSelecionado(
			String tipoMovimentacaoSelecionado) {
		this.tipoMovimentacaoSelecionado = tipoMovimentacaoSelecionado;
	}

	public String getTipoMovimentacaoSelecionado() {
		return tipoMovimentacaoSelecionado;
	}

	public void setListaTipoMovimentacao(List<String> listaTipoMovimentacao) {
		this.listaTipoMovimentacao = listaTipoMovimentacao;
	}

	public List<String> getListaTipoMovimentacao() {
		return listaTipoMovimentacao;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setEnviarEmail(boolean enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

	public boolean isEnviarEmail() {
		return enviarEmail;
	}

}
