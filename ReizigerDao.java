package DP_P3;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ReizigerDao {
    public ArrayList<Reiziger> findAll() throws SQLException;
    public ArrayList<Reiziger> findByGBdatum(Date GBdatum) throws SQLException;
    public Reiziger save(Reiziger reiziger) throws SQLException;
    public Reiziger update(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;
}
