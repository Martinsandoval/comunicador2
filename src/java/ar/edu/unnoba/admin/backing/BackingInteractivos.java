/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.admin.backing;

import DAO.ElementoInteractivoDAO;
import DAO.TematicaDAO;
import ar.edu.unnoba.model.ElementoInteractivo;
import ar.edu.unnoba.model.InteractivoCompuesto;
import ar.edu.unnoba.model.InteractivoFinal;
import ar.edu.unnoba.model.Tematica;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Part;
import javax.servlet.ServletContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Sandoval
 */
@ManagedBean
@ViewScoped

public class BackingInteractivos {

    private boolean isCompuesto = true;
    private boolean isPrimerNivel = true;
    private Tematica tematica;
   private UploadedFile file;
   private InteractivoCompuesto elementosuperior;
    private String nombre;
    private ElementoInteractivo elemento;
    @EJB
    private ElementoInteractivoDAO interactivoDAO;
    @EJB
    private TematicaDAO tematicaDAO;
    
    
    public BackingInteractivos() {
    }
    
    public void establecerTipoElemento(){
        elemento = (isCompuesto)? new InteractivoCompuesto():new InteractivoFinal();
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Tematica>getTematicas(){
        return tematicaDAO.findAll();
    }
    
    public List<ElementoInteractivo>getElementosCompuestos(){
        return interactivoDAO.compuestos();
    }
    
    public List<ElementoInteractivo>getElementos(){
        return interactivoDAO.findAll();
    }
    
      public boolean isIsCompuesto() {
        return isCompuesto;
    }

    public void setIsCompuesto(boolean isCompuesto) {
        this.isCompuesto = isCompuesto;
    }

    public boolean isIsPrimerNivel() {
        return isPrimerNivel;
    }

    public void setIsPrimerNivel(boolean isPrimerNivel) {
        this.isPrimerNivel = isPrimerNivel;
    }

    public InteractivoCompuesto getElementosuperior() {
        return elementosuperior;
    }

    public void setElementosuperior(InteractivoCompuesto elementosuperior) {
        this.elementosuperior = elementosuperior;
    }
    
     public ElementoInteractivo getElemento() {
        return elemento;
    }

    public void setElemento(ElementoInteractivo elemento) {
        this.elemento = elemento;
    }
    
    public String agregar(){
        if(isCompuesto){
                elemento = new InteractivoCompuesto();
                elemento.setTipo("compuesto");
               
        }else{
            elemento = new InteractivoFinal();
            elemento.setTipo("final");
        }
        try{
            elemento.setNombre(nombre);
            elemento.setTematica(tematica);
            elemento.setInteractivocompuesto(elementosuperior);
            elemento.setImg_path(file.getFileName());
            if(isPrimerNivel){
            tematica.getElementos().add(elemento);
            }else{
                elemento.getInteractivocompuesto().getInteractivos().add(elemento);
            }
            interactivoDAO.create(elemento);
              FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("El elemento fué agregado exitosamente"));
            return "success";
            }catch(Exception e){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error al crear elemento"));
            return "failure";
            
        }
       
    }
     public String borrarElemento(int id){
       try{
        elemento = interactivoDAO.find(id);
        interactivoDAO.remove(elemento);
        return "success";
       }catch(Exception e){
           FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error al eliminar"));
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
            String carpetaImagen=(String)servletContext.getRealPath("/interactivo_imagen");
           
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

