package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ArticleController implements Initializable{

	@FXML
	ImageView image;
	@FXML
	Label header;
	@FXML
	TextArea body;
	@FXML
	Label pageNo;
	ArrayList<Article> articles ; 
	private int n = 0;
	//private int rightN = 0;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		body.setWrapText(true);
		articles = MainController.getInstance().articles;
		if(articles != null && articles.size()>0){
		header.setText(articles.get(0).getHeader());
		body.setText(articles.get(0).getBody());
		image.setImage(new Image(articles.get(0).getImgURL()));
		pageNo.setText("Page# 1");
		}
	}

	@FXML
	public void goHome(ActionEvent e) throws IOException{
		Node node=(Node) e.getSource();
		  Stage primaryStage=(Stage) node.getScene().getWindow();
		  Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene2 = new Scene(root);
		scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene2);
		primaryStage.show();
	}

	@FXML
	public void goLeft(){
		n--;
		if(n < 0)
			n = 4;
		if(n == 5)
			n = 0; 
		
		pageNo.setText("Page# "+String.valueOf(n+1));
	}
	@FXML void goRight(){
		n++;
		if(n < 0)
			n = n * -1;
		if(n == 5)
			n = 0; 
		pageNo.setText("Page# "+String.valueOf(n+1));

	}
	
	
	

}
