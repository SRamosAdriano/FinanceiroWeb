package br.com.financeiro.web.rn;

import java.util.List;

import br.com.financeiro.util.DAOFactory;
import br.com.financeiro.web.dao.UsuarioDAO;
import br.com.financeiro.web.entity.Usuario;

public class UsuarioRN {

	 private UsuarioDAO usuarioDAO;
	 
	 public UsuarioRN(){
		 this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	 }
	 
	 public Usuario carregar(Integer codigo){
		 return this.usuarioDAO.carregar(codigo);
	 }
	 
	 public Usuario buscarPorLogin(String login){
		 return this.usuarioDAO.buscarPorLogin(login);
	 }
	 
	 public void salvar(Usuario usuario){
		 Integer codigo = usuario.getCodigo();
		 if(codigo == null || codigo == 0){
			 usuario.getPermissao().add("ROLE_USUARIO");
			 this.usuarioDAO.salvar(usuario);
			 
			 CategoriaRN categoriaRN = new CategoriaRN();
			 categoriaRN.salvarEstruturaPadrao(usuario);
		 }else{
			 this.usuarioDAO.atualizar(usuario);
		 }
	 }
	 
	 public void excluir(Usuario usuario){
		 CategoriaRN categoriaRN = new CategoriaRN();
		 categoriaRN.excluir(usuario);
		 
		 this.usuarioDAO.excluir(usuario);
	 }
	 
	 public List<Usuario> listar(){
		 return this.usuarioDAO.listar();
	 }
}
