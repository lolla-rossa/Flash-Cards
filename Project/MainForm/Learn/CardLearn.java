package MainForm.Learn;

public class CardLearn {
    private int id;
    private String FirstSide;
    private String SecondSide;
    private int State;
    private int forgetButton;
    private String dateRepeat;
    private String dateCreate;

    public String getDateRepeat() {
        return dateRepeat;
    }

    public void setDateRepeat(String dateRepeat) {
        this.dateRepeat = dateRepeat;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public CardLearn(int id, String FirstSide, String SecondSide, int State, int forgetButton, String dateCreate, String dateRepeat){
        this.id = id;
        this.FirstSide = FirstSide;
        this.SecondSide = SecondSide;
        this.State = State;
        this.forgetButton = forgetButton;
        this.dateCreate = dateCreate;
        this.dateRepeat = dateRepeat;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstSide() {
        return FirstSide;
    }

    public void setFirstSide(String firstSide) {
        FirstSide = firstSide;
    }

    public String getSecondSide() {
        return SecondSide;
    }

    public void setSecondSide(String secondSide) {
        SecondSide = secondSide;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public int getForgetButton() {
        return forgetButton;
    }

    public void setForgetButton(int forgetButton) {
        this.forgetButton = forgetButton;
    }


}
