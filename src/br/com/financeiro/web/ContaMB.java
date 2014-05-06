package br.com.financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.rn.ContaRN;
import br.com.financeiro.web.util.ContextoUtil;

@ManagedBean(name="contaMB")
@RequestScoped
public class ContaMB implements Serializable{
	
	private static final long serialVersionUID = 5279074781321568693L;
	
	private Conta selecionada = new Conta();
	
	private List<Conta> lista = null;
	
	public void salvar(){
		ContextoMB contextoMB = ContextoUtil.getContextoMB();
		this.selecionada.setUsuario(contextoMB.getUsuarioLogado());
		
		ContaRN contaRN = new ContaRN();
		contaRN.salvar(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
	}
	
	public void excluir(){
		ContaRN contaRN = new ContaRN();
		contaRN.excluir(this.selecionada);
		this.selecionada = new Conta();
		this.lista = null;
		
	}
	
	public void tornarfavorita(){
		ContaRN contaRN = new ContaRN();
		contaRN.tornarFavorita(this.selecionada);
		this.selecionada = new Conta();
	}

	public List<Conta> getLista() {
		if(this.lista == null){
			ContextoMB contextoMB = ContextoUtil.getContextoMB();
			
			ContaRN contaRN = new ContaRN();
			this.lista = contaRN.listar(contextoMB.getUsuarioLogado());
		}
		return this.lista;
	}

	public Conta getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Conta selecionada) {
		this.selecionada = selecionada;
	}
}
