package DP_P3;


import java.sql.*;
import java.util.ArrayList;

public class OVChipkaartDaoOracleDB extends OracleBaseDAO implements OVChipkaartDao {

    private OVChipkaart toOVC(ResultSet resultSet) throws SQLException {
        OVChipkaart ovChipkaart = new OVChipkaart(
                resultSet.getInt("KAARTNUMMER"),
                resultSet.getDate("GELDIGTOT"),
                resultSet.getInt("KLASSE"),
                resultSet.getDouble("SALDO"),
                resultSet.getInt("REIZIGERID")
        );
        return ovChipkaart;
    }


    public ArrayList<OVChipkaart> findAll() {

        ProductDaoOracleDB productDaoOracleDB = new ProductDaoOracleDB();
        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();

        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();
        ArrayList<Reiziger> reizigers = reizigerDaoOracleDB.findAll();

        try {
            for (Reiziger r : reizigers) {
                for(OVChipkaart ov : r.getOvChipkaarten()) {
                    ov.setProducten(productDaoOracleDB.findByKaart(ov));
                    ovChipkaarten.add(ov);
                }
            }
            return ovChipkaarten;
        }
        catch (Exception e) {
            System.out.println(" !!!  ALERT OVChipkaartDao findAll() Failure!!!");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<OVChipkaart> findByProduct(Product product) {

        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();
        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();

        try {
            Connection conn = super.getConnection();
            String query =  "SELECT ov.kaartnummer, ov.geldigtot, ov.klasse, ov.saldo, ov.reizigerid " +
                            "FROM product p, ov_chipkaart ov, ov_chipkaart_product ovp " +
                            "WHERE ov.kaartnummer = ovp.kaartnummer " +
                            "AND p.productnummer = ovp.productnummer " +
                            "AND p.productnummer = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, product.getProductNummer());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int kaartnummer = resultSet.getInt("kaartnummer");
                Date geldigtot = resultSet.getDate("geldigtot");
                int klasse = resultSet.getInt("klasse");
                double saldo = resultSet.getDouble("saldo");
                int reizigerid = resultSet.getInt("reizigerid");

                Reiziger reiziger = reizigerDaoOracleDB.findByReizigerId(reizigerid);
                OVChipkaart ovChipkaart = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, reiziger);
                ovChipkaarten.add(ovChipkaart);
            }
            return ovChipkaarten;
        }
        catch (SQLException e) {
            System.out.println("!!! Alert, OVCHIPKAARTDAO findByProduct() Failure!!");
            e.printStackTrace();
        }
        return null;
    }

    public OVChipkaart findByKaartnummer(int kNummer) {

        ReizigerDaoOracleDB reizigerDaoOracleDB = new ReizigerDaoOracleDB();

        try {

            Connection conn = super.getConnection();
            String query =  "SELECT * FROM ov_chipkaart" +
                            " WHERE kaartnummer = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, kNummer);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                OVChipkaart ovChipkaart = toOVC(resultSet);
                int reizigerid = resultSet.getInt("reizigerid");

                Reiziger reiziger = reizigerDaoOracleDB.findByReizigerId(reizigerid);

                return ovChipkaart;
            }
        }
        catch (SQLException e) {
            System.out.println("!!! Alert, OVCHIPKAARTDAO findByKaartnummer() Failure!!");
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<OVChipkaart> findByKaarthouder(Reiziger kaarthouder) {
        ArrayList<OVChipkaart> ovChipkaarten = new ArrayList<>();

        try {
            Connection conn = super.getConnection();
            String query =  "SELECT * FROM ov_chipkaart " +
                            "WHERE reizigerid = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, kaarthouder.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int kaartnummer = resultSet.getInt("kaartnummer");
                Date geldigtot = resultSet.getDate("geldigtot");
                int klasse = resultSet.getInt("klasse");
                double saldo = resultSet.getDouble("saldo");

                OVChipkaart ov = new OVChipkaart(kaartnummer, geldigtot, klasse, saldo, kaarthouder);
                ovChipkaarten.add(ov);
            }

            return ovChipkaarten;

        }
        catch (SQLException e) {
            System.out.println("!!! Alert, OVCHIPKAARTDAO findByKaarthouder() Failure!!");
            e.printStackTrace();
        }
        return null;
    }

    public void voegProductToeAanKaart(int prodId, OVChipkaart ov, Product p) {
        try{
            Connection connection = getConnection();

            String query = "INSERT INTO ov_chipkaart_product VALUES(?,?,?,?,CURRENT_DATE)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,prodId);
            preparedStatement.setInt(2,ov.getKaartNummer());
            preparedStatement.setInt(3,p.getProductNummer());
            preparedStatement.setString(4,"actief");
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("FAILURE voegProductToe()!!");
        }
    }

    public boolean verwijderProductVanKaart(OVChipkaart ov, Product p) {
        try{
            Connection connection = getConnection();
            String query = "DELETE FROM ov_chipkaart_product WHERE kaartnummer = ? AND productnummer = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,ov.getKaartNummer());
            preparedStatement.setInt(2, p.getProductNummer());
            preparedStatement.executeUpdate();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("verwijderProduct() FAILURE!!");
            return false;
        }
    }

    public OVChipkaart save(OVChipkaart ovChipkaart) {
        Connection connection = super.getConnection();

        String query = "INSERT INTO OV_CHIPKAART (KAARTNUMMER, GELDIGTOT, KLASSE, SALDO, REIZIGERID) VALUES ( ?, ?, ?, ?, ?)";


        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ovChipkaart.getKaartNummer());
            stmt.setDate(2, ovChipkaart.getGeldigTot());
            stmt.setInt(3, ovChipkaart.getKlasse());
            stmt.setDouble(4, ovChipkaart.getSaldo());
            stmt.setInt(5, ovChipkaart.getReizigerId());

            stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return ovChipkaart;
    }

    public OVChipkaart update(OVChipkaart ovChipkaart) {
       Connection connection = super.getConnection();

       try {
           String query =   "UPDATE OV_CHIPKAART SET GELDIGTOT = ?, KLASSE = ?, SALDO = ?, REIZIGERID = ?\r\n" +
                            " WHERE kaartnummer = ?";

           PreparedStatement statement = connection.prepareStatement(query);
           statement.setDate(1, ovChipkaart.getGeldigTot());
           statement.setInt(2, ovChipkaart.getKlasse());
           statement.setDouble(3, ovChipkaart.getSaldo());
           statement.setInt(4, ovChipkaart.getEigenaar().getId());
           statement.setInt(5, ovChipkaart.getKaartNummer());
       }
       catch (SQLException e) {
           System.out.println("ALERT!!! OVCHIPKAARTDAO Update Failure!!");
           e.printStackTrace();
       }
       return ovChipkaart;
    }

    public boolean delete(OVChipkaart ovChipkaart) {

        try {
            Connection connection = getConnection();
            String query = "DELETE FROM OV_CHIPKAART WHERE KAARTNUMMER = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, ovChipkaart.getKaartNummer());
            statement.executeUpdate();
            statement.close();

            return true;

        }catch (Exception e) {
            System.out.println("ALERT!!! OVCHIPKAARTDAO Delete fail");
            e.printStackTrace();
        }
        return false;
    }
}
