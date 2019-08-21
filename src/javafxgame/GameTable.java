package javafxgame;

import java.util.Arrays;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/** GameTable Class - virtual table top with four players and four seats, plus the kitty
 * @author mikec
 */
public class GameTable extends BorderPane implements GameConstants
{
    public int tableNumber;
    
    public Player north;
    public Player south;
    public Player east;
    public Player west;
    public Kitty theKitty;
    
    public Seat seat1;
    public Seat seat2;
    public Seat seat3;
    public Seat seat4;
    
    private BorderPane discardArea;
    private Label discard1;
    private Label discard2;
    private Label discard3;
    private Label discard4;
    private String discard1Text;
    private String discard2Text;
    private String discard3Text;
    private String discard4Text;
    
    private boolean handStarted = false;
    private Player handLeader;
    private int suitLead = -1;
    private int handValue = 0;
    private int cardsPlayed = 0;
    
    private int teamOne = 0;
    private int teamTwo = 0;
     
    private int dealer;
    private final int playOrder[][] = { {1, 3, 2, 4}, 
                                        {3, 2, 4, 1}, 
                                        {2, 4, 1, 3}, 
                                        {4, 1, 3, 2} };
    
    public GameTable()
    {
        setPadding( new Insets(10, 10, 10, 10) );
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        initGameTable();
    }
    
    private void initGameTable()
    {
        // create the players
        north = new Player("North");
        south = new Player("South");
        east = new Player("East");
        west = new Player("West");
        
        theKitty = new Kitty();
        
        discard1 = new Label();
        discard1.setPrefSize(50, 50);
        discard1.setAlignment(Pos.CENTER);
        discard1.setStyle("-fx-background-color: #DDDDDD;");
        
        discard2 = new Label();
        discard2.setPrefSize(50, 50);
        discard2.setAlignment(Pos.CENTER);
        discard2.setStyle("-fx-background-color: #DDDDDD;");
        
        discard3 = new Label();
        discard3.setPrefSize(50, 50);
        discard3.setAlignment(Pos.CENTER);
        discard3.setStyle("-fx-background-color: #DDDDDD;");
        
        discard4 = new Label();
        discard4.setPrefSize(50, 50);
        discard4.setAlignment(Pos.CENTER);
        discard4.setStyle("-fx-background-color: #DDDDDD;");
        
        discardArea = new BorderPane();
        BorderPane.setAlignment(discard1, Pos.CENTER);
        BorderPane.setAlignment(discard2, Pos.CENTER);
        BorderPane.setAlignment(discard3, Pos.CENTER);
        BorderPane.setAlignment(discard4, Pos.CENTER);
        discardArea.setTop(discard1);
        discardArea.setBottom(discard2);
        discardArea.setRight(discard3);
        discardArea.setLeft(discard4);
        discardArea.setCenter(theKitty.getKitty());
        
        // create the seats
        seat1 = new Seat(this, north, 1);
        seat2 = new Seat(this, south, 2);
        seat3 = new Seat(this, east, 3);
        seat4 = new Seat(this, west, 4);
        
        // add seats to our game table
        setTop(seat1.getHSeat());
        setBottom(seat2.getHSeat());
        setRight(seat3.getVSeat());
        setLeft(seat4.getVSeat());
        setCenter(discardArea);        
    }
    
