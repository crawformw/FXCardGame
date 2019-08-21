package javafxgame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/** Kitty Class - represents the 4 last cards of the deal that comprise the kitty
 * @author mikec
 */
public class Kitty 
{
    private final Label name = new Label("Kitty");
    private int kittyCardCnt;
  
    private Label trumpCard;
    private Label card2;
    private Label card3;
    private Label card4;
    
    private HBox hbox;
    
    /** Kitty() - main constructor */
    public Kitty() { init(); }
    
    /** getKitty() - returns the kitty in formatted hbox
     * @return 
     */
    public HBox getKitty()
    {
        hbox = new HBox(3);
        hbox.setPadding( new Insets(5, 5, 5, 5) );
        hbox.setAlignment(Pos.CENTER);
        hbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        hbox.getChildren().addAll(name, trumpCard, card2, card3, card4);
        return hbox;
    }
    
    /** init() - initialize the kitty */
    private void init()
    {
        trumpCard = new Label();
        trumpCard.setAlignment(Pos.CENTER);
        trumpCard.setPrefSize(50,50);
        
        card2 = new Label();
        card2.setAlignment(Pos.CENTER);
        card2.setPrefSize(30,30);
        
        card3 = new Label();
        card3.setAlignment(Pos.CENTER);
        card3.setPrefSize(30,30);
       
        card4 = new Label();
        card4.setAlignment(Pos.CENTER);
        card4.setPrefSize(30,30);
        
        kittyCardCnt = 0;
    }
    
    /** addToKitty() - add a card to the kitty, this is done in order
     * @param c 
     */
    public void addToKitty(Card c)
    {
        // we are adding a card, increment the counter first
        kittyCardCnt++;
             
        switch( kittyCardCnt )
        {
            case 1: trumpCard.setGraphic( new ImageView(c.getCardImage() )); break;
            case 2: card2.setGraphic( new ImageView(c.getBackSide() ));     break;
            case 3: card3.setGraphic( new ImageView(c.getBackSide() ));     break;
            case 4: card4.setGraphic( new ImageView(c.getBackSide() ));     break;
        }
    }
    
    /** reset() - reset the kitty for the next hand */
    public void reset()
    {
        kittyCardCnt = 0;
        
        trumpCard.setText("");
        trumpCard.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black;");
        
        card2.setText("");
        card2.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black;");
        
        card3.setText("");
        card3.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black;");
        
        card4.setText("");
        card4.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: black;");
    }
}
