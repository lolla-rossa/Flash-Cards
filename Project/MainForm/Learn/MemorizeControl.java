package MainForm.Learn;

import DataBaseControl.SetActiveDeck;
import DataBaseControl.StudyControl;
import MainForm.ActiveDeck;
import MainForm.ErrorMessageForm;
import MainForm.SuccessMessageForm;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MemorizeControl {
    private StudyControl studyControl;
    private ArrayList<CardMemorize> cardList;
    private  MemorizeForm memorizeForm;
    private JFrame deckForm;

    public MemorizeControl(String tag, int type, JFrame deckForm){
        studyControl = new StudyControl();
        studyControl.setTag(tag);
        this.deckForm = deckForm;
        studyControl.setType("Memorize");
        if (type == 0){
            studyControl.Start();
        }else
        {
            studyControl.Continue();
        }
        Study();
    }

    private void Study(){
        if (studyControl.check()){
            deckForm.setVisible(true);
            ErrorMessageForm error = new ErrorMessageForm("Нет подходящих карточек для обучения");
            error.pack();
            return;
        }
        cardList = studyControl.getCardMemorize();
        memorizeForm = new MemorizeForm();
        memorizeForm.setCard(cardList.get(0));

        memorizeForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (memorizeForm.getStop()){
                    ActiveDeck activeDeck = new ActiveDeck();
                    new SetActiveDeck(activeDeck.getActiveDeck(), 0);
                    memorizeForm.dispose();
                    deckForm.setVisible(true);
                    return;
                }
                if (memorizeForm.getStatusCard() == 1) {
                    studyControl.updateStateCard(cardList.get(0));
                    cardList.remove(0);
                    if (cardList.isEmpty()) {
                        studyControl.DeleteOld();
                        memorizeForm.dispose();
                        deckForm.setVisible(true);
                        SuccessMessageForm success = new SuccessMessageForm("Обучение завершено");
                        success.pack();
                        return;
                    }
                } else
                {
                    CardMemorize tempCard = cardList.get(0);
                    cardList.remove(0);
                    cardList.add(tempCard);
                }
                memorizeForm.setFirstPane();
                memorizeForm.setCard(cardList.get(0));
                memorizeForm.setVisible(true);
            }
        });
    }
}
