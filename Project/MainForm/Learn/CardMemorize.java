package MainForm.Learn;

public class CardMemorize {
    private int id;
    private String FirstSide;
    private String SecondSide;
    private int State;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstSide() {
        return FirstSide;
    }

    private void setFirstSide(String firstSide) {
        FirstSide = firstSide;
    }

    public String getSecondSide() {
        return SecondSide;
    }

    private void setSecondSide(String secondSide) {
        SecondSide = secondSide;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public CardMemorize(int id, String FirstSide, String SecondSide, int State){
        this.setId(id);
        this.setFirstSide(FirstSide);
        this.setSecondSide(SecondSide);
        this.setState(State);
    }
}
