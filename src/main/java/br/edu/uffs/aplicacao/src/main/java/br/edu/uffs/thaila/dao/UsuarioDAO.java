package br.edu.uffs.thaila.dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.uffs.thaila.model.thaila;

@Stateful
public class UsuarioDAO {

	//Criar os objetos sob demanda de forma automática
	@Inject
    private EntityManager em;
	
	public thaila findById(Long id) {
        return em.find(thaila.class, id);
    }
	
	public List<thaila> findAll(){
		//Cria objeto que fará consulta
		CriteriaBuilder cb = em.getCriteriaBuilder();
		//Retorno é da classe Usuario
        CriteriaQuery<thaila> criteria = cb.createQuery(thaila.class);
        //From thaila
        Root<thaila> usuario = criteria.from(thaila.class);
        //Select todos os usuários
        criteria.select(usuario);//.orderBy(cb.asc(usuario.get("usuario")));
        //Executa a consulta e traz o retorno
        return em.createQuery(criteria).getResultList();
	}
	
	public List<thaila> findAllHQL() {
	    return em.createQuery("SELECT a FROM Usuario a ", 
	    		thaila.class).getResultList();      
	}
	
	public void save(thaila u) {
		em.persist(u);
	}
	
}