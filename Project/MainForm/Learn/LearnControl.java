package MainForm.Learn;

import DataBaseControl.StudyControl;
import DataBaseControl.SetActiveDeck;
import MainForm.ActiveDeck;
import MainForm.ErrorMessageForm;
import MainForm.SuccessMessageForm;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class LearnControl {
    private StudyControl studyControl;
    private ArrayList<CardLearn> cardList;
    private  LearnForm learnForm;
    private JFrame deckForm;

    public LearnControl(String tag,int type, JFrame deckForm){
        this.deckForm = deckForm;
        studyControl = new StudyControl();
        studyControl.setTag(tag);
        studyControl.setType("Learn");
        if (type == 0){
            studyControl.Start();
        } else
        {
           studyControl.Continue();
        }

        Study();
    }

    public void Study(){
        if (studyControl.check()){
            ErrorMessageForm error = new ErrorMessageForm("Нет подходящих карточек для обучения");
            error.pack();
            deckForm.setVisible(true);
            return;
        }
        cardList = studyControl.getCardLearn();
        learnForm = new LearnForm();
        learnForm.setCard(cardList.get(0));

        learnForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (learnForm.getStop()){

                    ActiveDeck activeDeck = new ActiveDeck();
                    new SetActiveDeck(activeDeck.getActiveDeck(), 0);
                    learnForm.dispose();
                    deckForm.setVisible(true);
                    return;
                }
                if (learnForm.getStatusCard() == 1) {
                    studyControl.updateStateCard(cardList.get(0));
                    cardList.remove(0);
                    if (cardList.isEmpty()) {
                        studyControl.DeleteOld();
                        learnForm.dispose();
                        deckForm.setVisible(true);
                        SuccessMessageForm success = new SuccessMessageForm("Обучение завершено");
                        success.pack();
                        return;}
                } else {
                        CardLearn tempCard = cardList.get(0);
                        cardList.remove(0);
                        cardList.add(tempCard);
                    }
                    learnForm.setFirstPane();
                    learnForm.setCard(cardList.get(0));
                    learnForm.setVisible(true);

            }
        });
    }


}
