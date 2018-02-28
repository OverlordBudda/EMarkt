<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        <c:choose>
            <c:when test="${edit}">
                Angebot bearbeiten
            </c:when>
            <c:otherwise>
                Angebot anlegen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/task_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>

                <label for="task_category">Kategorie:</label>
                <div class="side-by-side">
                        <select name="task_category">
                   
                            <option value="" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>Keine Kategorie</option>

                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}" ${task_form.values["task_category"][0] == category.name ? 'selected' : ''} ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                                <c:out value="${category.name}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <label for="task_angebotsArt">Art des Angebots:</label>
                <div class="side-by-side">
                    <select name="task_angebotsArt">
                        <option value="" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>Wähle</option>

                        <c:forEach items="${angebotsArten}" var="angebotsArt">
                            <option value="${angebotsArt}" ${task_form.values["task_angebotsArt"][0] == angebotsArt ? 'selected' : ''}${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                                <c:out value="${angebotsArt}" />
                            </option>
                        </c:forEach>
                    </select>
                </div>
                
                <label for="task_shorttext">
                    Bezeichnung:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_shorttext" value="${task_form.values["task_short_text"][0]}" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal.toString() ? '' : 'disabled'} >
                </div>
                
                <label for="task_longtext">
                    Beschreibung:
                </label>
                <div class="side-by-side">
                    <input type="text" name="task_longtext" value="${task_form.values["task_long_text"][0]}" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'} >
                </div>
                
                <label for="task_preisArt">Preis:</label>
                <div class="side-by-side">
                    <select name="task_preisArt">
                        <option value="" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>Wähle</option>

                        <c:forEach items="${preisArten}" var="preisArt">
                            <option value="${preisArt}" ${task_form.values["task_preisArt"][0] == preisArt ? 'selected' : ''}${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                                <c:out value="${preisArt}" />
                            </option>
                        </c:forEach>
                    </select>
                    
                    <input type="text" name="task_preis" value="${task_form.values["task_preis"][0]}" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                </div>


                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete" ${task_form.values["task_owner"][0] == pageContext.request.userPrincipal ? '' : 'disabled'}>
                            Löschen
                        </button>
                    </c:if>
                </div>
                
                <label>Angelegt am:</label>
                <div class="side-by-side">
                    <label>${task_form.values["task_due_date"][0]} ${task_form.values["task_due_time"][0]}</label> <br>

                </div>    
                
                <label>Anbieter:</label>
                <div class="side-by-side"> </div>                    
                    <label>${task_form.values["task_owner"][1]}</label>
                    <label>${task_form.values["task_owner"][2]}</label>
                    <label>${task_form.values["task_owner"][3]}</label> 
                    <label>${task_form.values["task_owner"][4]}</label> 

                
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty task_form.errors}">
                <ul class="errors">
                    <c:forEach items="${task_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>
