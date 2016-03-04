/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.unnoba.admin.backing;

import ar.edu.unnoba.model.Usuario;
import javax.ejb.SessionBean;
import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sandoval
 */
public class MyPhaseListener implements PhaseListener {

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext fc = event.getFacesContext();
        boolean privado = fc.getViewRoot().getViewId().lastIndexOf("/admin/") > -1;
        boolean welcome = fc.getViewRoot().getViewId().lastIndexOf("welcome") > -1;

        if (!isLoggedIn() && (privado || welcome) ) {
            NavigationHandler nh = fc.getApplication().getNavigationHandler();
            nh.handleNavigation(fc, null, "bienvenida");
        }
    }

   public boolean isLoggedIn() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null) {
            return true;
        }
        return false;

    }
}
