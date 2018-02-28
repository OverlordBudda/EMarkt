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
        Benutzerprofil bearbeiten
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/login.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/"/>">Übersicht</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div class="container">
            <form method="post" class="stacked">
                <div class="column">
                    <%-- CSRF-Token --%>
                    <input type="hidden" name="csrf_token" value="${csrf_token}">
                    <h2>Logindaten</h2>        
                    <%-- Eingabefelder --%>
                    <label for="edit_username">
                        Benutzername:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_username" value="${edit_form.values["edit_username"][0]}" >
                    </div>
                    
                    <label for="edit_oldpw">
                        altes Passwort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_oldpw" value="${edit_form.values["edit_oldpw"][0]}">
                    </div>
                    
                    <label for="edit_password1">
                        Passwort (PW ändern sonst "default"):
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_password1" value="${edit_form.values["edit_password1"][0]}">
                    </div>

                    <label for="edit_password2">
                        Passwort (wdh.):
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="password" name="edit_password2" value="${edit_form.values["edit_password2"][0]}">
                    </div>
                    
                    <h2>Anschrift</h2>
                    <label for="edit_name">
                        Vor- und Nachname:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_name" value="${edit_form.values["edit_name"][0]}">
                    </div>
                    
                    <label for="edit_strasse">
                        Strassenname & Hausnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_strasse" value="${edit_form.values["edit_strasse"][0]}">
                    </div>
                    
                    <label for="edit_plz">
                        PLZ & Ort:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_plz" value="${edit_form.values["edit_plz"][0]}">
                        <input type="text" name="edit_ort" value="${edit_form.values["edit_ort"][0]}">
                    </div>
                    
                    <h2>Kontaktdaten</h2>
                    <label for="edit_telefon">
                        Telefonnummer:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_telefon" value="${edit_form.values["edit_telefon"][0]}">
                    </div>
                    
                    <label for="edit_mail">
                        E-Mail:
                        <span class="required">*</span>
                    </label>
                    <div class="side-by-side">
                        <input type="text" name="edit_mail" value="${edit_form.values["edit_mail"][0]}">
                    </div>
                    
                    <%-- Button zum Abschicken --%>
                    <div class="side-by-side">
                        <button class="icon-pencil" type="submit">
                            Ändern
                        </button>
                    </div>
                </div>
                <%-- Fehlermeldungen --%>
                <c:if test="${!empty edit_form.errors}">
                    <ul class="errors">
                        <c:forEach items="${edit_form.errors}" var="error">
                            <li>${error}</li>
                            </c:forEach>
                    </ul>
                </c:if>
            </form>
        </div>
    </jsp:attribute>
</template:base>