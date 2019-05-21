package DP_P3;

import java.sql.Date;
import java.util.ArrayList;

public class Reiziger {
    private String achternaam;
    private String tussenvoegsel;
    private String voorletters;
    private Date gbdatum;
    private int reizigerId;
    private ArrayList<OVChipkaart> ovChipkaarten;

    public Reiziger(int id, String an, String tv, String vl, Date gbdatum) {
        this.achternaam = an;
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.gbdatum = gbdatum;
        this.reizigerId = id;
        this.ovChipkaarten = new ArrayList<OVChipkaart>();
    }

    public Reiziger() {
        this.ovChipkaarten = new ArrayList<OVChipkaart>();
    }

    public int getId() { return reizigerId;}
    public void setId(int id) { this.reizigerId = id;}

    public String getActhernaam() { return achternaam;}
    public void setAchternaam(String n) { achternaam = n;}

    public String getVoorletters() { return voorletters;}
    public void setVoorletters(String vl) { this.voorletters = vl;}

    public String getTussenvoegsel() { return tussenvoegsel;}
    public void setTussenvoegsel(String t) { this.tussenvoegsel = t;}

    public Date getGBdatum() { return gbdatum;}
    public void setGBdatum(Date date) {this.gbdatum = date;}

    public ArrayList<OVChipkaart> getOvChipkaarten() { return ovChipkaarten;}

    public boolean addOvChipkaart(OVChipkaart ovChipkaart) {
        if(!this.ovChipkaarten.contains(ovChipkaart)){
            return this.ovChipkaarten.add(ovChipkaart);
        }
        else{
            return false;
        }
    }

    public boolean deleteOvChipkaart(OVChipkaart ovChipkaart) {
        if (this.ovChipkaarten.contains(ovChipkaart)){
            return this.ovChipkaarten.remove(ovChipkaart);
        }
        else {
            return false;
        }
    }

    public String getNaam() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.voorletters).append(" ");
        if (this.tussenvoegsel != null) {
            sb.append(this.tussenvoegsel).append(" ");
        }
        sb.append(this.achternaam).append(" ");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ").append(this.getId());
        sb.append(this.getNaam()).append(", GBdatum: ");
        sb.append(this.getGBdatum().toLocalDate().toString());
        sb.append(" ]");
        if (!this.ovChipkaarten.isEmpty()) {
            sb.append("   ");
            for (OVChipkaart ovChipkaart : this.ovChipkaarten) {
                sb.append("\n");
                sb.append(ovChipkaart);
            }
        }
        return sb.toString();
    }
}
