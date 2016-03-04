/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.ElementoInteractivoDAO;
import ar.edu.unnoba.model.ElementoInteractivo;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Sandoval
 */
@FacesConverter(forClass=ElementoInteractivo.class)
public class ElementoConverter extends AbstractEntityConverter<ElementoInteractivo>{

    @Override
    public String getDAOName() {
        return ElementoInteractivoDAO.class.getSimpleName();
    }
    
}
