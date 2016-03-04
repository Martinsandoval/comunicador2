/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sandoval
 */


@DiscriminatorValue("final")
@Entity
@Table(name="elementosinteractivos")
public class InteractivoFinal extends ElementoInteractivo{
    
}
