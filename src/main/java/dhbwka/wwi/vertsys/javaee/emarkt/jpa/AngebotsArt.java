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

/**
 *
 * @author Henry
 */
public enum AngebotsArt {
        SUCHE, BIETE;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getAngebotsArt() {
        switch (this) {
            case SUCHE:
                return "Suche";
            case BIETE:
                return "Biete";
            default:
                return this.toString();
        }
    }
}
