package repository;

import model.Warship;
import utils.DateUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WarshipRep {

    public WarshipRep() {
    }

    public int insertSQL(Connection connection, Warship warship) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO WARSHIPS (Name,Class,COMMISSION_DATE,DECOMMISSION_DATE) VALUES (?,?,?,?)")) {
            stmt.setString(1, warship.getName());
            stmt.setString(2, warship.getClas());
            stmt.setDate(3, DateUtils.javaToSqlDate(warship.getCommissionDate()));
            stmt.setDate(4, DateUtils.javaToSqlDate(warship.getDecommissionDate()));
            return stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;

    }


    public int updateSQL(Connection connection, Warship warship) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE WARSHIPS SET CLASS=?,COMMISSION_DATE=?,DECOMMISSION_DATE=? Where Name=?")) {
            stmt.setString(1, warship.getClas());
            stmt.setDate(2, DateUtils.javaToSqlDate(warship.getCommissionDate()));
            stmt.setDate(3, DateUtils.javaToSqlDate(warship.getDecommissionDate()));
            stmt.setString(4, warship.getName());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    public int deleteRowsSQL(Connection connection, Collection<String> warshipCollection) {
        try (PreparedStatement stmt = connection.prepareStatement("Delete from WARSHIPS Where NAME=?")) {
            for (String x : warshipCollection) {
                stmt.setString(1, x);
                stmt.addBatch();
            }
            return stmt.executeBatch().length;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    public List<String> selectShipNames(Connection connection) {
        List<String> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select NAME from WARSHIPS");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataList.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;
    }

    public List<Warship> selectSQL(Connection connection) {
        List<Warship> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select NAME,CLASS,COMMISSION_DATE,DECOMMISSION_DATE from WARSHIPS");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dataList.add(new Warship(rs.getString("name"), rs.getString("class"), rs.getDate("COMMISSION_DATE"), rs.getDate("DECOMMISSION_DATE")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;

    }

    public List<String> getColumnHeaders() {
        List<String> columnsHeader = new ArrayList<>();
        columnsHeader.add("Name of ship");
        columnsHeader.add("Class");
        columnsHeader.add("Commission date");
        columnsHeader.add("Decommission date");
        return columnsHeader;
    }

}
