package table_model;

import repository.BattleMemberRep;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public abstract class TableModel {
    public DefaultTableModel fillTableHeader(DefaultTableModel model, List<String> columnName) {

        model = new DefaultTableModel();
        for (String c : columnName) {
            model.addColumn(c);
        }
        return model;
    }
}
