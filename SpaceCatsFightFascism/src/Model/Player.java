public class Player {
    Cat cat;
    Deck deck;

    public Player() {

    }

    public int getInput() {
            return 1;
    }

    public Cat getCat() {
        return cat;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setCat(Cat inCat) {
        cat = inCat;
    }
}
