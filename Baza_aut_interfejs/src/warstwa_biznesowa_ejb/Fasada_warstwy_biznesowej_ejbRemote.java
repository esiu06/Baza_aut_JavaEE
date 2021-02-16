/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warstwa_biznesowa_ejb;

import java.util.ArrayList;
import javax.ejb.Remote;
import warstwa_biznesowa.dto.Produkt_dto;

/**
 *
 * @author Paweł Esmanowicz
 */
@Remote
public interface Fasada_warstwy_biznesowej_ejbRemote {
    public void utworz_produkt(Produkt_dto produkt_dto);
    public Produkt_dto dane_produktu();
    public ArrayList<ArrayList<String>> items();
    public ArrayList<Produkt_dto> items_(); 
    public int count();
    public ArrayList<Produkt_dto> findRange(int[] range);
    public boolean isStan() ;
    public void setStan(boolean stan); 
    public boolean edit(Produkt_dto o_przed, Produkt_dto o_update);
    public void remove(Produkt_dto p);
    public void zapisz();
    public void pobierz(); 
}
