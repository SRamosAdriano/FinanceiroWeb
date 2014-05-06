package br.com.financeiro.web.dao;

import java.util.List;

import br.com.financeiro.web.entity.Categoria;
import br.com.financeiro.web.entity.Usuario;

public interface CategoriaDAO extends AbstractDAO{

	public void excluir(Categoria categoria);

	public Categoria salvar(Categoria categoria);
	
	public Categoria carregar(Integer categoria);
	
	public List<Categoria> listar(Usuario usuario);
}
