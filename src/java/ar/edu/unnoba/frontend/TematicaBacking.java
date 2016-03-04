/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.frontend;

import DAO.TematicaDAO;
import ar.edu.unnoba.model.Tematica;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jpgm
 */
@ManagedBean(name = "tematicaFrontBacking")
@RequestScoped
public class TematicaBacking {
    
    @ManagedProperty(value="#{elementoInteractivoFrontBacking}")
    private ElementoInteractivoBacking elementoInteractivoBacking;
    @EJB
    private TematicaDAO tematicaDAO;
    private Tematica tematica;
  

  
    public TematicaBacking() {
        tematica = new Tematica();
    }
   
    public String init(int id) throws Exception{
        tematica=tematicaDAO.find(id);
        return "interactivos/show.xhtml?id="+id;
    }
    public ElementoInteractivoBacking getElementoInteractivoBacking() {
        return elementoInteractivoBacking;
    }

    public void setElementoInteractivoBacking(ElementoInteractivoBacking elementoInteractivoBacking) {
        this.elementoInteractivoBacking = elementoInteractivoBacking;
    }
    
    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
    
    public void seleccionarElemento(int id) throws Exception{
        elementoInteractivoBacking.seleccionarElemento(id);
    }
  
 
}
