package sample;


import DataBaseControl.DeleteActive;
import LogOn.StartForm;
import MainForm.Learn.CardLearn;
import MainForm.Learn.LearnForm;

public class Main  {

    public static void main(String[] args) {
        DeleteActive deleteActive= new DeleteActive();
        deleteActive.DeleteProfile();
        deleteActive.DeleteDeck();
        deleteActive.Close();
        StartForm st = new StartForm();
        st.pack();
    }
}
