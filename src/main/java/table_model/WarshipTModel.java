package table_model;

import model.Battle;
import model.Warship;
import repository.BattleRep;
import repository.WarshipRep;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public class WarshipTModel extends TableModel {
    public DefaultTableModel getModel(Connection connection, WarshipRep warshipRep, DefaultTableModel model) {
        List<String> columnName = warshipRep.getColumnHeaders();
        model = fillTableHeader(model, columnName);
        Object[][] array = new Object[columnName.size()][];
        List<Warship> warShipList = warshipRep.selectSQL(connection);
        for (int i = 0; i < columnName.size(); i++) {
            array[i] = new Object[warShipList.size()];
        }
        for (int j = 0; j < warShipList.size(); j++) {
            array[0][j] = warShipList.get(j).getName();
            array[1][j] = warShipList.get(j).getClas();
            array[2][j] = warShipList.get(j).getCommissionDate();
            array[3][j] = warShipList.get(j).getDecommissionDate();
            model.addRow(new Object[]{array[0][j], array[1][j], array[2][j], array[3][j]});
        }
        return model;
    }
}
