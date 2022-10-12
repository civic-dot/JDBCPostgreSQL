package repository;

import model.Country;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountryRep {

    public int insertSQL(Connection connection, Country country) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO COUNTRIES (Name,Side) VALUES (?,?)")) {
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getSide());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }


    public int updateSQL(Connection connection, Country country) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE COUNTRIES SET side=? Where name=?")) {
            stmt.setString(1, country.getSide());
            stmt.setString(2, country.getName());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 0;
    }

    public CountryRep() {
    }

    public List<Country> selectSQL(Connection connection) {

        List<Country> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select name,side from COUNTRIES");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataList.add(new Country(rs.getString("name"), rs.getString("side")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;
    }


    public List<String> getColumnHeaders() {
        List<String> columnsHeader = new ArrayList<>();
        columnsHeader.add("Name of country");
        columnsHeader.add("Side");
        return columnsHeader;
    }


    public int deleteRowsSQL(Connection connection, Collection<String> countryCollection) {
        try (PreparedStatement stmt = connection.prepareStatement("Delete from COUNTRIES Where NAME=?")) {
            for (String x : countryCollection) {
                stmt.setString(1, x);
                stmt.addBatch();
            }
            return stmt.executeBatch().length;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

}
