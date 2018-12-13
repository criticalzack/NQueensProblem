package nqueensproblem;


import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class nQueensHandler implements EventHandler<ActionEvent>
{
    GridPane board;
    TextField tf;
    public nQueensHandler(TextField gameSizeTf, GridPane board)
    {
        tf = gameSizeTf;
        this.board = board;
    }
    @Override
    public void handle(ActionEvent event)
    {
        LinkedList<Place> solution = new LinkedList<>();
        int gameSize = Integer.parseInt(tf.getText());
        boolean success = extend(solution, 0, gameSize);
        
        board.getChildren().clear();
        Map<String, Label> labelMap = new HashMap<>();
        for(int row = 0; row < gameSize; row++)
        {
            for(int col = 0; col < gameSize; col++)
            {
                Label label = new Label(" ");
                String color;
                if((row + col) % 2 == 0)
                    color = "yellow";
                else
                    color = "green";
                label.setStyle("-fx-background-color: " + color);
                label.setFont(Font.font(30));
                label.setPadding(new Insets(10, 30, 10, 30));

                board.add(label, col, row);

                labelMap.put(row + "-" + col, label);

            }
        }
        
        if(success)
        {
            for(Place p : solution)
            {
                Label queen = labelMap.get(p.toString());
                queen.setText("Q");
                queen.setPadding(new Insets(10, 23, 10, 23));
            }
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No non-attacking positions are possible.");
            alert.setTitle("Message");
            alert.setHeaderText("Message");
            alert.showAndWait();
        }
    }
    // Try to extend a partial queens configuration by placing
    // a queen in a given row.
    boolean extend(LinkedList<Place> partial, int row, int gameSize)
    {
        if(row == gameSize)
            return true;
        for(int col = 0; col < gameSize; col++)
        {                        
            if(conflict(partial, row, col))
                continue;
            else
            {
                Place queen = new Place(row, col);
                partial.add(queen);
                boolean success = extend(partial, row + 1, gameSize);
                if(!success)                
                    partial.removeLast();
                else
                    return true;
            }
        }
        
        return false;

    }
    boolean conflict(Collection<Place> places, int r, int c)
    {
        for(Place p : places)
        {
            if(p.conflict(r, c))
                return true;
        }
        return false;
    }
}

class Place
{
    public final int row;
    public final int col;
    public Place(int r, int c)
    {
        row = r; col = c;   
    }
    public boolean conflict(int r, int c)
    {
        if(this.row == r ||
                this.col == c ||
                (this.row + this.col) == (r + c) ||
                (this.row - this.col) == (r - c))
            return true;
        else
            return false;
    }
    @Override
    public String toString()
    {
    return row+"-"+col;
    }
    
}
