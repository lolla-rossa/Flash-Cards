package DataBaseControl;

import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteActive extends CreateConnection implements Request {

    public DeleteActive(){

    }

    public void DeleteDeck() { getRequest("update Deck set State = 0"); }
    public void DeleteProfile(){
        getRequest("update Users set state = 0");
    }
    public void Close() {
        try {
            connection.close();
        }catch (Exception e){

        }
    }
    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            int k = st.executeUpdate(sql);
            if ( k == 0) {
                ErrorMessageForm error = new ErrorMessageForm ("Ошибка обновления состояния");
                error.pack();
            }
        }catch (Exception e) {
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
