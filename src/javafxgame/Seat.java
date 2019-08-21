package javafxgame;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/** Seat Class - created for each player seated at the table. Each seat will have 5 cards 
 * @author mikec
 */
public class Seat
{
    private Label seatNumber;
    private int seatNumInt;
    private Label seatName;
    private Player thePlayer;
  
    private Label card1;
    private Label card2;
    private Label card3;
    private Label card4;
    private Label card5;
    
    private Button passBtn;
    private Button trumpBtn;
    
    private HBox hbox;
    private VBox vbox;
    
    private GameTable theTable;
    
    public Seat(GameTable table, Player player, int seatNum)
    {
        setGameTable(table);
        setSeatedPlayer(player);
        setSeatNumber(seatNum);
        initButtons();
        initCards();
    }
    
    public HBox getHSeat() 
    { 
        hbox = new HBox(3);
        hbox.setPadding( new Insets(5, 5, 5, 5) );
        hbox.setAlignment(Pos.CENTER);
        hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hbox.getChildren().addAll(seatName, seatNumber, card1, card2, card3, card4, card5, new Label(" "), passBtn, trumpBtn);
        return hbox; 
    }
    
    public VBox getVSeat() 
    {
        vbox = new VBox(3);
        vbox.setPadding( new Insets(5, 5, 5, 5) );
        vbox.setAlignment(Pos.CENTER);
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        vbox.getChildren().addAll(seatName, seatNumber, card1, card2, card3, card4, card5, new Label(" "), passBtn, trumpBtn);
        return vbox; 
    }
    
    private void setGameTable(GameTable t) { theTable = t; }
    private void setSeatedPlayer(Player player) 
    {
        thePlayer = player;
        seatName = new Label();
        seatName.setText(thePlayer.getPlayerName()); 
    }
    private String getSeatedPlayer() { return seatName.getText(); }
    
    public void disableHand(boolean flag) 
    {
        if (hbox != null) { hbox.setDisable(flag); }
        if (vbox != null) { vbox.setDisable(flag); } 
    }
    
    private void setSeatNumber(int seatNum) 
    {
        seatNumInt = seatNum;
        seatNumber = new Label();
        seatNumber.setText("Seat #: "+seatNum);
    }
    private String getSeatNumber() { return seatNumber.getText(); }
    
    private void initButtons()
    {
        passBtn = new Button();
        passBtn.setText("Pass");
        passBtn.setDisable(true);
        passBtn.setOnAction(ae -> { System.out.println("passing"); });
        
        trumpBtn = new Button();
        trumpBtn.setText("Up");
        trumpBtn.setDisable(true);
        trumpBtn.setOnAction(ae -> { System.out.println("Order Up"); });
    }
    
    private void initCards()
    {
        card1 = new Label();
        card1.setAlignment(Pos.CENTER);
        card1.setPrefSize(30,30);
        
        card2 = new Label();
        card2.setAlignment(Pos.CENTER);
        card2.setPrefSize(30,30);
        
        card3 = new Label();
        card3.setAlignment(Pos.CENTER);
        card3.setPrefSize(30,30);
        
        card4 = new Label();
        card4.setAlignment(Pos.CENTER);
        card4.setPrefSize(30,30);
        
        card5 = new Label();
        card5.setAlignment(Pos.CENTER);
        card5.setPrefSize(30,30);
    }
    
    // add player cards to their seat
    public void addCardsToSeat()
    {   
        // player card 1
        card1.setDisable(false);
        card1.setGraphic( new ImageView(thePlayer.hand.get(0).getCardImage()));
        
        card1.setOnMouseClicked((MouseEvent event) -> 
        {
            PauseTransition pauseTrans = new PauseTransition(Duration.seconds(1));
            pauseTrans.setOnFinished((ActionEvent event1) -> 
            { 
                theTable.showDiscard(seatNumInt, thePlayer.hand.get(0));
                theTable.playOutHand(seatNumInt);
            });
            pauseTrans.play();
            thePlayer.markCardPlayed(thePlayer.getCard(1));
            card1.setGraphic(null);
            card1.setDisable(true); 
        });

        // player card2
        card2.setDisable(false);
        card2.setGraphic( new ImageView(thePlayer.hand.get(1).getCardImage()));
     
        card2.setOnMouseClicked((MouseEvent event) -> 
        {
            PauseTransition pauseTrans = new PauseTransition(Duration.seconds(1));
            pauseTrans.setOnFinished((ActionEvent event1) -> 
            { 
                theTable.showDiscard(seatNumInt, thePlayer.hand.get(1));
                theTable.playOutHand(seatNumInt);
            });
            pauseTrans.play();
            thePlayer.markCardPlayed(thePlayer.getCard(2));
            card2.setGraphic(null);
            card2.setDisable(true);
        });
        
        // player card3
        card3.setDisable(false);
        card3.setGraphic( new ImageView(thePlayer.hand.get(2).getCardImage()));
        
        card3.setOnMouseClicked((MouseEvent event) -> 
        {
            PauseTransition pauseTrans = new PauseTransition(Duration.seconds(1));
            pauseTrans.setOnFinished((ActionEvent event1) -> 
            { 
                theTable.showDiscard(seatNumInt, thePlayer.hand.get(2));
                theTable.playOutHand(seatNumInt);
            });
            pauseTrans.play();
            thePlayer.markCardPlayed(thePlayer.getCard(3));
            card3.setGraphic(null);
            card3.setDisable(true);
        });

        // player card4
        card4.setDisable(false);
        card4.setGraphic( new ImageView(thePlayer.hand.get(3).getCardImage()));
        
        card4.setOnMouseClicked((MouseEvent event) -> 
        {
            PauseTransition pauseTrans = new PauseTransition(Duration.seconds(1));
            pauseTrans.setOnFinished((ActionEvent event1) -> 
            { 
                theTable.showDiscard(seatNumInt, thePlayer.hand.get(3));
                theTable.playOutHand(seatNumInt);
            });
            pauseTrans.play();
            thePlayer.markCardPlayed(thePlayer.getCard(4));
            card4.setGraphic(null);
            card4.setDisable(true);  
        });

        // player card5
        card5.setDisable(false);
        card5.setGraphic( new ImageView(thePlayer.hand.get(4).getCardImage()));
        
        card5.setOnMouseClicked((MouseEvent event) -> 
        {
            PauseTransition pauseTrans = new PauseTransition(Duration.seconds(1));
            pauseTrans.setOnFinished((ActionEvent event1) -> 
            { 
                theTable.showDiscard(seatNumInt, thePlayer.hand.get(4));
                theTable.playOutHand(seatNumInt);
            });
            pauseTrans.play();
            thePlayer.markCardPlayed(thePlayer.getCard(5));
            card5.setGraphic(null);
            card5.setDisable(true);
        });
    }
    
}
