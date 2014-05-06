package br.com.financeiro.web.dao;

import org.hibernate.Session;

public abstract interface AbstractDAO {
	
	public void setSession(Session session);
}
