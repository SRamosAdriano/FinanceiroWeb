package br.com.financeiro.web.dao;

import java.util.Date;
import java.util.List;

import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Lancamento;

public interface LancamentoDAO extends AbstractDAO{

	public void salvar(Lancamento lancamento);
	
	public void excluir(Lancamento lancamento);
	
	public Lancamento carregar(Integer lancamento);
	
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim);
	
	public float saldo(Conta conta, Date data);
}
