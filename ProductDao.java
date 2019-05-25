package DP_P3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface ProductDao {
    public List<Product> findAll() throws SQLException;
    public List<Product> findByProductnummer(int PrNum) throws SQLException, ParseException;
    public Product save(Product product) throws SQLException;
    public Product update(Product product) throws SQLException;
    public boolean delete(Product product) throws SQLException;
}
