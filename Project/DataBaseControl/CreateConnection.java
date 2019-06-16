package DataBaseControl;

import MainForm.ErrorMessageForm;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
    public Connection connection;

    public CreateConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
        } catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm("Неудалось установить соединение с базой данных");
            error.pack();
        }
    }
}
