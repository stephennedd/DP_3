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

    @Override
    public ArrayList<Product> findAll() {

        Connection connection = super.getConnection();
        ArrayList<Product> producten = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM PRODUCT";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                producten.add(toProduct(resultSet));
            }
            return producten;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> findByProductnummer(int PrNum) {

        Connection connection = super.getConnection();
        ArrayList<Product> producten = new ArrayList<>();

        try {
            String query = "SELECT * FROM PRODUCT WHERE PRODUCTNUMMER = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, PrNum);
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
