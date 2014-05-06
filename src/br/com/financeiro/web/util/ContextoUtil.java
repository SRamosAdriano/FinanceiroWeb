package br.com.financeiro.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.financeiro.web.ContextoMB;

public class ContextoUtil {

	public static ContextoMB getContextoMB(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ContextoMB contextoMB = (ContextoMB) session.getAttribute("contextoMB");
		return contextoMB;
	}
}
