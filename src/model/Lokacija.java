/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author necam
 */
public class Lokacija {
    private int id;
    private String nazivLokacije;

    public Lokacija() {
    }

    public Lokacija(int sifra, String nazivLokacije) {
        this.id = sifra;
        this.nazivLokacije = nazivLokacije;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivLokacije() {
        return nazivLokacije;
    }

    public void setNazivLokacije(String nazivLokacije) {
        this.nazivLokacije = nazivLokacije;
    }

    @Override
    public String toString() {
        return nazivLokacije;
    }
    
    
   
}
