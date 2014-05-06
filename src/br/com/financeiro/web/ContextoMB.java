package br.com.financeiro.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Usuario;
import br.com.financeiro.web.rn.ContaRN;
import br.com.financeiro.web.rn.UsuarioRN;

@ManagedBean(name="contextoMB")
@SessionScoped
public class ContextoMB implements Serializable{
	
	private static final long serialVersionUID = -7722786550846564382L;

	private Usuario usuarioLogado = null;
	
	private Conta contaAtiva = null;
	
	public Usuario getUsuarioLogado(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if(this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())){
			if(login != null){
				UsuarioRN usuarioRN = new UsuarioRN();
				this.usuarioLogado = usuarioRN.buscarPorLogin(login);
				this.contaAtiva = null;
			}
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuario){
		this.usuarioLogado = usuario;
	}
	
	public Conta getContaAtiva(){
		if(this.contaAtiva == null){
			Usuario usuario = this.getUsuarioLogado();
			ContaRN contaRN = new ContaRN();
			this.contaAtiva = contaRN.buscarFavorita(usuario);
			if(this.contaAtiva == null){
				List<Conta> contas = contaRN.listar(usuario);
				for (Conta conta : contas) {
					this.contaAtiva = conta;
					break;
				}
			}
		}
		return this.contaAtiva;
	}
	
	public void setContaAtiva(ValueChangeEvent event){
		Integer conta = (Integer) event.getNewValue();
		ContaRN contaRN = new ContaRN();
		this.contaAtiva = contaRN.carregar(conta);
	}
}
