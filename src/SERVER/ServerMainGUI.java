package SERVER;

import java.io.File;
import java.io.IOException;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Group; 
public class ServerMainGUI extends Application {
	Stage stageMain;
ServerUp suTemp;
	public void start(final Stage stage) throws Exception {
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
		Text passw=new Text();
		passw.setText("Password");
		passw.setX(345);
		passw.setY(400);
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
		password.setLayoutY(420);
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
		loginButton.setLayoutY(500);
		loginButton.setMinSize(300, 50);

		loginButton.setStyle("-fx-background-radius: 100 100 100 100;");
		final ServerMainGUI smg=this;
		loginButton.setOnMouseClicked(new EventHandler() {//login button click event
			public void handle(Event arg0) {
				if(username.getText().toLowerCase().equals("admin") && password.getText().equals("admin")){
					final ServerUp su=new ServerUp();//making a server scene
					su.smg=smg;
					Platform.runLater(new Runnable() {			
						public void run() {
							try {
//								ServerUp.profiles.clear();
//								ServerUp.saveProfiles();
								
								ServerUp.loadProfiles();
								su.serverOn();
								for(int i=0;i<ServerUp.profiles.size();i++){
									ServerUp.profiles.get(i).setOnline(false);
								}
								suTemp=su;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							stageMain=stage;
							stageMain.setScene(su.getScene());//changing the main scene to server scene

						}
					});

				}
				else{
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Login Error");
					alert.setHeaderText("We have problem with logging you in!");
					alert.setContentText("Username or Password is incorrect!\nPlease try again.");
					Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(icon); // To add an icon
					alert.showAndWait();
				}
			}
		});
		Group group=new Group();
		ObservableList list=group.getChildren();
		list.add(usern);
		list.add(passw);
		list.add(username);
		list.add(password);
		list.add(loginButton);
		list.add(log);
		Scene scene=new Scene(group,800,800);
		stage.setResizable(false);
		stage.getIcons().add(icon);//set icon
		stage.setScene(scene);
		stage.setTitle("Javagram - Server");
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
