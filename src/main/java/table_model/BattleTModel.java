package table_model;

import model.Battle;
import model.BattleMember;
import repository.BattleMemberRep;
import repository.BattleRep;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public class BattleTModel extends TableModel{

    public DefaultTableModel getModel(Connection connection, BattleRep battleRep, DefaultTableModel model){
        List<String > columnName = battleRep.getColumnHeaders();
        model = fillTableHeader(model,columnName);
        Object[][] array = new Object[columnName.size()][];
        List<Battle> battleList = battleRep.selectSQL(connection);
        for (int i = 0; i < columnName.size(); i++) {
            array[i] = new Object[battleList.size()];
        }
        for (int j = 0; j < battleList.size(); j++) {
            array[0][j] = battleList.get(j).getBattleName();
            array[1][j] = battleList.get(j).getBattleDate();
            model.addRow(new Object[]{array[0][j], array[1][j]});
        }
        return model;
    }
}
