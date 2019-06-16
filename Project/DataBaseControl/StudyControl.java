package DataBaseControl;

import MainForm.ActiveDeck;
import MainForm.ActiveProfile;
import MainForm.ErrorMessageForm;
import MainForm.Learn.CardLearn;
import MainForm.Learn.CardMemorize;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class StudyControl extends CreateConnection implements Request {
    private String tag;
    private boolean correctness = false;
    private String type;

    private ArrayList<CardMemorize> cardMemorize = new ArrayList<>();
    private ArrayList<CardLearn> cardLearn = new ArrayList<>();

    private ActiveProfile NameActiveProfile = new ActiveProfile();
    private ActiveDeck NameActiveDeck = new ActiveDeck();

    public StudyControl(){}

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void Continue()
    {
            getRequest("select * from "+ type +" where idUser = '" + NameActiveProfile.getActiveProfile() + "' and State = 0");
    }

    public void Start(){
        DeleteOld();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        String date = format.format(c.getTime());
        if (tag.equals("")) {
            if (type.equals("Memorize")) {
                getRequest("select * from Cards where idUser = '" + NameActiveProfile.getActiveProfile() + "' and Deck = '"
                        + NameActiveDeck.getActiveDeck() + "'");
            } else {
                getRequest("select * from Cards where idUser = '" + NameActiveProfile.getActiveProfile() + "' and Deck = '"
                        + NameActiveDeck.getActiveDeck() + "' and  \"date_repeat \" between '1999-01-01' and '"+date+"'");
            }
        } else
        {
            if (type.equals("Memorize")) {
                getRequest("select * from Cards where idUser = '" + NameActiveProfile.getActiveProfile() + "' and Deck = '"
                        + NameActiveDeck.getActiveDeck() + "' and Tag = '" + tag + "'");
            }else{
                getRequest("select * from Cards where idUser = '" + NameActiveProfile.getActiveProfile() + "' and Deck = '"
                        + NameActiveDeck.getActiveDeck() + "' and Tag = '" + tag + "' and  \"date_repeat \" between '1999-01-01' and '"+date+"'");
            }
        }
        if ( type.equals("Memorize")) {
            insertMemorizeTable();
        } else
        {
            insertLearnTable();
        }
    }
    public void DeleteOld(){
        getUpdateRequest("delete from "+type+" where idUser = '"+NameActiveProfile.getActiveProfile()+"'");
    }

    public ArrayList<CardMemorize> getCardMemorize () {
        return cardMemorize;
    }

    public ArrayList<CardLearn> getCardLearn(){
        return  cardLearn;
    }

    public boolean check(){
        return correctness;
    }

    public void updateStateCard(CardMemorize card){
        getUpdateRequest("update Memorize set State = 1 where id = '"+card.getId()+"'");
    }

    public void updateStateCard(CardLearn card){
        getUpdateRequest("update Learn set State = 1, forgetButton = "+card.getForgetButton()+", date_create = '"+card.getDateCreate()+"', 'date_repeat ' = '"+card.getDateRepeat()+"' where id = '"+card.getId()+"'");
    }

    private void insertMemorizeTable(){
        Iterator<CardMemorize> iterator = cardMemorize.iterator();
        while (iterator.hasNext()){
            CardMemorize cardMemorize = iterator.next();
                getUpdateRequest("insert into Memorize values ('" +  cardMemorize.getId() + "', '" +NameActiveProfile.getActiveProfile() + "','"
                        + cardMemorize.getFirstSide() + "', '" + cardMemorize.getSecondSide() + "', 0 )");

            if (correctness) return;
        }
    }

    private void insertLearnTable() {
        Iterator<CardLearn> iterator = cardLearn.iterator();
        while (iterator.hasNext()){
            CardLearn cardLearn = iterator.next();
            getUpdateRequest("insert into Learn values ('" + cardLearn.getId() + "', '" + NameActiveProfile.getActiveProfile() + "','"
                        + cardLearn.getFirstSide() + "', '" + cardLearn.getSecondSide() + "', 0, 0, '"+cardLearn.getDateCreate()+"', '"+cardLearn.getDateRepeat()+"')");
            if (correctness) return;
        }
    }

    @Override
    public void getRequest(String sql) {
        try{
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        if (resultSet.isClosed()){
            correctness = true;
            return;
        }
        if (type.equals("Memorize")) {
            fillMemorizeList(resultSet);
        }
        else {
            fillLearnList(resultSet);
        }
    }catch (Exception e) {
        correctness = true;
        ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
        error.pack();
    }
    }

    private void fillMemorizeList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                cardMemorize.add(new CardMemorize(resultSet.getInt("id"),
                        resultSet.getString("FirstSide"), resultSet.getString("SecondSide"), 0));
            }
        } catch (Exception e){
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }

    private void fillLearnList(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                cardLearn.add(new CardLearn(resultSet.getInt("id"),
                        resultSet.getString("FirstSide"), resultSet.getString("SecondSide"), 0, 0,resultSet.getString("date_create"), resultSet.getString("date_repeat ")));
            }
        } catch (Exception e){
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }

    private void getUpdateRequest(String sql) {
        try{
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
        }catch (Exception e) {
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
