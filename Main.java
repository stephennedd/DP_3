package DP_P3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        // initialize ReizigerDao and OVChipkaartDAO

        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();
        OVChipkaartDaoOracleDB ovChipkaartDaoOracleDB = new OVChipkaartDaoOracleDB();
        ProductDaoOracleDB productDaoOracleDB = new ProductDaoOracleDB();

        // Create OVChipkaart and Reiziger objects

        OVChipkaart ov1 = new OVChipkaart(12345, java.sql.Date.valueOf("2019-12-12"), 1, 10.00, 8);
        Reiziger r1 = new Reiziger(10, "Nedd", "", "S", java.sql.Date.valueOf("1995-01-01"));
        Product p1 = new Product(7, "Lightspeed", "Reis met hypersnelle trein. ", 100.00);

//        productDaoOracleDB.findAll();
        ovChipkaartDaoOracleDB.save(ov1);

        //voeg product toe aan kaart test.
//        ovChipkaartDaoOracleDB.voegProductToe(9, ov1, p1);
//        ovChipkaartDaoOracleDB.findByKaartnummer(12345);
    }
}

