
package warstwa_klienta_desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.PatternSyntaxException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import warstwa_biznesowa.dto.Produkt_dto;

/**
 *
 * @author Paweł Esmanowicz
 */
public class Produkt_form extends JPanel implements ActionListener  {
    JLabel lproducent = new JLabel("Producent"); 		
    JTextField producent = new JTextField(15);
    JLabel lmodel = new JLabel("Model"); 		
    JTextField model = new JTextField(15);
    JLabel lrodzaj_paliwa = new JLabel("Rodzaj paliwa"); 		
    JTextField rodzaj_paliwa = new JTextField(15);
    JLabel lpojemnosc = new JLabel("Pojemnosc"); 		
    JTextField pojemnosc = new JTextField(15);
    JLabel lprzebieg = new JLabel("Przebieg"); 		
    JTextField przebieg = new JTextField(15);
    JLabel lrok_produkcji = new JLabel("Rok produkcji");		
    JTextField rok_produkcji = new JTextField(15);
    JLabel lcena = new JLabel("Cena");		 
    JTextField cena = new JTextField(15);
    JLabel ldata = new JLabel("Data zakupu");		 
    JTextField data = new JTextField(15);
    JButton dodaj_produkt = new JButton("Dodaj produkt");	 
 
public void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  	
        add(lproducent);			
        add(lproducent);			
        add(producent);
        add(lmodel);			
        add(model);
        add(lrodzaj_paliwa);			
        add(rodzaj_paliwa);
        add(lpojemnosc);			
        add(pojemnosc);
        add(lprzebieg);			 
        add(przebieg);
        add(lrok_produkcji);			 
        add(rok_produkcji);
        add(lcena);			 
        add(cena);
        add(ldata);
        add(data);
        dodaj_produkt.addActionListener(this);	
		                      	 	
        add(dodaj_produkt);			
    }

  public String content_validate(JTextField val, int typ) {
        String s = val.getText();
        if (s.equals("") || s.length() > 15) { 		
            JOptionPane.showMessageDialog(this, "Lancuch danych jest dluzszy niz 15 lub jest pusty");
            return null;
        } else {
            s = s.replaceAll(" ", "_"); 		
            val.setText(s);
        }
        if (typ == 1) {		                          
            try {                  
                Float.parseFloat(s);
            } catch (NumberFormatException e) {
		 JOptionPane.showMessageDialog(this, "Blad formatu danych liczbowych");
              	 return null;
            }
        }
        return s;
    }
 public String[] form_produkt() {
        if (content_validate(producent, 0) == null) 			
            return null;
        if (content_validate(model, 0) == null) 			
            return null;
        if (content_validate(rodzaj_paliwa, 0) == null) 			
            return null; 
        if (content_validate(pojemnosc, 1) == null) 			
            return null;  
        if (content_validate(przebieg, 1) == null) 			
            return null; 
        if (content_validate(rok_produkcji, 1) == null) 			
            return null;  
        if (content_validate(cena, 1) == null) 			
            return null;
        
        String dane[] = {(String) producent.getText(), (String) model.getText(), (String) rodzaj_paliwa.getText(), (String) pojemnosc.getText(), (String) przebieg.getText(), (String) rok_produkcji.getText(), (String) cena.getText()};	//tablica z danymi produktu
        return dane;
    }

    public Date data() {
        if (content_validate(data, 0) == null)			
            return null;
        int rok, miesiac, dzien;
        String data1 = data.getText();
        try {
            String[] data2 = data1.split("-");	
            rok = Integer.parseInt(data2[2]);
            miesiac = Integer.parseInt(data2[1]);
            dzien = Integer.parseInt(data2[0]);
        } catch (PatternSyntaxException | NumberFormatException | ArrayIndexOutOfBoundsException e) {        
                          return null;
        }
        GregorianCalendar gc = new GregorianCalendar();		 
        gc.set(rok, miesiac - 1, dzien);			
        return gc.getTime();				
    }
 @Override
    public void actionPerformed(ActionEvent evt) {	
        String[] dane = form_produkt(); 		
        if (dane == null) {
            return;
        }
        Date data_ = data();			
        if (data_ == null) {
            return;
        }
        Produkt_dto produkt = new Produkt_dto(dane,data_);
        GUI_main.getFacade().utworz_produkt(produkt);
    }
}
