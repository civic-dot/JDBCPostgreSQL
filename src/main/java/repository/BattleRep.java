package repository;

import model.Battle;
import utils.DateUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BattleRep {

    public int insertSQL(Connection connection, Battle battle) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO BATTLES (BATTLE_NAME,BATTLE_DATE) VALUES (?,?)")) {
            stmt.setString(1, battle.getBattleName());
            stmt.setDate(2, DateUtils.javaToSqlDate(battle.getBattleDate()));
            return stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    public List<String> selectBattleNames(Connection connection) {
        List<String> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select BATTLE_NAME from BATTLES");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataList.add(rs.getString("BATTLE_NAME"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;
    }

    public int updateSQL(Connection connection, Battle battle) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE BATTLES SET BATTLE_DATE=? Where BATTLE_NAME=?")) {
            stmt.setString(2, battle.getBattleName());
            stmt.setDate(1, DateUtils.javaToSqlDate(battle.getBattleDate()));
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return 0;
    }


    public List<Battle> selectSQL(Connection connection) {
        List<Battle> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select battle_name,battle_date from Battles");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dataList.add(new Battle(rs.getString("battle_name"), rs.getDate("battle_date")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;

    }

    public int deleteRowsSQL(Connection connection, Collection<String> battleCollection) {
        try (PreparedStatement stmt = connection.prepareStatement("Delete from BATTLES Where BATTLE_NAME=?")) {
            for (String x : battleCollection) {
                stmt.setString(1, x);
                stmt.addBatch();
            }
            return stmt.executeBatch().length;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }


    public List<String> getColumnHeaders() {
        List<String> columnsHeader = new ArrayList<>();
        columnsHeader.add("Name of battle");
        columnsHeader.add("Battle date");
        return columnsHeader;
    }

}
