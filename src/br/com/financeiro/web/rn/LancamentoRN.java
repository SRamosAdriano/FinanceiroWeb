package br.com.financeiro.web.rn;

import java.util.Date;
import java.util.List;

import br.com.financeiro.util.DAOFactory;
import br.com.financeiro.web.dao.LancamentoDAO;
import br.com.financeiro.web.entity.Conta;
import br.com.financeiro.web.entity.Lancamento;

public class LancamentoRN {

	private LancamentoDAO lancamentoDAO;
	
	public LancamentoRN(){
		this.lancamentoDAO = DAOFactory.criarLancamentoDAO();
	}
	
	public void salvar(Lancamento lancamento){
		this.lancamentoDAO.salvar(lancamento);
	}
	
	public void excluir(Lancamento lancamento){
		this.lancamentoDAO.excluir(lancamento);
	}
	
	public Lancamento carregar(Integer lancamento){
		return this.lancamentoDAO.carregar(lancamento);
	}
	
	public float saldo(Conta conta, Date data){
		float saldoInicial = conta.getSaldoInicial();
		float saldoNaData = this.lancamentoDAO.saldo(conta, data);
		return saldoInicial + saldoNaData;
	}
	
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim){
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim);
	}
	
	
}
