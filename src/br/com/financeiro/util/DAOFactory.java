package br.com.financeiro.util;

import br.com.financeiro.web.dao.CategoriaDAO;
import br.com.financeiro.web.dao.ContaDAO;
import br.com.financeiro.web.dao.LancamentoDAO;
import br.com.financeiro.web.dao.UsuarioDAO;
import br.com.financeiro.web.dao.hibernate.CategoriaDAOHibernate;
import br.com.financeiro.web.dao.hibernate.ContaDAOHibernate;
import br.com.financeiro.web.dao.hibernate.LancamentoDAOHibernate;
import br.com.financeiro.web.dao.hibernate.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static ContaDAO criarContaDAO(){
		ContaDAOHibernate contaDAO = new ContaDAOHibernate();
		contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return contaDAO;
	}
	
	public static CategoriaDAO criarCategoriaDAO(){
		CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
		categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return categoriaDAO;
	}

	public static LancamentoDAO criarLancamentoDAO(){
		LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
		lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return lancamentoDAO;
	}
}