    /** playOutHand() - based on starting seat number, play out the hand
     * @param startingSeatNum 
     */
    public void playOutHand(int startingSeatNum)
    {
        cardsPlayed++;
        switch( startingSeatNum )
        {
            case 1:
                if (!handStarted) 
                { 
                    handStarted = true;
                    handLeader = north;
                    suitLead = Arrays.asList(abbrSuit).indexOf(discard1Text.substring(1,2));
                    handValue = Arrays.asList(cards).indexOf(discard1Text.substring(0,1));
                    JavaFXGame.setStatusArea("North Started, played the: " + discard1Text + " "+ handValue +" suit: " + suitLead );
                }
                else 
                { 
                    int suitPlayed = Arrays.asList(abbrSuit).indexOf(discard1Text.substring(1,2));
                    int valuePlayed = Arrays.asList(cards).indexOf(discard1Text.substring(0,1));
                    
                    JavaFXGame.setStatusArea("North played:  " + discard1Text );
                    
                    if (suitPlayed == suitLead)
                    {
                        if (valuePlayed > handValue)
                        {
                            handValue = valuePlayed;
                            handLeader = north;
                            JavaFXGame.setStatusArea("North winning the hand");
                        }
                    }
                }
                seat2.disableHand(false);
                seat3.disableHand(false);
                seat4.disableHand(false);
                break;
            
            case 2:
                if (!handStarted) 
                {    
                    handStarted = true;
                    handLeader = south;
                    suitLead = Arrays.asList(abbrSuit).indexOf(discard2Text.substring(1,2));
                    handValue = Arrays.asList(cards).indexOf(discard2Text.substring(0,1));
                    JavaFXGame.setStatusArea("South Started, played the: " + discard2Text + " "+ handValue +" suit: " + suitLead );
                }
                else 
                { 
                    int suitPlayed = Arrays.asList(abbrSuit).indexOf(discard2Text.substring(1,2));
                    int valuePlayed = Arrays.asList(cards).indexOf(discard2Text.substring(0,1));
                    
                    JavaFXGame.setStatusArea("South played:  " + discard2Text );
                    
                    if (suitPlayed == suitLead)
                    {
                        if (valuePlayed > handValue)
                        {
                            handValue = valuePlayed;
                            handLeader = south;
                            JavaFXGame.setStatusArea("South winning the hand");
                        }
                    }
                }
                seat1.disableHand(false);
                seat3.disableHand(false);
                seat4.disableHand(false);
                break;
                
            case 3:
                if (!handStarted) 
                { 
                    handStarted = true;
                    handLeader = east;
                    suitLead = Arrays.asList(abbrSuit).indexOf(discard3Text.substring(1,2));
                    handValue = Arrays.asList(cards).indexOf(discard3Text.substring(0,1));
                    JavaFXGame.setStatusArea("East Started, played the: " + discard3Text + " "+ handValue +" suit: " + suitLead );
                }
                else 
                { 
                    int suitPlayed = Arrays.asList(abbrSuit).indexOf(discard3Text.substring(1,2));
                    int valuePlayed = Arrays.asList(cards).indexOf(discard3Text.substring(0,1));
                    
                    JavaFXGame.setStatusArea("East played:  " + discard3Text );
                    
                    if (suitPlayed == suitLead)
                    {
                        if (valuePlayed > handValue)
                        {
                            handValue = valuePlayed;
                            handLeader = east;
                            JavaFXGame.setStatusArea("East winning the hand");
                        }
                    } 
                }
                seat1.disableHand(false);
                seat2.disableHand(false);
                seat4.disableHand(false);
                break;
                
            case 4:
                if (!handStarted) 
                { 
                    handStarted = true;
                    handLeader = west;
                    suitLead = Arrays.asList(abbrSuit).indexOf(discard4Text.substring(1,2));
                    handValue = Arrays.asList(cards).indexOf(discard4Text.substring(0,1));
                    JavaFXGame.setStatusArea("West Started, played the: " + discard4Text + " "+ handValue +" suit: " + suitLead );
                }
                else 
                { 
                    int suitPlayed = Arrays.asList(abbrSuit).indexOf(discard4Text.substring(1,2));
                    int valuePlayed = Arrays.asList(cards).indexOf(discard4Text.substring(0,1));
                    
                    JavaFXGame.setStatusArea("West played:  " + discard4Text );
                    
                    if (suitPlayed == suitLead)
                    {
                        if (valuePlayed > handValue)
                        {
                            handValue = valuePlayed;
                            handLeader = west;
                            JavaFXGame.setStatusArea("West winning the hand");
                        }
                    } 
                }
                seat1.disableHand(false);
                seat2.disableHand(false);
                seat3.disableHand(false);
                break;
        }
        
        Platform.runLater(() -> 
        { 
            if (cardsPlayed == 4) 
            { 
                JavaFXGame.setStatusArea("Hand won by: " + handLeader.getPlayerName());
                if (handLeader == north || handLeader == south) { JavaFXGame.addToTricks(1); }
                else { JavaFXGame.addToTricks(2); }
                
                PauseTransition pauseTrans = new PauseTransition(Duration.seconds(2));
                pauseTrans.setOnFinished((ActionEvent event1) -> { clearDiscardArea(); });
                pauseTrans.play();
            } 
        });
    }
    
