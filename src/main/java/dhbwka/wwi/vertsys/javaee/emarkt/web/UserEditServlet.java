/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.emarkt.web;

import dhbwka.wwi.vertsys.javaee.emarkt.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.emarkt.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.emarkt.jpa.Task;
import dhbwka.wwi.vertsys.javaee.emarkt.jpa.User;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet für die Registrierungsseite. Hier kann sich ein neuer Benutzer
 * registrieren. Anschließend wird der auf die Startseite weitergeleitet.
 */
@WebServlet(urlPatterns = {"/app/user/"})
public class UserEditServlet extends HttpServlet {
    
    @EJB
    ValidationBean validationBean;
            
    @EJB
    UserBean userBean;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        User user = this.userBean.getCurrentUser();
        request.setAttribute("edit_form",createEditForm(user));
        
        // Anfrage an dazugerhörige JSP weiterleiten
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/app/user_edit.jsp");
        dispatcher.forward(request, response);
        
        // Alte Formulardaten aus der Session entfernen
        HttpSession session = request.getSession();
        session.removeAttribute("edit_form");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Formulareingaben auslesen
        request.setCharacterEncoding("utf-8");
        
        String username = request.getParameter("edit_username");
        String oldpw = request.getParameter("edit_oldpw");
        String password1 = request.getParameter("edit_password1");
        String password2 = request.getParameter("edit_password2");
        String name = request.getParameter("edit_name");
        String strasse = request.getParameter("edit_strasse");
        String plz = request.getParameter("edit_plz");
        String ort = request.getParameter("edit_ort");
        String telefon = request.getParameter("edit_telefon");
        String mail = request.getParameter("edit_mail");
       
//        int iplz=00000;
//        try{
//             iplz = Integer.parseInt(plz);
//        
//        }catch (NumberFormatException nfe){
//            
//        }
        
        // Eingaben prüfen
        User user = new User(username, password1, name, strasse, plz, ort, telefon, mail);
        List<String> errors = this.validationBean.validate(user);
        this.validationBean.validate(user.getPassword(), errors);
        
        if (password1 != null && password2 != null && !password1.equals(password2)) {
            errors.add("Die beiden Passwörter stimmen nicht überein.");
        }
        
        // Neuen Benutzer anlegen
        try{
        if (errors.isEmpty()) {
                this.userBean.update(user); }
        }
        catch(IllegalStateException ie){
        }
        
        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/user/"));
        } else {
            // Fehler: Formular erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);
            
            HttpSession session = request.getSession();
            session.setAttribute("edit_form", formValues);
            
            response.sendRedirect(request.getRequestURI());
        }
    }
    private FormValues createEditForm(User user) {
        Map<String, String[]> values = new HashMap<>();

        values.put("edit_username", new String[]{
            user.getUsername()
        });

        values.put("edit_name", new String[]{
            user.getName()
        });
        
        values.put("edit_password1", new String[]{
            "default"
        });
        
        values.put("edit_password2", new String[]{
            "default"
        });
        values.put("edit_oldpw", new String[]{
            "default"
        });

        values.put("edit_strasse", new String[]{
            user.getStrasse()
        });

        values.put("edit_plz", new String[]{
            user.getPlz()
        });

        values.put("edit_ort", new String[]{
            user.getOrt()
        });
        
        values.put("edit_telefon", new String[]{
            user.getTelefon()
        });
        
        values.put("edit_mail", new String[]{
            user.getMail()
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }
    
}
