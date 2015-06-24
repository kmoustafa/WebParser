package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
//			 Group root1 = new Group();
//		        primaryStage.setResizable(false);
//		        AnchorPane gridPane = new AnchorPane();
//		        ProgressIndicator p1 = new ProgressIndicator();
//		        p1.setPrefSize(50, 50);
//		        gridPane.getChildren().add(p1);
//		 
//		         
//		        root1.getChildren().add(gridPane);
//		        primaryStage.setScene(new Scene(root1, 400,400));
//		        
//		         
//		 
//		       
////				Scene scene = new Scene(root1,400,400);
//
//		     //   primaryStage.setScene(scene);
//				primaryStage.show();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			TabPane root = (TabPane)loader.load();
			
			MainController co = loader.getController();
			co.setStage(primaryStage);
		 Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
