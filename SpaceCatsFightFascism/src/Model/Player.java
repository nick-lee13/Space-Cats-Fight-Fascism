public class Player {
    Cat cat;
    Deck deck;
    int playerNum;
    int actionCount;

    public Player(int playerNum, Cat cat) {
            this.cat = cat;
            deck = new Deck(this);
            this.playerNum = playerNum;
            turnCount = 1;
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

    public int getPlayerNum(){
        return playerNum;
    }

    public void setCat(Cat inCat) {
        cat = inCat;
    }
}
