import ui.SeaBattleGUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;


public class Application {


    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            FBConnection.properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Connection connection = FBConnection.getConnection();
        SeaBattleGUI seaBattleGUI = new SeaBattleGUI(connection);

    }
}
