/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ar.edu.unnoba.model.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

    
/**
 *
 * @author Sandoval
 */
@Stateless
@LocalBean
public class UsuarioDAO extends AbstractDAO<Usuario> { 
 
    public UsuarioDAO(Class<Usuario> entityClass) {
        super(entityClass);
    }

 
public UsuarioDAO(){
    super(Usuario.class);
}
   
    public boolean login(String user, String pass){
        Usuario usuario = new Usuario();
        usuario.setUsername(user);
        usuario.setPassword(pass);
        Query query=em.createNamedQuery("usuario.exist");
        query.setParameter("username", usuario.getUsername());
         query.setParameter("password", usuario.getPassword());
         List<Usuario>usuarios=query.getResultList();
         return !usuarios.isEmpty();
        
    }
    
}
