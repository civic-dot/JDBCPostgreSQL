package repository;

import model.BattleMember;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class BattleMemberRep {

    public int updateSQl(Connection connection, BattleMember battleMember) {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE BATTLE_MEMBERS SET BATTLE_NAME=?,RESULT=?,WARSHIP_NAME=? Where ID=?")) {

            stmt.setString(1, battleMember.getBattleName());
            stmt.setString(2, battleMember.getResult());
            stmt.setString(3, battleMember.getName());
            stmt.setInt(4, battleMember.getId());
            return stmt.executeUpdate();

        } catch (SQLException e) {
            if (e.getErrorCode() == 335544517) // При срабатывании триггера
                JOptionPane.showMessageDialog(null, "Корабль не может принимать участие в бою до дня ввода в эксплуатацию или после даты списания");
            else
                JOptionPane.showMessageDialog(null, e.getMessage());


        }
        return 0;

    }

    public BattleMemberRep() {
    }

    public int insertSQL(Connection connection, BattleMember battleMember) {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO BATTLE_MEMBERS (ID,BATTLE_NAME,RESULT,WARSHIP_NAME) VALUES (?,?,?,?)")) {
            stmt.setInt(1, battleMember.getId());
            stmt.setString(2, battleMember.getBattleName());
            stmt.setString(3, battleMember.getResult());
            stmt.setString(4, battleMember.getName());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 335544517) // При срабатывании триггера
                JOptionPane.showMessageDialog(null, "Корабль не может принимать участие в бою до дня ввода в эксплуатацию или после даты списания");
            else
                JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return 0;
    }

    public List<BattleMember> selectSQL(Connection connection) {
        List<BattleMember> dataList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("Select id,result,battle_name,WARSHIP_NAME from Battle_members"); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                dataList.add(new BattleMember(rs.getInt("id"), rs.getString("result"), rs.getString("battle_name"), rs.getString("WARSHIP_NAME")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return dataList;
    }

    public int deleteRowsSQL(Connection connection, Collection<Integer> battleMemberCollection) {
        try (PreparedStatement stmt = connection.prepareStatement("Delete from BATTLE_MEMBERS Where ID=?")) {
            for (int x : battleMemberCollection) {
                stmt.setInt(1, x);
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
        columnsHeader.add("ID");
        columnsHeader.add("Result");
        columnsHeader.add("Name of ship");
        columnsHeader.add("Battle name");

        return columnsHeader;

    }
}

