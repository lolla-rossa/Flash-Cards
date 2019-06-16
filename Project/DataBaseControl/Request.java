package DataBaseControl;

import MainForm.ErrorMessageForm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public interface Request {
    default Connection CreateConnection() {
        try {
           Class.forName("org.sqlite.JDBC");
           final Connection connection = DriverManager.getConnection("jdbc:sqlite:DataBase.db");
           System.out.println("Connection");
           return connection;
        }catch (Exception e){
            ErrorMessageForm error = new ErrorMessageForm("Неудалось установить соединение с базой данных");
            error.pack();
        }
        return null;
    }
    void getRequest(String sql);
}
