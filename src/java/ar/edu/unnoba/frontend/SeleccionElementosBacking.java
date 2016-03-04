/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.edu.unnoba.frontend;

import ar.edu.unnoba.model.ElementoInteractivo;
import ar.edu.unnoba.model.InteractivoFinal;
import ar.edu.unnoba.model.Tematica;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author jpgm
 */
@ManagedBean
@SessionScoped
public class SeleccionElementosBacking {
    
    private List<InteractivoFinal> elementoSeleccionados;
    private Tematica tematica;

    /**
     * Creates a new instance of SeleccionElementosBacking
     */
    public SeleccionElementosBacking() {
        elementoSeleccionados = new ArrayList<>();
        tematica = new Tematica();
    }

    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
        this.elementoSeleccionados.clear();
    }
    
    public List<InteractivoFinal> getElementoSeleccionados() {
        return elementoSeleccionados;
    }

    public void agregarElementoASeleccion(InteractivoFinal elemento){
        if(!this.getElementoSeleccionados().contains(elemento))
            this.getElementoSeleccionados().add(elemento);
    }
    
    public void quitarElementoASeleccion(InteractivoFinal elemento){
        this.getElementoSeleccionados().remove(elemento);
    }
    
    public void limpiarSeleccion(){
        this.getElementoSeleccionados().clear();
    }
    
}
