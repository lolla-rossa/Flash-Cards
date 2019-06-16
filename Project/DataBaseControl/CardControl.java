package DataBaseControl;

import MainForm.ActiveDeck;
import MainForm.ActiveProfile;
import MainForm.Card.Card;
import MainForm.ErrorMessageForm;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CardControl extends CreateConnection implements Request{

    private boolean correctness = false;


    public void EditCard(Card card){
        getRequest("update Cards set FirstSide = '"+card.getFirstSide()+"', SecondSide = '"+ card.getSecondSide()
                +"', Tag = '" +card.getTag()+"' where id = "+card.getId());
    }

    public void CreateCard(Card card){
        ActiveDeck activeDeck = new ActiveDeck();
        ActiveProfile activeProfile = new ActiveProfile();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String date = format.format(c.getTime());
        getRequest("insert into Cards values(null, '"+activeProfile.getActiveProfile()+"','"+activeDeck.getActiveDeck()+"','"+card.getFirstSide()
                +"','"+card.getSecondSide()+"','"+card.getTag()+"', '"+date+"', '"+date+"')");
    }

    public void DeleteCard(Card card) {
        getRequest("delete from Cards where id = "+card.getId());
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
