package table_model;

import model.Battle;
import model.Country;
import repository.BattleRep;
import repository.CountryRep;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.util.List;

public class CountryTModel extends TableModel {
    public DefaultTableModel getModel(Connection connection, CountryRep countryRep, DefaultTableModel model) {
        List<String> columnName = countryRep.getColumnHeaders();
        model = fillTableHeader(model, columnName);
        Object[][] array = new Object[columnName.size()][];
        List<Country> countryList = countryRep.selectSQL(connection);
        for (int i = 0; i < columnName.size(); i++) {
            array[i] = new Object[countryList.size()];
        }
        for (int j = 0; j < countryList.size(); j++) {
            array[0][j] = countryList.get(j).getName();
            array[1][j] = countryList.get(j).getSide();
            model.addRow(new Object[]{array[0][j], array[1][j]});
        }
        return model;
    }
}
