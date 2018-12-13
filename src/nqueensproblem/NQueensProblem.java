
package nqueensproblem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class NQueensProblem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label label = new Label();
        label.setText("Size of New Game: ");
        
        GridPane board = new GridPane();
        board.setAlignment(Pos.CENTER);
        
        TextField input = new TextField();
        input.setOnAction(new nQueensHandler(input, board));        
        
        HBox bottom = new HBox();
        bottom.setSpacing(20);
        bottom.getChildren().addAll(label, input);
        
        VBox center = new VBox();
        center.getChildren().add(board);
        center.setAlignment(Pos.CENTER);
                
        BorderPane root = new BorderPane();
        root.setBottom(bottom);
        root.setCenter(center);
        root.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(root, 500, 350);
        
        primaryStage.setTitle("N Queens Algorithm");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
