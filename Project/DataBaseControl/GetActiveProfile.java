package DataBaseControl;

import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;

public class GetActiveProfile extends CreateConnection implements Request {
    private String Name;
    public GetActiveProfile(){
        getRequest("select login from Users where state = 1");
    }

    public String getName() {
        return Name;
    }

    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            if (resultSet.isClosed()){
                ErrorMessageForm error = new ErrorMessageForm("Неверный запрос");
                error.pack();
            }
            Name = resultSet.getString("login");
            connection.close();
        }catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
