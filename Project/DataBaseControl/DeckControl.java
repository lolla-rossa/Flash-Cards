package DataBaseControl;

import MainForm.ActiveDeck;
import MainForm.ActiveProfile;
import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DeckControl extends CreateConnection implements Request {
    private boolean correctness  = false;
    private ActiveProfile activeProfile = new ActiveProfile();

    public void addDeck(String nameDeck){
        getRequest("insert into Deck values('"+nameDeck+"','"+activeProfile.getActiveProfile()+"',0)");
    }

    public boolean checkData(String nameDeck){
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("select * from Deck where Name = '" + nameDeck
                    + "' and Owner = '" + activeProfile.getActiveProfile() + "'");
            if (resultSet.isClosed()) return false;
        } catch (Exception e){
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
        return true;
    }
    public void deleteDeck(){
        ActiveDeck activeDeck = new ActiveDeck();
        getRequest("delete from Deck where Owner = '"+activeProfile.getActiveProfile()+"' and Name = '"
                +activeDeck.getActiveDeck()+"'");

    }

    public int getNumberReadyCard(){
        ActiveDeck activeDeck = new ActiveDeck();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String date = format.format(c.getTime());
        return getselectRequest("select count(*) as \"num\" from Cards where idUser = '"+activeProfile.getActiveProfile()+"' and Deck = '"
                + activeDeck.getActiveDeck()+"' and  \"date_repeat \" between '1999-01-01' and '" +date+"'");
    }

    public void changeNameDeck(String oldNameDeck, String newNameDeck){

    }
    public void Close(){
        try {
            connection.close();
        }catch (Exception e){}
    }

    public boolean check(){
        return correctness;
    }

    public int getselectRequest (String sql){
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet  = st.executeQuery(sql);
            if (resultSet.isClosed()) {
                return 0;
            }
            return  resultSet.getInt("num");
        }catch (Exception e){
            ErrorMessageForm error = new ErrorMessageForm (e.getMessage());
            error.pack();
            correctness = false;
        }
        return 0;
    }


    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            int k = st.executeUpdate(sql);
            if (k == 0){
                ErrorMessageForm error = new ErrorMessageForm("Неправильный запрос");
                error.pack();
                correctness = true;
                return;
            }
        }catch (Exception e) {
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
