package DP_P3;

import java.sql.Date;
import java.util.ArrayList;

public class OVChipkaart {
    private int reizigerId;
    private int kaartNummer;
    private Date geldigTot;
    private int klasse;
    private double saldo;
    private Reiziger eigenaar;
    private ArrayList<Product> producten = new ArrayList<>();


    public OVChipkaart() {}

    public OVChipkaart(int kn, Date gt, int kl, double sal, int reizigerId ) {
        this.kaartNummer = kn;
        this.geldigTot = gt;
        this.klasse = kl;
        this.saldo = sal;
        this.reizigerId = reizigerId;
    }

    public OVChipkaart(int kn, Date gt, int kl, double sal, Reiziger eigenaar) {
        this.kaartNummer = kn;
        this.geldigTot = gt;
        this.klasse = kl;
        this.saldo = sal;
        this.eigenaar = eigenaar;
    }

    public int getReizigerId() {return reizigerId;}
    public void setReizigerId(int id) { reizigerId = id;}

    public int getKaartNummer() {return kaartNummer;}
    public void setKaartNummer(int num) {kaartNummer = num;}

    public Date getGeldigTot() {return geldigTot;}
    public void setGeldigTot(Date datum) { geldigTot = datum;}

    public int getKlasse() {return klasse;}
    public void setKlasse(int kl) {klasse = kl;}

    public double getSaldo() {return saldo;}
    public void setSaldo(double s) {saldo = s;}

    public Reiziger getEigenaar() { return eigenaar;}
    public void setEigenaar(Reiziger reiziger) { this.eigenaar = reiziger;}

    public ArrayList<Product> getProducten() { return producten; }
    public void voegProductenToe(ArrayList<Product> producten) {
        this.producten = producten;
    }

    @Override
    public String toString() {
        String a = "   OV-Chipkaart: [ Kaartnummer : " + this.kaartNummer + ", Geldig tot: " + this.geldigTot + ", Saldo: " + this.saldo + ", klasse: " + this.klasse + ", reizigerID: " + this.reizigerId + " ]";
        a += "\nProducten:  \n";
        for (Product product : this.producten)
            a += product.getProductNummer() + " " + product.getProductNaam() + " " + product.getBeschrijving() + "\n";


        return a;
    }
}
