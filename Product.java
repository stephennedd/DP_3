package DP_P3;

import java.util.ArrayList;

public class Product {
    private int productNummer;
    private String productNaam;
    private String beschrijving;
    private double prijs;
    private ArrayList<OVChipkaart> opOvChipkaarten = new ArrayList<OVChipkaart>();

    public Product(int pn, String pnaam, String bv, double pr)  {
        this.productNummer = pn;
        this.productNaam = pnaam;
        this.beschrijving = bv;
        this.prijs = pr;
    }

    public int getProductNummer() {
        return this.productNummer;
    }
    public void setProductNummer(int pn) {
        this.productNummer = pn;
    }

    public String getProductNaam() {
        return this.productNaam;
    }

    public void setProductNaam(String nm) {
        this.productNaam = nm;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String bes) {
        this.beschrijving = bes;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public ArrayList<OVChipkaart> getopOvChipkaarten() { return opOvChipkaarten; }

    public void setOpOvChipkaarten(ArrayList<OVChipkaart> opOvChipkaarten) {
        this.opOvChipkaarten = opOvChipkaarten;
    }

    @Override
    public String toString() {
        String a = "    Product [product Nummer= " + productNummer + ", Product Naam = " + productNaam + ", beschrijving = " + beschrijving + ", prijs " + prijs;
        a += " OVChipkaarten: ";
        for (OVChipkaart ovChipkaart : this.opOvChipkaarten){
            a += ovChipkaart.getKaartNummer() + " " + ovChipkaart.getKlasse();
        }
        return a;
    }


}
