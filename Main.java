package DP_P3;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
        List<Product> products = new ArrayList<>();
        ProductDao productDao = new ProductDaoOracleDB();
        products = productDao.findAll();
    }
}
