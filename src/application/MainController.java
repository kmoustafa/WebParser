package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController implements Initializable {
	static MainController instance;
	@FXML
	ImageView image2;
	@FXML
	ImageView image1;
	@FXML
	AnchorPane tab2Anchor;
	@FXML
	AnchorPane tab3Anchor;
	@FXML
	ProgressIndicator prog;
	@FXML
	TextArea textArea;
	// @FXML
	// Button button;
	@FXML
	Label conLabel;
	@FXML
	TabPane tabPane;
	@FXML
	Tab tab2;
	@FXML
	Tab tab3;	
	private static final Image IMAGE = new Image(MainController.class
			.getResource("images/The_Horse_in_Motion.jpg").toString());

	private static final int COLUMNS = 4;
	private static final int COUNT = 10;
	private static final int OFFSET_X = 18;
	private static final int OFFSET_Y = 25;
	private static final int WIDTH = 374;
	private static final int HEIGHT = 243;
	private Stage myStage;
	static boolean isConnected = false;
	Article article = new Article("", "", "");
	ArrayList<Article> articles = new ArrayList();

	public void setStage(Stage stage) {
		myStage = stage;
	}

	public static MainController getInstance() {
		return instance;
	}

	public MainController() {
		// TODO Auto-generated constructor stub
		instance = this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// prog.setVisible(false);
		tab2.setDisable(true);
		tab3.setDisable(true);
		System.out.println("ART :: " + articles.size());
		Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				prog.setVisible(true);
				return null;
			}
		};
		new Thread(task).start();
		connect();
		tabPane.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Tab>() {

					@Override
					public void changed(
							ObservableValue<? extends Tab> observable,
							Tab oldValue, Tab newValue) {
						// TODO Auto-generated method stub
						if (!newValue.getText().equals("Tab1")
								&& !article.getHeader().isEmpty()
								&& !article.getImgURL().isEmpty()
								&& !article.getBody().isEmpty()) {
							printArticles();
						}
					}
				});
	}

	@FXML
	public void pressed() {
		Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				prog.setVisible(true);
				return null;
			}
		};
		new Thread(task).start();
		System.out.println("pressed");

	}

	@FXML
	public void tabSelect() {
		System.out.println("select");
		printArticles();
	}

	// @FXML
	public void connect() {
		// prog.setVisible(true);
		System.out.println("released");
		Task task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				// TODO Auto-generated method stub
				prepareArticles();

				return null;
			}
		};
		new Thread(task).start();
		task.setOnSucceeded(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				conLabel.setVisible(false);
				prog.setVisible(false);
				isConnected = true;
				tab2.setDisable(false);
				tab3.setDisable(false);
			}
		});
		image2.setImage(IMAGE);
		image2.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));

		final Animation animation = new SpriteAnimation(image2,
				Duration.millis(1000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y,
				WIDTH, HEIGHT);
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		image1.setVisible(true);
		textArea.setVisible(true);
	}

	private void prepareArticles() {
		// TODO Auto-generated method stub
		Document doc = null;
		try {
			doc = Jsoup
					.connect(
							"http://ligeos.com/blog/2015/05/09/test-private-space")
					.timeout(10 * 1000).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements header = doc.select("article>header>h1");
		this.article.setHeader(header.text());
		System.out.println(header.text());
		Elements body = doc.select(".entry-content p");
		String b = "";
		for (int i = 1; i < body.size(); i++) {
			System.out.println("----------");
			System.out.println(body.get(i).text());
			b += body.get(i).text();
		}
		this.article.setBody(b);
		Elements img = doc.select("article img");
		this.article.setImgURL(img.attr("abs:src"));
		// System.out.println(imgURL);
		image1.setImage(new Image(this.article.getImgURL()));

	}

	public void printArticles() {
		for (int i = 0; i < 5; i++) {
			TextArea area = new TextArea();
			TextArea area1 = new TextArea();

			area.setPrefHeight(25.0);
			area.setLayoutX(10.0);
			area.setLayoutY((i) * 50 + 3);
			area.setEditable(false);
			area.getStyleClass().add("text-area1");
			area1.setPrefHeight(25.0);
			area1.setLayoutX(10.0);
			area1.setLayoutY((i) * 50 + 3);
			area1.setEditable(false);
			area1.getStyleClass().add("text-area1");
			if (this.article.getBody().length() > 100){
				area.setText(this.article.getBody().substring(0, 100) + "...");
				area1.setText(this.article.getBody().substring(0, 100) + "...");

			}
			else{
				area.setText(this.article.getBody());
				area1.setText(this.article.getBody());

			}
			area.setWrapText(true);
			area1.setWrapText(true);

			area.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					Node node = (Node) event.getSource();

					// FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					// .getResource("Article.fxml"));
					// Parent root = null;
					// try {
					// root = (Parent) fxmlLoader.load();
					// ArticleController controller = fxmlLoader
					// .getController();
					// // System.out.println("Main : " + header.text());
					// // controller.setH(header.text());
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					//
					// Scene scene = new Scene(root);
					// Stage stage = new Stage();
					// stage.setScene(scene);
					//
					// stage.show();

					Stage primaryStage = (Stage) node.getScene().getWindow();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource(
								"Article.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Scene scene2 = new Scene(root);
					scene2.getStylesheets().add(
							getClass().getResource("application.css")
									.toExternalForm());
					primaryStage.setScene(scene2);
					primaryStage.show();
				}
			});
			area1.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					Node node = (Node) event.getSource();

					// FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					// .getResource("Article.fxml"));
					// Parent root = null;
					// try {
					// root = (Parent) fxmlLoader.load();
					// ArticleController controller = fxmlLoader
					// .getController();
					// // System.out.println("Main : " + header.text());
					// // controller.setH(header.text());
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
					//
					// Scene scene = new Scene(root);
					// Stage stage = new Stage();
					// stage.setScene(scene);
					//
					// stage.show();

					Stage primaryStage = (Stage) node.getScene().getWindow();
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource(
								"Article.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Scene scene2 = new Scene(root);
					scene2.getStylesheets().add(
							getClass().getResource("application.css")
									.toExternalForm());
					primaryStage.setScene(scene2);
					primaryStage.show();
				}
			});
			tab2Anchor.getChildren().add(area);
			tab3Anchor.getChildren().add(area1);
			articles.add(article);
		}
	}

}
