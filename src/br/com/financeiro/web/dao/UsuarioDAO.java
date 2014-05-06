package br.com.financeiro.web.dao;

import java.util.List;

import br.com.financeiro.web.entity.Usuario;

public interface UsuarioDAO extends AbstractDAO {
	
	public void salvar(Usuario usuario);
	
	public void atualizar(Usuario usuario);
	
	public void excluir(Usuario usuario);
	
	public Usuario carregar(Integer codigo);
	
	public Usuario buscarPorLogin(String login);
	
	public List<Usuario> listar();
}
