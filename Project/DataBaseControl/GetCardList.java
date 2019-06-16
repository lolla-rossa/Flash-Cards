package DataBaseControl;

import MainForm.ActiveDeck;
import MainForm.ActiveProfile;
import MainForm.Card.Card;
import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class GetCardList extends CreateConnection implements Request{

    private ArrayList<Card> cardList = new ArrayList<>();
    private ActiveProfile NameActiveProfile = new ActiveProfile();
    private ActiveDeck NameActiveDeck = new ActiveDeck();

    public ArrayList<Card> getCard() {
        getRequest("select * from Cards where idUser = '"
                +NameActiveProfile.getActiveProfile()+"' and Deck = '"+NameActiveDeck.getActiveDeck()+"'");
        return cardList;
    }

    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                cardList.add(new Card (resultSet.getInt("id"),
                resultSet.getString("FirstSide"),resultSet.getString("SecondSide"),
                resultSet.getString("Tag")));
            }

        }catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }

    }
}
