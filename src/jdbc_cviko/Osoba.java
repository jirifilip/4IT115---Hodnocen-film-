/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc_cviko;

/**
 *
 * @author zenisjan
 */
public class Osoba {
    
    private int id;
    private int osoba_id;
    private String jmeno;
    private String prijmeni;
    private String adresa;
    private String pohlavi;

    public Osoba(int id,int osoba_id, String jmeno, String prijmeni, String adresa, String pohlavi) {
        this.id = id;
        this.osoba_id = osoba_id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.adresa = adresa;
        this.pohlavi = pohlavi;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the osoba_id
     */
    public int getOsoba_id() {
        return osoba_id;
    }

    /**
     * @param osoba_id the osoba_id to set
     */
    public void setOsoba_id(int osoba_id) {
        this.osoba_id = osoba_id;
    }

    /**
     * @return the pohlavi
     */
    public String getPohlavi() {
        return pohlavi;
    }

    /**
     * @param pohlavi the pohlavi to set
     */
    public void setPohlavi(String pohlavi) {
        this.pohlavi = pohlavi;
    }
    
    
    
}
