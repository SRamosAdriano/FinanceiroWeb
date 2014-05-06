package br.com.financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.financeiro.web.entity.Categoria;
import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Lancamento;
import br.com.financeiro.web.rn.LancamentoRN;
import br.com.financeiro.web.util.ContextoUtil;

@ManagedBean(name="lancamentoMB")
@ViewScoped
public class LancamentoMB implements Serializable{

	private static final long serialVersionUID = -6403125567073141029L;
	
	private List<Lancamento> lista;
	
	private List<Double> saldos = new ArrayList<Double>();
	
	private float saldoGeral;
	
	private Lancamento editado = new Lancamento();
	
	public LancamentoMB(){
		this.novo();
	}
	
	public void novo(){
		this.editado = new Lancamento();
		this.editado.setData(new Date(System.currentTimeMillis()));
	}
	
	public void editar(){
		
	}
	
	public void salvar(){
		ContextoMB contextoMB = ContextoUtil.getContextoMB();
		this.editado.setUsuario(contextoMB.getUsuarioLogado());
		this.editado.setConta(contextoMB.getContaAtiva());
		
		LancamentoRN lancamentoRN = new LancamentoRN();
		lancamentoRN.salvar(this.editado);
		
		this.novo();
		this.lista = null;
	}
	
	public void excluir(){
		LancamentoRN lancamentoRN = new LancamentoRN();
		this.editado = lancamentoRN.carregar(this.editado.getLancamento());
		lancamentoRN.excluir(this.editado);
		
		this.lista = null;
	}
	
	public List<Lancamento> getLista(){
		if(this.lista == null){
			ContextoMB contextoMB = ContextoUtil.getContextoMB();
			Conta conta = contextoMB.getContaAtiva();
			
			Calendar dataSaldo = Calendar.getInstance();
			dataSaldo.add(Calendar.MONTH, -1);
			dataSaldo.add(Calendar.DAY_OF_MONTH, -1);
			
			Calendar inicio = Calendar.getInstance();
			inicio.add(Calendar.MONTH, -1);
			
			LancamentoRN lancamentoRN = new LancamentoRN();
			this.saldoGeral = lancamentoRN.saldo(conta, dataSaldo.getTime());
			this.lista = lancamentoRN.listar(conta, inicio.getTime(), null);
			
			Categoria categoria = null;
			double saldo = this.saldoGeral;
			for (Lancamento lancamento : this.lista) {
				categoria = lancamento.getCategoria();
				saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFator());
				this.saldos.add(saldo);
			}
		}
		return this.lista;
	}
	
	public Lancamento getEditado(){
		return this.editado;
	}
	
	public void setEditado(Lancamento editado){
		this.editado = editado;
	}
	
	public List<Double> getSaldos(){
		return this.saldos;
	}
	
	public void setSaldos(List<Double> saldos){
		this.saldos = saldos;
	}
}
