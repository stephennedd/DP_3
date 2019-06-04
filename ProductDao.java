package DP_P3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface ProductDao {
    public ArrayList<Product> findAll() throws SQLException;
    public ArrayList<Product> findByKaart(OVChipkaart ovChipkaart) throws SQLException, ParseException;
    public Product save(Product product) throws SQLException;
    public Product update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
}
