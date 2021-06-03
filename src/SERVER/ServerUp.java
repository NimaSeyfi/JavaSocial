package SERVER;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ServerUp extends Application{
	public static ArrayList<Profile> profiles=new ArrayList<Profile>();
	public Scene serverScene;
	Text usern=new Text();
	ServerSocket serverSocket;
	Socket socket;
	ServerMainGUI smg;
	public void start(Stage stage) throws Exception {
	}

	public Scene getScene(){
		Thread th=new Thread(new Runnable() {

			public void run() {
				Font font1=new Font("Dolphian", 20);
				Font font2=new Font("2 Arabic Style", 15);
				Font font3=new Font("Dolphian", 45);
				Font font4=new Font("Tw Cen MT", 18);
				Font font5=new Font("Tw Cen MT", 14);
				File file = new File("src/LOGO/icon.png");
				final Image icon = new Image(file.toURI().toString());
				int TX=0;
				int TY=0;
				//				while(true){
				Pane group=new Pane();
				for(int i=0;i<profiles.size();i++){
					if(i==0){
						TX=160;
						TY=70;
					}

					try {
						Image onOff;
						if(profiles.get(i).online){
							onOff = new Image(new FileInputStream("src/icons/online.png"));
						}else{
							onOff = new Image(new FileInputStream("src/icons/offline.png"));
						}
						ImageView iv=new ImageView(onOff);
						iv.setFitHeight(30);
						iv.setFitWidth(30);
						iv.setX(TX-140);
						iv.setY(TY-20);
						group.getChildren().add(iv);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Text t=new Text(profiles.get(i).getName());
					t.setX(TX);
					t.setY(TY);
					t.setFont(font4);

					Button scg=new Button("SCG");
					scg.setLayoutX(TX+520);
					scg.setLayoutY(TY-20);
					scg.setFont(font5);
					scg.setMaxSize(110, 10);
					scg.wrapTextProperty();
					final int index=i;
					scg.setOnMouseClicked(new EventHandler<Event>() {

						@Override
						public void handle(Event arg0) {
//							System.out.println(profiles.get(0).getGraph().source.followings.get(0).profile.
//getGraph().source.followings.get(0).profile.getName()
//									);
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setResizable(false);
							alert.setTitle("SCG");
							alert.setHeaderText("Strongly Connected Graph");
							alert.setContentText(" ");
							VBox vbox=new VBox();
							for(int k=0;k<profiles.get(index).getGraph().source.followings.size();k++){
								System.out.println(profiles.get(index).getGraph().source.followings.size());
								System.out.println(profiles.get(index).getGraph().source.followings.get(k).profile
										.getGraph().source.followings.size());
								for(int j=0;j<profiles.get(index).followers.size();j++){
									if(profiles.get(index).followers.get(j).getName()
											.equals(
													profiles.get(index).getGraph().source.followings.get(k).profile.getName()
													)){
										System.out.println(profiles.get(index).getName()+"--->"
												+profiles.get(index).getGraph().source.followings.get(k).profile
												.getGraph().source.profile.getName());
//								System.out.println("-"+profiles.get(index).getGraph().source.followings.get(k).profile.getName());
								Pane p=new Pane();
								p.setMinSize(500, 50);
								p.setMaxSize(500, 50);

										Text t=new Text();
										t.setX(190);
										t.setY(20);
										t.setText(profiles.get(index).getName()+"<===>"
												+profiles.get(index).getGraph().source.followings.get(k).profile
												.getGraph().source.profile.getName());

										p.getChildren().add(t);
										vbox.getChildren().add(p);
									}		

								}
							}
							ScrollPane scrollPane = new ScrollPane();
							scrollPane.setContent(vbox);
							scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
							alert.getDialogPane().setContent(scrollPane);
							Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
							stage2.getIcons().add(icon); // To add an icon
							alert.showAndWait();
						}
					});
					group.getChildren().add(scg);

					Line line=new Line();
					line.setStartX(0);
					line.setStartY(TY+45);
					line.setEndX(900);
					line.setEndY(TY+45);
					line.setStrokeWidth(1);
					line.setStyle("-fx-stroke:#e0e0e0");

					group.getChildren().add(line);
					ImagePattern prof_pic;
					try {
						prof_pic = new ImagePattern(new Image(new FileInputStream(profiles.get(i).getIcon())));
						Circle circle = new Circle(25);
						circle.setCenterX(TX-60);
						circle.setCenterY(TY-5);
						circle.setFill(prof_pic);
						group.getChildren().addAll(circle);

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TY=TY+100;
					group.getChildren().add(t);
				}
				//					bp.setCenter(vbox2);
				ScrollPane scrollPane = new ScrollPane();
				scrollPane.setContent(group);
				scrollPane.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
				scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
				Scene scene=new Scene(scrollPane,800,800);
				serverScene=scene;


			}
			//}	
		});
		th.run();
		return serverScene;

	}
	public void addCenter(final BorderPane bp){
		new Thread(new Runnable() {

			@Override
			public void run() {

			}
		}).start();

	}
	public void serverOn() throws Exception{//run server method
		connect();
		//loadProfiles();

	}
	public void connect()  throws Exception{
		Thread connectThread=new Thread(new Runnable() {
			public void run() {
				try {
					serverSocket=new ServerSocket(1234);
					int count = 0;
					while(true){
						System.out.println("waiting for client. . .");				
						socket=serverSocket.accept();

						ObjectOutputStream ObjectoutputStream=
								ObjectoutputStream = new ObjectOutputStream(socket.getOutputStream());
						ObjectInputStream ObjectinputStream=
								ObjectinputStream = new ObjectInputStream(socket.getInputStream());

						count++;
						System.out.println(count);

						ClientWorks cw=new ClientWorks(socket,ObjectoutputStream,ObjectinputStream,smg);
						Thread t=new Thread(cw);
						t.start();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		});
		connectThread.start();
	}
	public static void loadProfiles() throws IOException, ClassNotFoundException {
		profiles.clear();

		FileInputStream fi = new FileInputStream(new File("src/SERVER/_PROFILES.txt"));
		ObjectInputStream oi = new ObjectInputStream(fi);
		profiles= (ArrayList<Profile>) oi.readObject();

	}

	public static void saveProfiles() throws IOException{
		FileOutputStream f = new FileOutputStream(new File("src/SERVER/_PROFILES.txt"));
		ObjectOutputStream o = new ObjectOutputStream(f);

		o.writeObject(profiles);

	}
	
	private static Profile findProfile(String name) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).name.equals(name)){
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}

	private static Post findPost(long id) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			for(int j=0;j<ServerUp.profiles.get(i).getPosts().size();j++){
				if(ServerUp.profiles.get(i).getPosts().get(j).getId()==id){
					return ServerUp.profiles.get(i).getPosts().get(j);
				}
			}
		}
		return null;
	}
}
