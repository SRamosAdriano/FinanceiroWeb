package br.com.financeiro.web.dao.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.financeiro.web.dao.LancamentoDAO;
import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Lancamento;

public class LancamentoDAOHibernate implements LancamentoDAO {

	private Session session;
	
	@Override
	public void setSession(Session session){
		this.session = session;
	}
	
	@Override
	public void salvar(Lancamento lancamento) {
		this.session.saveOrUpdate(lancamento);
	}

	@Override
	public void excluir(Lancamento lancamento) {
		this.session.delete(lancamento);
	}

	@Override
	public Lancamento carregar(Integer lancamento) {
		return (Lancamento) this.session.get(Lancamento.class, lancamento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) {
		Criteria criteria = this.session.createCriteria(Lancamento.class);
		
		if(dataInicio != null && dataFim != null){
			criteria.add(Restrictions.between("data", dataInicio, dataFim));
		}else if(dataInicio != null){
			criteria.add(Restrictions.ge("data", dataInicio));
		}else if(dataFim != null){
			criteria.add(Restrictions.le("data", dataFim));
		}
		
		criteria.add(Restrictions.eq("conta", conta));
		criteria.addOrder(Order.asc("data"));
		
		return criteria.list();
	}

	@Override
	public float saldo(Conta conta, Date data) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(l.valor * c.fator) ");
		sql.append(" from LANCAMENTO l, ");
		sql.append("    CATEGORIA c ");
		sql.append(" where l.categoria = c.codigo ");
		sql.append(" and l.conta = :conta ");
		sql.append(" and l.data = :data ");
		SQLQuery query = this.session.createSQLQuery(sql.toString());
		query.setParameter("conta", conta);
		query.setParameter("data", data);
		BigDecimal saldo = (BigDecimal) query.uniqueResult();
		if(saldo != null){
			return saldo.floatValue();
		}
		return 0f;
	}

}
