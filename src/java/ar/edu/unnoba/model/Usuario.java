/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author Sandoval
 */
@Entity
@Table(name="usuarios")
@NamedQueries({
@NamedQuery(name = "usuario.exist", query = "Select u from Usuario u where u.username= :username and u.password= :password")})

public class Usuario extends AbstractEntity{
    private String username;
    private String password;
    
      
    public Usuario(){
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
