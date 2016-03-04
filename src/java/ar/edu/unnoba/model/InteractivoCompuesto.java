/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.model;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Sandoval
 */
@Entity
@DiscriminatorValue("compuesto")
@NamedQueries({
@NamedQuery(name = "compuestos.all", query = "Select c.nombre from InteractivoCompuesto c order by c.nombre")})

public class InteractivoCompuesto extends ElementoInteractivo{
    @OneToMany(mappedBy = "interactivocompuesto")
    private List<ElementoInteractivo>interactivos;

   public InteractivoCompuesto(){
   }
   
    public List<ElementoInteractivo> getInteractivos() {
        return interactivos;
    }

    public void setInteractivos(List<ElementoInteractivo> interactivos) {
        this.interactivos = interactivos;
    }
}
