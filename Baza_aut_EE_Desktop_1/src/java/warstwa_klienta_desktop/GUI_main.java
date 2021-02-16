
package warstwa_klienta_desktop;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.ejb.EJB;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import warstwa_biznesowa_ejb.Fasada_warstwy_biznesowej_ejbRemote;


/**
 *
 * @author Paweł Esmanowicz
 */
public class GUI_main implements ActionListener {

    @EJB
    private static Fasada_warstwy_biznesowej_ejbRemote fasada;

        
    static JPanel cards;  	

    static CardLayout cl; 	
    static Pusty_form card0 = new Pusty_form();		
    static Produkt_form card1 = new Produkt_form();	
    static Produkty_form card2 = new Produkty_form();

    final static String PUSTY = "Pusty";
    final static String PRODUKT = "Dodaj auto";
    final static String PRODUKTY = "Lista aut";

    static public Fasada_warstwy_biznesowej_ejbRemote getFacade() {
        return fasada;
    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        menuBar = new JMenuBar(); 			

        menu = new JMenu("A Menu");
        menu.setMnemonic(KeyEvent.VK_A);			
        menuBar.add(menu);		 		

        menuItem = new JMenuItem(PRODUKT, KeyEvent.VK_P);	
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK)); 		
        menuItem.addActionListener(this);			
        menu.add(menuItem);				

        menuItem = new JMenuItem(PRODUKTY);
        menuItem.setMnemonic(KeyEvent.VK_R);    
        menuItem.addActionListener(this);			
        menu.add(menuItem);				
        menuItem = new JMenuItem(PUSTY);
        menuItem.setMnemonic(KeyEvent.VK_U);	
        menuItem.addActionListener(this);		
        menu.add(menuItem);			
        menu.addSeparator();

        submenu = new JMenu("A submenu"); 		
        submenu.setMnemonic(KeyEvent.VK_A);	

        menuItem = new JMenuItem(PUSTY);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));	
        menuItem.addActionListener(this);		
        submenu.add(menuItem);		

        menuItem = new JMenuItem(PUSTY);
        menuItem.setMnemonic(KeyEvent.VK_S);	
        menuItem.addActionListener(this);		
        submenu.add(menuItem);		

        menu.add(submenu);	           

        menu = new JMenu("Inne Menu");  		
        menu.setMnemonic(KeyEvent.VK_I);		
        menuBar.add(menu);			

        return menuBar;			
    }

    public Container createContentPane() {

        
        card1.init();
        card2.init();
        cards = new JPanel(new CardLayout());
        cards.add(card0, PUSTY);		
        cards.add(card1, PRODUKT);		
        cards.add(card2, PRODUKTY);	

        JPanel p1 = new JPanel();		
        p1.add(cards, BorderLayout.CENTER);	
        return p1;			
    }

    public static void updateDodaj_auto() {
        card2.table_content();		
        cl.show(cards, PRODUKTY);	

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JMenuItem source = (JMenuItem) (e.getSource()); 	
        cl = (CardLayout) (cards.getLayout());
        switch (source.getText()) {
            case PRODUKT:
                cl.show(cards, PRODUKT);  		
                break;
            case PRODUKTY:
                updateDodaj_auto(); 		
                break;
            case PUSTY:
                cl.show(cards, PUSTY);		
                break;
            default:
                break;
        }
    }

    private static void createAndShowGUI() {		
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        GUI_main demo = new GUI_main();
        frame.setJMenuBar(demo.createMenuBar());	
        frame.setContentPane(demo.createContentPane());
        frame.setVisible(true);			
    }
      public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            createAndShowGUI();
            }
        });
    }
}

