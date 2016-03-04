/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ar.edu.unnoba.model.Tematica;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Sandoval
 */
@Stateless
@LocalBean
public class TematicaDAO extends AbstractDAO<Tematica>{
     public TematicaDAO(Class<Tematica> entityClass) {
        super(entityClass);
    }

 
public TematicaDAO(){
    super(Tematica.class);
}

public List findAll(){
    Query query=em.createNamedQuery("tematicas.all");
   return query.getResultList();
     
}

}
