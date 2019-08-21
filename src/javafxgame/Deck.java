package javafxgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author mikec
 */
public class Deck 
{
    private ArrayList<Card> cards;
    
    private final int deckType = 1;       // type = 0 is 52 card deck, type = 1 is 24 card deck
    
    private int shuffleIndex1;
    private int shuffleIndex2;
    private Random generator;
    private Card tempCard;
    
    public Deck() { initDeck(); }
    
    private void initDeck()
    {
        cards = new ArrayList<>();
        
        switch(deckType)
        {
            case 0: // full deck (52 cards)
                for (int s = 0; s <= 3; s++)
                {
                    for ( int f=0; f <= 12; f++) { cards.add( new Card(s,f)); }
                }
                break;
                
            case 1: // euchre deck (24 cards, 9 - A)
                for (int s = 0; s <= 3; s++)
                {
                    for ( int f=7; f <= 12; f++) { cards.add( new Card(s,f)); }
                }
                break;            
        }
    }
    
    private void shuffle()
    {
        generator = new Random();
        
        if (cards.size() <= 2) { JavaFXGame.setStatusArea("Deck size too small to shuffle."); }
        else
        {
            JavaFXGame.setStatusArea("Shuffling the deck");
            
            for ( int i=0; i < 100; i++)
            {
                shuffleIndex1 = generator.nextInt( cards.size() - 1);
                shuffleIndex2 = generator.nextInt( cards.size() - 1);

                tempCard = (Card) cards.get( shuffleIndex2 );
                cards.set( shuffleIndex2, cards.get( shuffleIndex1) );
                cards.set( shuffleIndex1, tempCard );
            }
        }
        
    }
    
    public void dealCards(GameTable theTable)
    {        
        if (cards.size() != 24 ) 
        {
            initDeck();
            theTable.resetGameTable();
            dealCards(theTable); 
        }
        else
        {
            // shuffle the deck first
            shuffle();
            
            // initialize a new hand for each player at the table.
            theTable.north.newHand();
            theTable.south.newHand();
            theTable.east.newHand();
            theTable.west.newHand();
        
            // deal the cards to the players first
            do 
            {
                theTable.north.addToHand(cards.remove(0));
                theTable.south.addToHand(cards.remove(0));
                theTable.east.addToHand(cards.remove(0));
                theTable.west.addToHand(cards.remove(0)); 
            } while (cards.size() > 4);
            
            // add the cards to the seat labels
            theTable.seat1.addCardsToSeat();
            theTable.seat2.addCardsToSeat();
            theTable.seat3.addCardsToSeat();
            theTable.seat4.addCardsToSeat();

            // deal the remaining cards to the kitty
            theTable.theKitty.addToKitty(cards.remove(0));
            theTable.theKitty.addToKitty(cards.remove(0));
            theTable.theKitty.addToKitty(cards.remove(0));
            theTable.theKitty.addToKitty(cards.remove(0));

            theTable.north.showHand();
            theTable.south.showHand();
            theTable.east.showHand();
            theTable.west.showHand();   
        }
    }
    
    public Card drawNextCard() { return cards.remove( 0 ); }
    public int getTotalCards() { return cards.size(); }
   
}
