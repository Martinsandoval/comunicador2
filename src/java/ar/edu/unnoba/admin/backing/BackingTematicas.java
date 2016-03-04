/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.admin.backing;

import DAO.TematicaDAO;
import ar.edu.unnoba.model.Tematica;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Sandoval
 */
@ManagedBean
@ViewScoped
public class BackingTematicas implements Serializable {
   private Tematica tematica;
   @EJB
   private TematicaDAO tematicaDAO;
   private UploadedFile file;
   
   public BackingTematicas() {
        this.tematica=new Tematica();
    }
  public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
  
   
    public Tematica getTematica() {
        return tematica;
    }

    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
    
    
    public String agregar(){
      try{
        
        tematicaDAO.create(tematica);
        FacesContext context = FacesContext.getCurrentInstance();
       context.addMessage(null, new FacesMessage("La tematica fué creada exitosamente"));
       return "success";
      }catch(Exception e){
          FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error al crear tematica"));
            return "failure";
      }
}
    public List<Tematica>getTematicas(){
        return tematicaDAO.findAll();
    }
    public String borrarTematica(int id){
       try{
        tematica = tematicaDAO.find(id);
        tematicaDAO.remove(tematica);
        return "success";
       }catch(Exception e){
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error al eliminar"));
           return "failure";
           
       }
      }
    
    public String activar(int id) {
        try{
            tematica = tematicaDAO.find(id);
            if(tematica.isActiva()){
                tematica.setActiva(false);
            }else{
               tematica.setActiva(true);

            }
                tematicaDAO.edit(tematica);
                return "success";
         
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No se puedo activar/desactivar"));
            return "failure";
        }

    }
    
 public void upload()throws IOException
    {
        InputStream inputStream=null;
        OutputStream outputStream=null;
        
        try
        {
            if(this.file.getSize()<=0)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
                return;
            }
            
            if(!this.file.getFileName().endsWith(".png"))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo debe ser con extensión \".png\""));
                return;
            }
            
            if(this.file.getSize()>2097152)
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
                return;
            }
            
            ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaImagen=(String)servletContext.getRealPath("/web");
            tematica.setImg_path(file.getFileName());
            outputStream=new FileOutputStream(new File(carpetaImagen+"/"+file.getFileName()));
            inputStream=this.file.getInputstream();
            
            int read=0;
            byte[] bytes=new byte[1024];
            
            while((read=inputStream.read(bytes))!=-1)
            {
                outputStream.write(bytes, 0, read);
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La imagen se cargo correctamente", "Correcto"));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador "+ex.getMessage()));
        }
        finally
        {
            if(inputStream!=null)
            {
                inputStream.close();
            }
            
            if(outputStream!=null)
            {
                outputStream.close();
            }
        }
        
       
    }
 
 
    
   
}  

