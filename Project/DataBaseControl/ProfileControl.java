package DataBaseControl;

import MainForm.ActiveProfile;
import MainForm.ErrorMessageForm;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProfileControl extends CreateConnection implements Request  {
    private boolean correctness = false;
    private boolean checkOldPass = false;
    private ActiveProfile NameActiveProfile = new ActiveProfile();

    public boolean Check(){
        return correctness;
    }
    public void Close() {
        try {
            connection.close();
        }catch (Exception e){

        }
    }
    public void ChangeName(String newName){
        getUpdateRequest("update Users set login = '"+newName+"' where login = '"+NameActiveProfile.getActiveProfile()+"'");
    }
    public boolean CheckOldPassword(char[] password){
        getRequest("select * from Users where password = '"+ String.valueOf(password)+"' and login = '"+NameActiveProfile.getActiveProfile()+"'");
        return checkOldPass;
    }
    public void DeleteUser(char[] password){
        getUpdateRequest("delete from Users where login = '"+NameActiveProfile.getActiveProfile()+"' and password = '"+String.valueOf(password)+"'");
    }

    public void ChangePassword (char[] password){
        getUpdateRequest("update Users set password = '"+String.valueOf(password)+"' where login = '"+NameActiveProfile.getActiveProfile()+"'");
    }

    @Override
    public void getRequest(String sql) {
        try {
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery(sql);
            if (result.isClosed()){
                checkOldPass = true;
            }
        }catch (Exception e) {
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
    private void getUpdateRequest(String sql) {
        try{
            Statement st = connection.createStatement();
            int k = st.executeUpdate(sql);
            if (k == 0){
                correctness = true;
            }
        }catch (Exception e) {
            correctness = true;
            ErrorMessageForm error = new ErrorMessageForm(e.getMessage());
            error.pack();
        }
    }
}
