import java.util.LinkedList;
import java.util.Collections;

public class Deck {

    Player owner;
    LinkedList<Card> cards = new LinkedList<Card>(); // "Deck of Cards" is a collection of card objects

    public Deck(Player inOwner) { // Deck Constructor
            // Body
        owner = inOwner;
    }

    public void addCard(Card inCard) { // Removes the given card from a deck
        cards.add(inCard);
        inCard.setOwner(owner);
    }

    public void removeCard(Card inCard) { // Removes the given card from the deck
        cards.remove(inCard);
        inCard.setOwner(null);
    }

    public LinkedList<Card> getCards() { // Returns a list of cards in deck
        return cards;
    }

    public void shuffle() { // Shuffles the cards in the deck
        Collections.shuffle(cards);
    }

    public int getSize(){
        return cards.size();
    }
}
