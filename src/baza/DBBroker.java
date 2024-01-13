/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.util.ArrayList;
import java.util.List;
import model.Lokacija;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alat;
import model.Masina;
/**
 *
 * @author necam
 */
public class DBBroker {

    public List<Lokacija> vratiLokacije() {
        List<Lokacija> lista = new ArrayList<>();
        try {
            
            String upit = "SELECT * FROM LOKACIJA ORDER BY NAZIVLOKACIJE ASC";
            
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("nazivLokacije");
                
                Lokacija l = new Lokacija(id, naziv);
                lista.add(l);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Alat> vratiAlate() {
        List<Alat> lista = new ArrayList<>();
        try {
            
            String upit = "SELECT * FROM ALAT";
            
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()) {
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                int stanje = rs.getInt("stanje");
                
                Alat a = new Alat(id, naziv, stanje);
                lista.add(a);
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public boolean postojiMasina(int idLokacije) {
        String upit = "SELECT * FROM MASINA WHERE = "+idLokacije;
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    public boolean ubaciMasinu(Masina m) throws SQLException {
        try {
            String upit = "INSERT INTO masina(naziv, godProizvodnje, datumPocetka, radniVek, proizvodjac, tip, lokacija, inzenjer) VALUES (?, ?, ?,?, ?, ?, ?, ?)";
            
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, m.getNaziv());
            ps.setInt(2, m.getGodProizvodnje());
            ps.setDate(3, new java.sql.Date(m.getDatumPocetka().getTime()));
            ps.setInt(4, m.getRadniVek());
            ps.setString(5, m.getProizvodjac());
            ps.setString(6, m.getTip().toString());
            ps.setInt(7, m.getLokacija().getId());
            ps.setString(8, m.getInzenjer());
            
            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("GRESKA KREIRANJE MASINE");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            if(generatedKeys.next()) {
                long masinaId = generatedKeys.getLong(1);
                String upit2 = "INSERT INTO upotreba (masina, alat) VALUES (?, ?)";
                String upit3 = "UPDATE alat SET STANJE = ? WHERE ID = ?";
                ps = Konekcija.getInstance().getConnection().prepareStatement(upit2);
                PreparedStatement ps3 = Konekcija.getInstance().getConnection().prepareStatement(upit3);
                for (Alat alat : m.getAlati()) {
                    ps.setLong(1, masinaId);
                    ps.setLong(2, alat.getId());
                    
                    int novoStanje = alat.getStanje() - 1;
                    ps3.setLong(1, novoStanje);
                    ps3.setLong(2, alat.getId());
                    
                    ps.addBatch();
                    ps3.addBatch();
                }
                ps.executeBatch();
                ps3.executeBatch();
                Konekcija.getInstance().getConnection().commit();
                
            } else {
                Konekcija.getInstance().getConnection().rollback();
            }
            
        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
