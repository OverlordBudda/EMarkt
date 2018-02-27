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
public enum PreisArt {
    VHB, FESTPREIS;

    /**
     * Bezeichnung ermitteln
     *
     * @return Bezeichnung
     */
    public String getPreisart() {
        switch (this) {
            case VHB:
                return "Verhandlungsbasis";
            case FESTPREIS:
                return "Festpreis";
            default:
                return this.toString();
        }
    }
}
