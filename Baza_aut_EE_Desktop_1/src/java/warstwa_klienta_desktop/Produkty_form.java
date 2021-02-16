/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_klienta_desktop;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paweł Esmanowicz
 */
public class Produkty_form extends JPanel {

    private JTable tabela_aut;	
    MyTableModel model_tab;			
    JComboBox lista_aut;		

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model_tab = new MyTableModel();	
        tabela_aut = new JTable(model_tab);	
        table_content();
        tabela_aut.setPreferredScrollableViewportSize(new Dimension(800, 220));
        tabela_aut.setFillsViewportHeight(true);
        tabela_aut.getSelectionModel().addListSelectionListener(new RowListener());          
        add(new JScrollPane(tabela_aut));			         
        JLabel lprodukty = new JLabel("Auta");
        add(lprodukty);
        lista_aut = new JComboBox();
        add(lista_aut);
    }

    void table_content() {				
        ArrayList<ArrayList<String>> produkty = GUI_main.getFacade().items();
        model_tab.setData(produkty);
        //tabela_produktow.repaint();
        model_tab.fireTableDataChanged();
    }

    private void list_content(ArrayList<ArrayList<String>> col, JComboBox list) {
        ArrayList<String> s;				
        list.removeAllItems();
        Iterator<ArrayList<String>> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    void print_produkty() {		 
        ArrayList<ArrayList<String>> help3 = GUI_main.getFacade().items();	
        if (help3 != null) {
            list_content(help3, lista_aut);			         
        }
    }

    private class RowListener implements ListSelectionListener {      

        @Override
        public void valueChanged(ListSelectionEvent event) {		 
            if (event.getValueIsAdjusting()) {			
                return;
            }
            print_produkty();		  
        }
    }

    class MyTableModel extends AbstractTableModel {	

        private final String[] columnNames = {"Id produktu", "Producent", "Model", "Rodzaj paliwa", "Pojemność silnika", "Przebieg",  "Rok produkcji", "Cena", "Data zakupu" };
        private ArrayList<ArrayList<String>> data;	

        public void setData(ArrayList<ArrayList<String>> val) { 		
            data = val;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length; 				
        }

        @Override
        public int getRowCount() {
            return data.size();					
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);		
        }
    }
}
