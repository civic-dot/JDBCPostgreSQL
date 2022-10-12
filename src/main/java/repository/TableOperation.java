package repository;

import model.BaseModel;
import java.sql.Connection;
import java.util.List;


public interface TableOperation {
    List<? extends BaseModel> selectSQL(Connection connection);
    void deleteSQL(Connection connection, BaseModel model);
    void insertSQL(Connection connection, BaseModel model);
    void updateSQL(Connection connection, BaseModel oldModel, BaseModel newModel);
    List<String> getColumnHeaders();
}
