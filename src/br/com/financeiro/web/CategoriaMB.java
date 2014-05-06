package br.com.financeiro.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.financeiro.web.entity.Categoria;
import br.com.financeiro.web.rn.CategoriaRN;
import br.com.financeiro.web.util.ContextoUtil;

@ManagedBean(name="categoriaMB")
@RequestScoped
public class CategoriaMB implements Serializable{
	
	private static final long serialVersionUID = 6792160146670689866L;

	private TreeNode categoriasTree;
	
	private Categoria editada = new Categoria();
	
	private List<SelectItem> categoriasSelect;
	
	private boolean mostraEdicao = false;
	
	public void novo(){
		Categoria pai = null;
		if(this.editada.getCodigo() != null){
			CategoriaRN categoriaRN = new CategoriaRN();
			pai = categoriaRN.carregar(this.editada.getCodigo());
		}
		this.editada = new Categoria();
		this.editada.setPai(pai);
		this.mostraEdicao = true;
	}
	
	public void selecionar(NodeSelectEvent event){
		this.editada = (Categoria) event.getTreeNode().getData();
		Categoria pai = this.editada.getPai();
		if(this.editada != null && pai != null && pai.getCodigo() != null){
			this.mostraEdicao = true;
		}else{
			this.mostraEdicao = false;
		}
	}
	
	public void salvar(){
		ContextoMB contextoMB = ContextoUtil.getContextoMB();
		CategoriaRN categoriaRN = new CategoriaRN();
		this.editada.setUsuario(contextoMB.getUsuarioLogado());
		categoriaRN.salvar(this.editada);
		
		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
	}
	
	public void excluir(){
		CategoriaRN categoriaRN = new CategoriaRN();
		categoriaRN.excluir(this.editada);
		
		this.editada = null;
		this.mostraEdicao = false;
		this.categoriasTree = null;
		this.categoriasSelect = null;
	}
	
	public TreeNode getCategoriasTree() {
		if(this.categoriasTree == null){
			ContextoMB contextoMB = ContextoUtil.getContextoMB();
			CategoriaRN categoriaRN = new CategoriaRN();
			List<Categoria> categorias = categoriaRN.listar(contextoMB.getUsuarioLogado());
			this.categoriasTree = new DefaultTreeNode(null, null);
			this.montaDadosTree(this.categoriasTree, categorias);
		}
		return categoriasTree;
	}
	
	private void montaDadosTree(TreeNode pai, List<Categoria> lista){
		if(lista != null && lista.size() > 0){
			TreeNode filho = null;
			for (Categoria categoria : lista) {
				filho = new DefaultTreeNode(categoria, pai);
				this.montaDadosTree(filho, categoria.getFilhos());
			}
		}
	}

	public List<SelectItem> getCategoriasSelect() {
		if(this.categoriasSelect == null){
			this.categoriasSelect = new ArrayList<SelectItem>();
			ContextoMB contextoMB = ContextoUtil.getContextoMB();
			CategoriaRN categoriaRN = new CategoriaRN();
			List<Categoria> categorias = categoriaRN.listar(contextoMB.getUsuarioLogado());
			this.montaDadosSelect(this.categoriasSelect, categorias, "");
		}
		return categoriasSelect;
	}
	
	private void montaDadosSelect(List<SelectItem> select, List<Categoria> categorias, String prefixo){
		SelectItem item = null;
		if(categorias != null){
			for (Categoria categoria : categorias) {
				item = new SelectItem(categoria, prefixo + categoria.getDescricao());
				item.setEscape(false);
				select.add(item);
				this.montaDadosSelect(select, categoria.getFilhos(), prefixo + "&nbsp;&nbsp;");
			}
		}
	}
	
	public void setCategoriasSelect(List<SelectItem> categoriasSelect) {
		this.categoriasSelect = categoriasSelect;
	}
	
	public void setCategoriasTree(TreeNode categoriasTree) {
		this.categoriasTree = categoriasTree;
	}

	public Categoria getEditada() {
		return editada;
	}

	public void setEditada(Categoria editada) {
		this.editada = editada;
	}

	public boolean isMostraEdicao() {
		return mostraEdicao;
	}

	public void setMostraEdicao(boolean mostraEdicao) {
		this.mostraEdicao = mostraEdicao;
	}
}
