package DP_P3;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productNummer;
    private String productNaam;
    private String beschrijving;
    private double prijs;
    private List<OVChipkaart> ovChipkaarten = new ArrayList<OVChipkaart>();

    public Product(int pn, String pnaam, String bv, double pr)  {
        this.productNummer = pn;
        this.productNaam = pnaam;
        this.beschrijving = bv;
        this.prijs = pr;
    }

    public Product() {}

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

    public List<OVChipkaart> getMijnChipkaarten() { return ovChipkaarten; }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public String toString() {
        return "    Product [product Nummer= " + productNummer + ", Product Naam = " + productNaam + ", beschrijving = " + beschrijving + ", prijs " + prijs + ", OvChipkaarten : " + ovChipkaarten + "]";
    }


}
