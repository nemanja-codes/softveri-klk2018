/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Inzenjer;
import model.Lokacija;
import model.Masina;

/**
 *
 * @author necam
 */
public class Controller {
    private static Controller instance;
    private List<Inzenjer> inzenjeri = new ArrayList<>();
    private Inzenjer ulogovaniInzenjer = null;
    private DBBroker dbb;

    public static Controller getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    
    
    private Controller() {
        dbb = new DBBroker();
        Inzenjer i1 = new Inzenjer(1, "Nemanja", "Markovic", "neca123", "neca123");
        Inzenjer i2 = new Inzenjer(2, "Petar", "Petrovic", "pera123", "pera123");
        Inzenjer i3 = new Inzenjer(3, "Ana", "Avakumovic", "ana123", "ana123");
        
        inzenjeri.add(i1);
        inzenjeri.add(i2);
        inzenjeri.add(i3);
    }

    public List<Inzenjer> getInzenjeri() {
        return inzenjeri;
    }

    public void setInzenjeri(List<Inzenjer> inzenjeri) {
        this.inzenjeri = inzenjeri;
    }

    public Inzenjer getUlogovaniInzenjer() {
        return ulogovaniInzenjer;
    }

    public void setUlogovaniInzenjer(Inzenjer ulogovaniInzenjer) {
        this.ulogovaniInzenjer = ulogovaniInzenjer;
    }

    
    
    public Inzenjer login(String username, String lozinka) {
        for (Inzenjer inzenjer : inzenjeri) {
            if(inzenjer.getKorisnickoIme().equals(username) && inzenjer.getLozinka().equals(lozinka)) {
                ulogovaniInzenjer = inzenjer;
                return inzenjer;
            }
        }
        return null;
    }

    public List<Lokacija> vratiLokacije() {
        return dbb.vratiLokacije();
    }

    public List<Alat> vratiAlate() {
        return dbb.vratiAlate();
    }

    public boolean postojiMasina(int idLokacije) {
        return dbb.postojiMasina(idLokacije);
    }

    public boolean ubaciMasinu(Masina m) throws SQLException {
        return dbb.ubaciMasinu(m);
    }
    
    
    
}
