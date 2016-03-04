/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sandoval
 */

@Entity
@Table(name="tematicas")
@NamedQueries({
@NamedQuery(name = "tematicas.all", query = "Select t from Tematica t order by t.nombre")})
public class Tematica extends AbstractEntity<Tematica>{
   private String nombre;
   private String img_path;
   private boolean activa = false;
   @OneToMany(mappedBy = "tematica")
   private List<ElementoInteractivo>elementos;
   
   public Tematica(){
   }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<ElementoInteractivo> getElementos() {
        return elementos;
    }

    public void setElementos(List<ElementoInteractivo> elementos) {
        this.elementos = elementos;
    }
     public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
