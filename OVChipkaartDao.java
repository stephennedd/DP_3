package DP_P3;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OVChipkaartDao {
    public OVChipkaart save(OVChipkaart ovChipkaart) throws SQLException;
    public ArrayList<OVChipkaart> findAll() throws SQLException;
    public ArrayList<OVChipkaart> findByProduct(Product product) throws SQLException;
    public OVChipkaart findByKaartnummer(int kNummer) throws SQLException;
    public void voegProductToeAanKaart(int prodId, OVChipkaart ov, Product p) throws SQLException;
    public boolean verwijderProductVanKaart(OVChipkaart ov, Product p) throws SQLException;
    public ArrayList<OVChipkaart> findByKaarthouder(Reiziger kaartHouder) throws SQLException;
    public OVChipkaart update(OVChipkaart ovChipkaart) throws SQLException;
    public boolean delete(OVChipkaart ovChipkaart) throws Exception;
}
