package br.edu.uffs.thaila.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.uffs.thaila.dao.UsuarioDAO;
import br.edu.uffs.thaila.model.thaila;

//@Model equivale a essas duas anotações
@RequestScoped
//Torna classe disponível na camada de visão (html)
@Named
public class UsuarioController {

	@Inject
	//Mensagens de erro para o usuário
    private FacesContext facesContext;

    @Inject
    private UsuarioDAO usuarioDAO;

    private thaila novoUsuario;
    
    private List<thaila> listaUsuarios;

    //Executa após instanciar classe UsuarioController, ou seja, 
    //a cada requisição (RequestScoped)
    @PostConstruct
    public void inicializarUsuario() {
        novoUsuario = new thaila();
        listaUsuarios = usuarioDAO.findAllHQL();
    }

    public void register() throws Exception {
        try {
            usuarioDAO.save(novoUsuario);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful");
            facesContext.addMessage(null, m);
            inicializarUsuario();
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration unsuccessful");
            facesContext.addMessage(null, m);
        }
    }

    private String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }

	public thaila getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(thaila novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public List<thaila> getListaUsuarios() {
		if (listaUsuarios == null) {
			listaUsuarios = usuarioDAO.findAllHQL();
		}
		return listaUsuarios;
	}

	public void setListaUsuarios(List<thaila> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}