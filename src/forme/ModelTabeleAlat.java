/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Alat;

/**
 *
 * @author necam
 */
public class ModelTabeleAlat extends AbstractTableModel {
    private List<Alat> alati = new ArrayList<>();
    private String[] kolone = {"id", "naziv", "stanje"};

    public ModelTabeleAlat() {
    }
    
    

    public ModelTabeleAlat(List<Alat> alati) {
        this.alati = alati;
    }

    public List<Alat> getAlati() {
        return alati;
    }

    public void setAlati(List<Alat> alati) {
        this.alati = alati;
    }
    
    
    
    @Override
    public int getRowCount() {
        return alati.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alat a = alati.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return a.getId();
            case 1:
                return a.getNaziv();
            case 2:
                return a.getStanje();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    void dodajAlat(Alat a) {
        alati.add(a);
        fireTableDataChanged();
    }

    void obrisiAlat(int selektovaniRed) {
        alati.remove(selektovaniRed);
        fireTableDataChanged();
    }
    
    
    
    
}
