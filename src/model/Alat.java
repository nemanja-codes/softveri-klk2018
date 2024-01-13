/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author necam
 */
public class Alat {
    private int id;
    private String naziv;
    private int stanje;

    public Alat() {
    }

    public Alat(int id, String naziv, int stanje) {
        this.id = id;
        this.naziv = naziv;
        this.stanje = stanje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(int stanje) {
        this.stanje = stanje;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
