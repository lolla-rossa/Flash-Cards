package DataBaseControl;

import MainForm.ActiveProfile;
import MainForm.ErrorMessageForm;

import java.sql.Statement;

public class SetActiveDeck extends CreateConnection implements Request {

    private ActiveProfile NameActiveProfile = new ActiveProfile();
    private Object NameDeck;
    private boolean correctness = false;
    private int state;

    public SetActiveDeck(Object NameDeck, int state){
        setState(state);
        setNameDeck(NameDeck);
        getRequest("update Deck set State = '"+state+"' where Owner = '" + NameActiveProfile.getActiveProfile() + "' and Name = '" + NameDeck +"'");
    }


    public void setState(int state) {
        this.state = state;
    }

    private void setNameDeck(Object NameDeck){
        this.NameDeck = NameDeck;
    }

    public boolean check(){
        return correctness;
    }
    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            int k = st.executeUpdate(sql);
            if (k == 0){
                correctness = true;
                ErrorMessageForm error = new ErrorMessageForm("Некорректное состояние колоды");
                error.pack();
                return;
            }
            connection.close();
        }catch (Exception e) {
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
