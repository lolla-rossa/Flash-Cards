package DataBaseControl;

import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;

public class GetActiveDeck extends CreateConnection implements Request {
    private Object NameDeck;
    public GetActiveDeck(){
        getRequest("select Name from Deck where state = 1");
    }

    public Object getNameDeck() {
        return NameDeck;
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
            NameDeck = resultSet.getString("Name");
            connection.close();
        }catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
