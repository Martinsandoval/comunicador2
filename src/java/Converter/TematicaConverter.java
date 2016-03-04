/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.TematicaDAO;
import ar.edu.unnoba.model.Tematica;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Sandoval
 */
@FacesConverter(forClass=Tematica.class)
public class TematicaConverter extends AbstractEntityConverter<Tematica> {

    @Override
    public String getDAOName() {
      return TematicaDAO.class.getSimpleName();
    }
    
}
