package DP_P3;

import java.sql.*;
import java.util.ArrayList;

public class ProductDaoOracleDB extends OracleBaseDAO implements ProductDao {

    private Product toProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product(
        resultSet.getInt("PRODUCTNUMMER"),
        resultSet.getString("PRODUCTNAAM"),
        resultSet.getString("BESCHRIJVING"),
        resultSet.getDouble("PRIJS")
        );
        return product;
    }

    public ArrayList<Product> findAll() {

        OVChipkaartDaoOracleDB ovChipkaartDaoOracleDB = new OVChipkaartDaoOracleDB();
        Connection connection = super.getConnection();
        ArrayList<Product> producten = new ArrayList<>();
        ArrayList<OVChipkaart> ovChipkaarten = ovChipkaartDaoOracleDB.findAll();


        try {
            for(OVChipkaart ovChipkaart : ovChipkaarten) {
                System.out.println("Producten op kaart " + ovChipkaart.getKaartNummer() + ";\n");

                for (Product p : ovChipkaart.getProducten()) {
                    System.out.println("   ["+ p.getProductNummer() + ", " + p.getProductNaam() + ", " + p.getBeschrijving() + ", $" + p.getPrijs() + ",- ]");
                    producten.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println("ALERT!! PRODUCTDAO Findall() Failure!!!");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> findByKaart(OVChipkaart ovChipkaart) {

        Connection conn = super.getConnection();
        ArrayList<Product> producten = new ArrayList<Product>();

        try {
            String query =  "SELECT p.productnummer, p.productnaam, p.beschrijving, p.prijs " +
                            "FROM product p, ov_chipkaart ov, ov_chipkaart_product ovp " +
                            "WHERE ov.kaartnummer = ovp.kaartnummer " +
                            "AND p.productnummer = ovp.productnummer " +
                            "AND ov.kaartnummer = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, ovChipkaart.getKaartNummer());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                producten.add(toProduct(resultSet));
            }
            return producten;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product save(Product product) {

        Connection connection = super.getConnection();
        String query = "INSERT INTO PRODUCT (PRODUCTNUMMER, PRODUCTNAAM, BESCHRIJVING, PRIJS) VALUES ( ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductNummer());
            preparedStatement.setString(2, product.getProductNaam());
            preparedStatement.setString(3, product.getBeschrijving());
            preparedStatement.setDouble(4, product.getPrijs());

            preparedStatement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product update(Product product) {

        Connection connection = super.getConnection();
        String query = "UPDATE PRODUCT SET PRODUCTNAAM = ?, BESCHRIJVING = ?, PRIJS = ? WHERE PRODUCTNUMMER = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductNaam());
            preparedStatement.setString(2,product.getBeschrijving());
            preparedStatement.setDouble(3,product.getPrijs());
            preparedStatement.setInt(4, product.getProductNummer());

            preparedStatement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean delete(Product product) {

        Connection connection = super.getConnection();
        String query = "DELETE FROM PRODUCT WHERE PRODUCTNUMMER = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductNummer());

            return preparedStatement.executeUpdate(query) == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
