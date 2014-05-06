package br.com.financeiro.web.dao;

import java.util.List;

import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Usuario;

public interface ContaDAO extends AbstractDAO{

	public void salvar(Conta conta);
	
	public void excluir(Conta conta);
	
	public Conta carregar(Integer conta);
	
	public Conta buscarFavorita(Usuario usuario);

	public List<Conta> listar(Usuario usuario);
}
