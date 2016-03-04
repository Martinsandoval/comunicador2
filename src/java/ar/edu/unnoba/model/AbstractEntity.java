/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Sandoval
 */
@MappedSuperclass
public abstract class AbstractEntity<T> {
   @Id
   private int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
   public boolean equals(Object obj) {
        final AbstractEntity<T> other = (AbstractEntity<T>)obj;
        if (this.id == other.getId()){
            return true;
        }
        return false;
    }
    
}
