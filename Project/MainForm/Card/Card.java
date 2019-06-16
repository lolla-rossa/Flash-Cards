package MainForm.Card;

public class Card {
    private int id;
    private String FirstSide;
    private String SecondSide;
    private String Tag;

    public Card(int id, String FirstSide, String SecondSide,String Tag) {
        this.setId(id);
        this.setFirstSide(FirstSide);
        this.setSecondSide(SecondSide);
        this.setTag(Tag);

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

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

}
