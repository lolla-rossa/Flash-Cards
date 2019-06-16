package MainForm;

import DataBaseControl.GetActiveDeck;

public class ActiveDeck {
    GetActiveDeck deck = new GetActiveDeck();
    private final Object ActiveDeck = deck.getNameDeck();
    public Object getActiveDeck() {
        return ActiveDeck;
    }

}
