package table_model;

import model.BattleMember;
import repository.BattleMemberRep;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public class BattleMemberTModel extends TableModel {

    public DefaultTableModel getModel(Connection connection, BattleMemberRep battleMemberRep, DefaultTableModel model) {
        List<String> columnName = battleMemberRep.getColumnHeaders();
        model = fillTableHeader(model, columnName);
        Object[][] array = new Object[columnName.size()][];
        List<BattleMember> battleMemberList = battleMemberRep.selectSQL(connection);
        for (int i = 0; i < columnName.size(); i++) {
            array[i] = new Object[battleMemberList.size()];
        }
        for (int j = 0; j < battleMemberList.size(); j++) {
            array[0][j] = battleMemberList.get(j).getId();
            array[1][j] = battleMemberList.get(j).getResult();
            array[2][j] = battleMemberList.get(j).getBattleName();
            array[3][j] = battleMemberList.get(j).getName();
            model.addRow(new Object[]{array[0][j], array[1][j], array[2][j], array[3][j]});
        }
        return model;
    }

}
