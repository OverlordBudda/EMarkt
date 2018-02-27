/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.emarkt.ejb;

/**
 *
 * @author Henry
 */

import dhbwka.wwi.vertsys.javaee.emarkt.jpa.AngebotsArt;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@RolesAllowed("emarkt-app-user")
public class AngebotsArtBean extends EntityBean<AngebotsArt, Long>{
  
    public AngebotsArtBean() {
        super(AngebotsArt.class);
    }

    /**
     * Auslesen aller Kategorien, alphabetisch sortiert.
     *
     * @return Liste mit allen Kategorien
     */
    public List<AngebotsArt> findAllSorted() {
        return this.em.createQuery("SELECT c FROM Angebotsart c ORDER BY c.angebotsart").getResultList();
    }  
}
