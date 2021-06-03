package CLIENT;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.sun.corba.se.spi.activation.Server;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import SERVER.ServerUp;

public class clientFirstPage extends Application {
	private ObjectOutputStream ObjectoutputStream = null;
	private ObjectInputStream ObjectinputStream = null;
	ServerWorks sw;
	Stage stageMain;
	public void start(Stage stage) throws Exception {

		final Socket clientSocket=new Socket(InetAddress.getLocalHost(), 1234);
		ObjectoutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectinputStream = new ObjectInputStream(clientSocket.getInputStream());


		//making a worker for this client
		sw=new ServerWorks(clientSocket, ObjectoutputStream, ObjectinputStream,this);


		//intializing nodes(components)
		Font font1=new Font("Dolphian", 20);
		Font font2=new Font("2 Arabic Style", 15);
		Font font3=new Font("Dolphian", 45);
		File file = new File("src/LOGO/icon.png");
		final Image icon = new Image(file.toURI().toString());
		Text usern=new Text();
		usern.setText("Username");
		usern.setX(345);
		usern.setY(250);
		usern.setFont(font1);
		final Text status=new Text();
		status.setX(10);
		status.setY(795);
		status.setFont(font2);
		new Thread(new Runnable() {
			public void run() {
				while(!clientSocket.isConnected()){
					status.setText("Connecting . . .");
					if(clientSocket.isClosed()){
						status.setText("Conncetion Error");
					}
				}

				status.setText("Connected");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				status.setText("");
			}
		}).start();
		
		Text passw=new Text();
		passw.setText("Password");
		passw.setX(345);
		passw.setY(360);
		passw.setFont(font1);
		final TextField username=new TextField();
		username.setMinSize(300, 50);
		username.setLayoutX(250);
		username.setLayoutY(270);
		username.setFont(font2);
		username.setAlignment(Pos.CENTER);
		username.setStyle("-fx-background-radius: 100 100 100 100;");

		final TextField password=new PasswordField();
		password.setMinSize(300, 50);
		password.setLayoutX(250);
		password.setLayoutY(380);
		password.setFont(font2);
		password.setAlignment(Pos.CENTER);
		password.setStyle("-fx-background-radius: 100 100 100 100;");

		Text log=new Text();
		log.setText("Login");
		log.setX(328);
		log.setY(150);
		log.setFont(font3);


		Button loginButton=new Button("Login");
		loginButton.setFont(font1);
		loginButton.setLayoutX(250);
		loginButton.setLayoutY(460);
		loginButton.setMinSize(140, 50);
		loginButton.setStyle("-fx-background-color: #93c4c1;-fx-background-radius: 100 100 100 100;");
		loginButton.setOnMouseClicked(new EventHandler() {//login button click event
			public void handle(Event arg0) {
				sw.sendLogin(username.getText(),password.getText(),"LOGIN",sw);
			}
		});
		Button signup=new Button("Sign Up");
		signup.setFont(font1);
		signup.setLayoutX(410);
		signup.setLayoutY(460);
		signup.setMinSize(140, 50);
		signup.setStyle("-fx-background-color: #acb7b0;-fx-background-radius: 100 100 100 100;");
		signup.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				sw.sendLogin(username.getText(),password.getText(),"SIGNUP",sw);
			}
		});
		Group group=new Group();
		ObservableList list=group.getChildren();
		list.add(usern);
		list.add(passw);
		list.add(username);
		list.add(password);
		list.add(loginButton);
		list.add(signup);
		list.add(log);
		list.add(status);
		Scene scene=new Scene(group,800,800);
		stage.setResizable(false);
		stage.getIcons().add(icon);//set icon
		stage.setScene(scene);
		stage.setTitle("Javagram");
		stage.show();
		stageMain=stage;

	}
	public static void main(String[] args) {
		launch(args);
	}
	public void alerterRunner(final Alert alert){
		alert.showAndWait();
	}

}
