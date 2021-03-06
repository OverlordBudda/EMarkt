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

import dhbwka.wwi.vertsys.javaee.emarkt.ejb.CategoryBean;
import dhbwka.wwi.vertsys.javaee.emarkt.ejb.TaskBean;
import dhbwka.wwi.vertsys.javaee.emarkt.jpa.Category;
import dhbwka.wwi.vertsys.javaee.emarkt.jpa.Task;
import dhbwka.wwi.vertsys.javaee.emarkt.jpa.AngebotsArt;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die Startseite bzw. jede Seite, die eine Liste der Aufgaben
 * zeigt.
 */
@WebServlet(urlPatterns = {"/app/tasks/"})
public class TaskListServlet extends HttpServlet {

    @EJB
    private CategoryBean categoryBean;
    
    @EJB
    private TaskBean taskBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verfügbare Kategorien und Stati für die Suchfelder ermitteln
        request.setAttribute("categories", this.categoryBean.findAllSorted());
        request.setAttribute("angebotsArten", AngebotsArt.values());

        // Suchparameter aus der URL auslesen
        String searchText = request.getParameter("search_text");
        String searchCategory = request.getParameter("search_category");
        String searchAngebotsArt = request.getParameter("search_angebotsArt");

        // Anzuzeigende Aufgaben suchen
        Category category = null;
        AngebotsArt angebotsArt = null;

        if (searchCategory != null) {
            try {
                category = this.categoryBean.findById(Long.parseLong(searchCategory));
            } catch (NumberFormatException ex) {
                category = null;
            }
        }

        if (searchAngebotsArt != null) {
            try {
                angebotsArt = AngebotsArt.valueOf(searchAngebotsArt);
            } catch (IllegalArgumentException ex) {
                angebotsArt = null;
            }

        }

        List<Task> tasks = this.taskBean.search(searchText, category, angebotsArt);
        request.setAttribute("tasks", tasks);

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/app/task_list.jsp").forward(request, response);
    }
}
