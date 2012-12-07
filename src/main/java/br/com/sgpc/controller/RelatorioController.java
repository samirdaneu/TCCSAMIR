package br.com.sgpc.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.sgpc.model.Pedido;
import br.com.sgpc.service.PedidoService;

@Controller(value = "relatorioController")
@Scope("request")
public class RelatorioController /*implements AlphaController*/{
	
	private static final long serialVersionUID = 298834463696787312L;
	
	private Date dataInicio = null;
	
	private Date dataTermino = null;
	
	@Resource(name = "pedidoService")
	private PedidoService pedidoService;
	
	private BigDecimal valorTotal = new BigDecimal("0.00");
	
	private DataModel<Pedido> model;
		
	public BigDecimal calculaValorTotal(DataModel<Pedido> model){
		for(Pedido pedido : model){
			this.valorTotal = this.valorTotal.add(pedido.getValorTotal());
		}		
		return this.valorTotal;
	}
	
	public void buscarVendas(){
		this.setModel(new ListDataModel<Pedido>(this.pedidoService.buscarRelatorioVendas(getDataInicio(), getDataTermino())));
		setValorTotal(calculaValorTotal(this.model));		
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

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataInicio() {
		return dataInicio;
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
