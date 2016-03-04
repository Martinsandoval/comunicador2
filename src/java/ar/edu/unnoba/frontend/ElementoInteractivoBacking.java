/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.frontend;

import ar.edu.unnoba.admin.backing.*;
import DAO.ElementoInteractivoDAO;
import DAO.TematicaDAO;
import ar.edu.unnoba.model.InteractivoCompuesto;
import ar.edu.unnoba.model.ElementoInteractivo;
import ar.edu.unnoba.model.InteractivoFinal;
import ar.edu.unnoba.model.Tematica;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author jpgm
 */
@ManagedBean(name = "elementoInteractivoFrontBacking")
@ViewScoped
public class ElementoInteractivoBacking {

    private InteractivoCompuesto elemento;
    @EJB
    private ElementoInteractivoDAO elementoInteractivoDAO;
    @ManagedProperty(value = "#{seleccionElementosBacking}")
    private SeleccionElementosBacking seleccionElementosBacking;
    String outcome = null;
    

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public InteractivoCompuesto getElemento() {
        return elemento;
    }

    public void setElemento(InteractivoCompuesto elemento) {
        this.elemento = elemento;
    }

    public SeleccionElementosBacking getSeleccionElementosBacking() {
        return seleccionElementosBacking;
    }

    public void setSeleccionElementosBacking(SeleccionElementosBacking seleccionElementosBacking) {
        this.seleccionElementosBacking = seleccionElementosBacking;
    }
   
            
    

    public void seleccionarElemento(int id) throws Exception {

        ElementoInteractivo elementoSeleccionado = elementoInteractivoDAO.find(id);
        if (elementoSeleccionado.getTipo().equals("final")) {               //Hacer el metodo isSeleccionable en ElementoInteractivo
            if (!seleccionElementosBacking.getElementoSeleccionados().contains(elementoSeleccionado)) {
                seleccionElementosBacking.agregarElementoASeleccion((InteractivoFinal) elementoSeleccionado);
               
            } else {
                seleccionElementosBacking.quitarElementoASeleccion((InteractivoFinal) elementoSeleccionado);
               
            }
        } else {
           
//            outcome = "/interactivos/show?faces-redirect=true&id=" + id;
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            facesContext.getApplication()
//                    .getNavigationHandler().handleNavigation(facesContext, null, outcome);
        }

    }

    public void volver(int elementoActualId) throws Exception {

        ElementoInteractivo elementoActual = elementoInteractivoDAO.find(elementoActualId);
        if (elementoActual.getTematica() == null) {
            outcome = "/interactivos/show?faces-redirect=true&id=" + elementoActual.getInteractivocompuesto().getId();
        } else {
            outcome = "/tematicas/show?faces-redirect=true&id=" + elementoActual.getTematica().getId();
        }
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getApplication()
                .getNavigationHandler().handleNavigation(facesContext, null, outcome);
    }
}
