package br.com.sgpc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Pedido;
import br.com.sgpc.service.PedidoService;

@Controller(value = "relatorioController")
@Scope("request")
public class RelatorioController implements AlphaController{
	
	private static final long serialVersionUID = 298834463696787312L;
	
	private Date dataInicio = null;
	
	private Date dataTermino = null;
	
	private List<Pedido> pedidos;

	@Resource(name = "pedidoService")
	private PedidoService pedidoService;
	
	private BigDecimal valorTotal = new BigDecimal("0.00");
	
	private DataModel<Pedido> model;
	
	@Override
	@PostConstruct
	public void inicio() {
		this.pedidos = new ArrayList<Pedido>();				
	}	
	
	private BigDecimal calculaValorTotal(List<Pedido> pedidos){
		for(Pedido pedido : pedidos){
			this.valorTotal.add(pedido.getValorTotal());
		}		
		return this.valorTotal;
	}
	
	public String buscarVendas(){
		this.setModel(new ListDataModel<Pedido>(this.pedidoService.buscarRelatorioVendas(getDataInicio(), getDataTermino())));
		calculaValorTotal(this.pedidos);
		return "ok";
	}
	
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public PedidoService getPedidoService() {
		getPedidos().get(0).getItens().size();
		return pedidoService;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setModel(DataModel<Pedido> model) {
		this.model = model;
	}

	public DataModel<Pedido> getModel() {
		return model;
	}

}
