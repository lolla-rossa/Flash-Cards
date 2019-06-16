package DataBaseControl;

import MainForm.ErrorMessageForm;
import MainForm.SuccessMessageForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddNewUser extends CreateConnection implements Request {

    private String login ;
    private char[] password;
    boolean correctness = true;

    public void addUser(String login, char[] password){
        correctness = true;
        setLogin(login);
        setPassword(password);
        getRequestCheck("select login from Users where  login = '" + login + "'");
        if (!correctness) return;
        getRequest("insert into Users values ('"+ login+"','" +String.valueOf(password) + "' , null)");
        try{
            connection.close(); }
        catch (Exception e){}
    }
    private void setLogin(String newLogin) {
        login = newLogin;
    }
    private void setPassword(char[] newPassword) {
        password = newPassword;
    }

    public boolean check(){
        return correctness;
    }

    private void getRequestCheck(String sql){
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            if (!resultSet.isClosed()) {
                ErrorMessageForm error = new ErrorMessageForm("Данное имя пользователя уже существует");
                error.pack();
                correctness = false;
                return;
            }
        }catch (Exception e){
            ErrorMessageForm error = new ErrorMessageForm (e.getMessage());
            error.pack();
            correctness = false;
        }
    }

    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            int i = st.executeUpdate(sql);
            if (i == 0){
                ErrorMessageForm error = new ErrorMessageForm ("Ошибка при добавлении нового пользователя");
                error.pack();
                correctness = false;
                return;
            }
            else {
                SuccessMessageForm success = new SuccessMessageForm("Новый пользователь добавлен");
                success.pack();
            }
            correctness = true;
        }catch (SQLException e) {
            ErrorMessageForm error = new ErrorMessageForm (e.getMessage());
            error.pack();
            correctness = false;
        }

    }
}
