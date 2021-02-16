/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sklep_gui;

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

    private JTable tabela_aut;	//komponent typu tabela do wyświetlania danych produktów
    MyTableModel model_tab;			//model widoku
    JComboBox lista_aut;		//lista wyswietlajaca dane produktów

    public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model_tab = new MyTableModel();	
        tabela_aut = new JTable(model_tab);	//tworzenie modelu danych tabeli
        table_content();//wypelnienie danymi produktow tabeli
                       // utworzenie tabeli i przekazanie jej modelu z danymi produktow
        tabela_aut.setPreferredScrollableViewportSize(new Dimension(800, 100));
        tabela_aut.setFillsViewportHeight(true);
        tabela_aut.getSelectionModel().addListSelectionListener(new RowListener());            //dodanie słuchacza zdarzen do obslugi 						             //zmiany wyboru wiersza 
        add(new JScrollPane(tabela_aut));			             //dodanie panelu przewijania tabdli 
        JLabel lprodukty = new JLabel("Auta");
        add(lprodukty);
        lista_aut = new JComboBox();
        add(lista_aut);
    }

    void table_content() {				//wypelnienie tablicy typu JTable danymi produktow
        ArrayList<ArrayList<String>> produkty = GUI_main.getFacade().items();
        model_tab.setData(produkty);
        //tabela_produktow.repaint();
        model_tab.fireTableDataChanged();
    }

    private void list_content(ArrayList<ArrayList<String>> col, JComboBox list) {
        ArrayList<String> s;				//wypelnienie listy typu JComboBox danymi produktow
        list.removeAllItems();
        Iterator<ArrayList<String>> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    void print_produkty() {		//metoda wypelniajaca listę typu JComboBox danymi produktow pobranymi metodą items. 
        ArrayList<ArrayList<String>> help3 = GUI_main.getFacade().items();	            // pobranie danych produktow metoda items
        if (help3 != null) {
            list_content(help3, lista_aut);			           //wypelnianie listy typu JComboBox danymi produktow
        }
    }

    private class RowListener implements ListSelectionListener {      	//klasa wewnetrzna do obslugi zdarzen zmiany wyboru wiersza tabeli

        @Override
        public void valueChanged(ListSelectionEvent event) {		//metoda do obsługi zdarzenia zmiany  wybranego wiersza tabeli 
            if (event.getValueIsAdjusting()) {			//za pomocą klikniecia myszy na wybrany rowek
                return;
            }
            print_produkty();		// po zmianie wiersza wykonanie metody wypelniajacej listę typu JComboBox danymi produktow  
        }
    }

    class MyTableModel extends AbstractTableModel {	//klasa wewnetrzna reprezentujaca model danych obiektu typu JTable

        private final String[] columnNames = {"Id produktu", "Producent", "Model", "Rodzaj paliwa", "Pojemność silnika", "Przebieg",  "Rok produkcji", "Cena", "Data zakupu" };
        private ArrayList<ArrayList<String>> data;	

        public void setData(ArrayList<ArrayList<String>> val) { 			//wstawienie danych modelu
            data = val;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length; 				//liczba kolumn
        }

        @Override
        public int getRowCount() {
            return data.size();					//liczba rowkow
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row).get(col);		//pobrane elementu z podanej komorki tabeli w wieszu row i kolumnie col
        }
    }
}
