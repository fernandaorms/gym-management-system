package src.javafxmvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.javafxmvc.model.domain.CheckOut;

public class CheckOutDAO {
    
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean insert(CheckOut checkOut) {
        String sql = "INSERT INTO CheckOut (data, hora, checkIn_id) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1,  java.sql.Date.valueOf(checkOut.getData()));
            stmt.setTime(2,  java.sql.Time.valueOf(checkOut.getHora()));
            stmt.setInt(3, checkOut.getCheckin_id());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean update(CheckOut checkOut) {
        String sql = "UPDATE checkOut SET data=?, hora=?, checkIn_id=? WHERE idCheckOut=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(checkOut.getData()));
            stmt.setTime(2, java.sql.Time.valueOf(checkOut.getHora()));
            stmt.setInt(3, checkOut.getCheckin_id());
            stmt.setInt(4, checkOut.getIdCheckOut());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(CheckOut checkOut) {
        String sql = "DELETE FROM checkOut WHERE idCheckOut=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, checkOut.getIdCheckOut());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<CheckOut> list() {
        String sql = "SELECT * FROM checkOut ORDER BY data ASC";
        List<CheckOut> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                CheckOut checkOut = new CheckOut();               
                checkOut.setIdCheckOut(resultado.getInt("idCheckOut"));
                checkOut.setData(resultado.getDate("data").toLocalDate());
                checkOut.setHora(resultado.getTime("hora").toLocalTime());
                checkOut.setCheckin_id(resultado.getInt("checkIn_id"));
                retorno.add(checkOut);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean search(int checkIn_id) { 
        String sql = "SELECT idCheckOut FROM CheckOut WHERE checkIn_id=?";
        boolean retorno = false;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, checkIn_id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
}
