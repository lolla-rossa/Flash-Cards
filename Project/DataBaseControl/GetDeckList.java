package DataBaseControl;

import MainForm.ActiveProfile;
import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class GetDeckList extends CreateConnection implements Request {

    public GetDeckList(){}

    private Vector<String> deckList = new Vector<>();

    private ActiveProfile NameActiveProfile = new ActiveProfile();

    public Vector<String> getDeck() {
        getRequest("select Name from Deck where Owner = '"+NameActiveProfile.getActiveProfile()+"'");
        return deckList;
    }
    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                deckList.add(resultSet.getString("Name"));
            }
        }catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }

    }
}
