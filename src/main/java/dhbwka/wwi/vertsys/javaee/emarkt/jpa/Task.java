/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.emarkt.jpa;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @ManyToOne
    @NotNull(message = "Die Aufgabe muss einem Benutzer geordnet werden.")
    private User owner;

    @ManyToOne
    private Category category;

    @Column(length = 50)
    @NotNull(message = "Die Bezeichnung darf nicht leer sein.")
    @Size(min = 1, max = 50, message = "Die Bezeichnung muss zwischen ein und 50 Zeichen lang sein.")
    private String shortText;
        
    @Column(length = 64)
    @NotNull(message = "Die Angebotsart darf nicht leer sein.")
    @Size(min = 1, max = 64, message = "Die Angebotsart muss zwischen ein und 50 Zeichen lang sein.")
    private String angebotsPreis;
    
  

    @Lob
    @NotNull
    private String longText;

    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date dueDate;

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time dueTime;

    @Enumerated(EnumType.STRING)
    @NotNull
    private AngebotsArt angebotsArt = AngebotsArt.SUCHE;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private PreisArt preisArt = PreisArt.FESTPREIS;

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Task() {
    }

    public Task(User owner, Category category, AngebotsArt angebotsArt, String shortText, String longText,PreisArt preisArt, String angebotsPreis, Date dueDate, Time dueTime) {
        this.owner = owner;
        this.category = category;
        this.angebotsArt = angebotsArt;
        this.shortText = shortText;
        this.longText = longText;
        this.preisArt = preisArt;
        this.angebotsPreis = angebotsPreis;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getDueTime() {
        return dueTime;
    }

    public void setDueTime(Time dueTime) {
        this.dueTime = dueTime;
    }
    public String getAngebotsPreis() {
        return angebotsPreis;
    }

    public void setAngebotsPreis(String angebotsPreis) {
        this.angebotsPreis = angebotsPreis;
    }

    public AngebotsArt getAngebotsArt() {
        return angebotsArt;
    }

    public void setAngebotsArt(AngebotsArt angebotsArt) {
        this.angebotsArt = angebotsArt;
    }

    public PreisArt getPreisArt() {
        return preisArt;
    }

    public void setPreisArt(PreisArt preisArt) {
        this.preisArt = preisArt;
    }
    
    //</editor-fold>

}