    /** resetGameTable() - resets our game table for the next hand */
    public void resetGameTable() 
    {
        handLeader = null;
        handValue = 0;
        cardsPlayed = 0;
        teamOne = 0;
        teamTwo = 0;
        seat1.disableHand(false);
        seat2.disableHand(false);
        seat3.disableHand(false);
        seat4.disableHand(false);
        clearDiscardArea();
        JavaFXGame.resetTricks();
        theKitty.reset(); 
    } 
    
    /** showDiscard() - based on the seat and card played, adjust the discard area according
     * @param seat
     * @param c 
     */
    public void showDiscard(int seat, Card c)
    {
        switch( seat )
        {
            case 1:
                discard1Text = c.cardName();
                discard1.setGraphic( new ImageView(c.getCardImage() ));    
            break;
            
            case 2:
                discard2Text = c.cardName();
                discard2.setGraphic( new ImageView(c.getCardImage() ));     
            break;
            
            case 3:
                discard3Text = c.cardName();
                discard3.setGraphic( new ImageView(c.getCardImage() ));     
            break;
            
            case 4:
                discard4Text = c.cardName();
                discard4.setGraphic( new ImageView(c.getCardImage() ));     
            break;
        }
    }
    
    /** clearDiscardArea() - called at the end of the hand to clear out any played cards and prep for the next hand */
    public void clearDiscardArea()
    {
        discard1.setGraphic(null);
        discard1.setStyle("-fx-background-color: #DDDDDD;");
        
        discard2.setGraphic(null);
        discard2.setStyle("-fx-background-color: #DDDDDD;");
        
        discard3.setGraphic(null);
        discard3.setStyle("-fx-background-color: #DDDDDD;");
        
        discard4.setGraphic(null);
        discard4.setStyle("-fx-background-color: #DDDDDD;");
        
        seat1.disableHand(true);
        seat2.disableHand(true);
        seat3.disableHand(true);
        seat4.disableHand(true);
        
        if (handLeader == north) 
        { 
            discard1.setStyle("-fx-background-color: #FFDDDD;");
            seat1.disableHand(false);
        }
        
        if (handLeader == south) 
        { 
            discard2.setStyle("-fx-background-color: #FFDDDD;");
            seat2.disableHand(false);
        }
        
        if (handLeader == east) 
        { 
            discard3.setStyle("-fx-background-color: #FFDDDD;");
            seat3.disableHand(false);
        }
        
        if (handLeader == west) 
        { 
            discard4.setStyle("-fx-background-color: #FFDDDD;"); 
            seat4.disableHand(false);
        }
        
        handStarted = false;
        suitLead = -1;
        cardsPlayed = 0;
        handLeader = null;
        handValue = 0;
    }
    
    public void setTableNumber(int tNum) { tableNumber = tNum; }
    public int getTableNumber() { return tableNumber; }
    
    public void showGameTable()
    {
        JavaFXGame.setStatusArea("Seated Players: ");
        JavaFXGame.setStatusArea("Seat 1: " + north.getPlayerName());
        JavaFXGame.setStatusArea("Seat 2: " + south.getPlayerName());
        JavaFXGame.setStatusArea("Seat 3: " + east.getPlayerName());
        JavaFXGame.setStatusArea("Seat 4: " + west.getPlayerName());
    }
    
}
