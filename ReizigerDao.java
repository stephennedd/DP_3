package DP_P3;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ReizigerDao {
    public List<Reiziger> findAll() throws SQLException;
    public List<Reiziger> findByGBdatum(Date GBdatum) throws SQLException;
    public Reiziger save(Reiziger reiziger) throws SQLException;
    public Reiziger update(Reiziger reiziger) throws SQLException;
    public boolean delete(Reiziger reiziger) throws SQLException;
}
