package CLIENT;

import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import com.sun.org.apache.bcel.internal.classfile.Code;

import SERVER.Like;
import SERVER.Post;
import SERVER.Profile;
import SERVER.ServerUp;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;

public class AcountView extends Application {
	//	Profile account=new Profile("nima");
	Profile account;
	clientFirstPage cfp;
	AcountView AV=this;
	public Profile getAccount() {
		return account;
	}
	public void setAccount(Profile account) {
		this.account = account;
	}
	public ServerWorks getSw() {
		return sw;
	}
	public void setSw(ServerWorks sw) {
		this.sw = sw;
	}
	Font font1=new Font("Dolphian", 20);
	Font font2=new Font("2 Arabic Style", 15);
	Font font3=new Font("Dolphian", 45);
	Font font4=new Font("Tw Cen MT", 18);
	Font font5=new Font("Tw Cen MT", 14);
	ServerWorks sw;
	BorderPane bp;
	private File file = new File("src/LOGO/icon.png");
	final private Image icon = new Image(file.toURI().toString());
	public Scene getScene() throws Exception {
		//intializing nodes(components)
		System.out.println("notification : "+account.notifications.size());
		final Thread updater=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(11099);
						account=sw.updater(account);
						System.out.println("updating... ");
					}
					 catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		updater.start();
		File file = new File("src/LOGO/icon.png");
		final Image icon = new Image(file.toURI().toString());
		bp=new BorderPane();
		bp.setTop(addTop());
		bp.setBottom(addDown());
		bp.setCenter(addCenter());
		Scene scene=new Scene(bp,800,800);

		return scene;


	}

	public AcountView(Profile acount, ServerWorks sw,clientFirstPage cfp) {
		super();
		this.account = acount;
		this.sw = sw;
		this.cfp=cfp;
	}
	public HBox addTop() throws FileNotFoundException{
		
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: #efefef;-fx-fill-height:true;");
		hbox.setSpacing(1);
		Button simpleSearch = new Button("");
		simpleSearch.setPrefSize(270, 60);
		simpleSearch.setStyle("-fx-background-color:null;");
		Image searchimage = new Image(new FileInputStream("src/icons/simple_search.png"));
		simpleSearch.setGraphic(new ImageView(searchimage));
		simpleSearch.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Profile searched;
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Search");
				alert.setHeaderText("Search For A User");
				alert.setContentText(" ");
				final Text label = new Text("Search By Name");
				final TextField textArea = new TextField("");
				textArea.setEditable(true);
				textArea.setMaxWidth(Double.MAX_VALUE);
				textArea.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea, Priority.ALWAYS);
				GridPane.setHgrow(textArea, Priority.ALWAYS);
				GridPane expContent = new GridPane();
				expContent.setMinWidth(600);
				expContent.setMaxWidth(600);
				expContent.add(label, 0, 0);
				expContent.add(textArea, 0, 1);

				final Text label2 = new Text("Search By Number");
				final TextField textArea2 = new TextField("");
				textArea2.setEditable(true);
				textArea2.setMaxWidth(Double.MAX_VALUE);
				textArea2.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea2, Priority.ALWAYS);
				GridPane.setHgrow(textArea2, Priority.ALWAYS);
				expContent.add(label2, 0, 3);
				expContent.add(textArea2, 0, 4);

				final Text label3 = new Text("Search By E-Mail");
				final TextField textArea3 = new TextField("");
				textArea3.setEditable(true);
				textArea3.setMaxWidth(Double.MAX_VALUE);
				textArea3.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea3, Priority.ALWAYS);
				GridPane.setHgrow(textArea3, Priority.ALWAYS);
				expContent.add(label3, 0, 5);
				expContent.add(textArea3, 0, 6);

				ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				ButtonType buttonTypeConfirm1 = new ButtonType("By Name", ButtonData.OK_DONE);
				ButtonType buttonTypeConfirm2 = new ButtonType("By Number", ButtonData.OK_DONE);
				ButtonType buttonTypeConfirm3 = new ButtonType("By E-Mail", ButtonData.OK_DONE);
				alert.getButtonTypes().setAll(buttonTypeCancel,buttonTypeConfirm1,buttonTypeConfirm2,buttonTypeConfirm3);
				alert.getDialogPane().setContent(expContent);
				Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(icon);
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get()!=buttonTypeCancel){
					if(result.get()==buttonTypeConfirm1){
						try {
							searched=sw.simpleSearch(textArea.getText());
							if(searched!=null){

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										try {
											Profile searchAgain=sw.simpleSearch(textArea.getText());//because of final object error											
											StrangeAccset(searchAgain, sw, cfp,AV,account);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
								});
							}
							else{
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										final Alert alert = new Alert(AlertType.WARNING);
										alert.setTitle("Sorry :(");
										alert.setHeaderText("We have a problem!");
										alert.setContentText("The user not found!\nPlease try again.");
										Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
										stage2.getIcons().add(icon); // To add an icon
										alert.showAndWait();
									}
								});
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}else if(result.get()==buttonTypeConfirm2){
						try {
							searched=sw.simpleSearchByNum(textArea2.getText());
							if(searched!=null){

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										try {
											Profile searchAgain=sw.simpleSearchByNum(textArea2.getText());//because of final object error											
											StrangeAccset(searchAgain, sw, cfp,AV,account);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
								});
							}
							else{
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										final Alert alert = new Alert(AlertType.WARNING);
										alert.setTitle("Sorry :(");
										alert.setHeaderText("We have a problem!");
										alert.setContentText("The user not found!\nPlease try again.");
										Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
										stage2.getIcons().add(icon); // To add an icon
										alert.showAndWait();
									}
								});
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}else if(result.get()==buttonTypeConfirm3){
						try {
							searched=sw.simpleSearchByMail(textArea3.getText());
							if(searched!=null){

								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										try {
											Profile searchAgain=sw.simpleSearchByMail(textArea3.getText());//because of final object error											
											StrangeAccset(searchAgain, sw, cfp,AV,account);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
								});
							}
							else{
								Platform.runLater(new Runnable() {
									@Override
									public void run() {
										final Alert alert = new Alert(AlertType.WARNING);
										alert.setTitle("Sorry :(");
										alert.setHeaderText("We have a problem!");
										alert.setContentText("The user not found!\nPlease try again.");
										Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
										stage2.getIcons().add(icon); // To add an icon
										alert.showAndWait();
									}
								});
							}
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}


			}
		});
		Button addPost = new Button("");
		addPost.setPrefSize(270, 60);
		addPost.setStyle("-fx-background-color:null;");
		Image addimage = new Image(new FileInputStream("src/icons/add_post.png"));
		addPost.setGraphic(new ImageView(addimage));
		addPost.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Add New Post");
				alert.setHeaderText("Add new Post");
				alert.setContentText("Type something here . . .");
				final Text label = new Text("Type something in this field . . .");
				final TextArea textArea = new TextArea("");
				textArea.setEditable(true);
				textArea.setWrapText(true);
				textArea.setMaxWidth(Double.MAX_VALUE);
				textArea.setMaxHeight(Double.MAX_VALUE);
				GridPane.setVgrow(textArea, Priority.ALWAYS);
				GridPane.setHgrow(textArea, Priority.ALWAYS);
				GridPane expContent = new GridPane();
				expContent.setMaxWidth(Double.MAX_VALUE);
				expContent.add(label, 0, 0);
				expContent.add(textArea, 0, 1);
				Button selectimg=new Button("Select Image");
				selectimg.setMinSize(600,40 );
				final StringBuffer strIMG=new StringBuffer();
				selectimg.setOnMouseClicked(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Choose a Image");
						fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
								);                 
						fileChooser.getExtensionFilters().addAll(
								new FileChooser.ExtensionFilter("JPG", "*.jpg"),
								new FileChooser.ExtensionFilter("PNG", "*.png")
								);
						File fileIMAGE2=fileChooser.showOpenDialog( cfp.stageMain);
						strIMG.append(fileIMAGE2.getPath());
						

					}
				});
			
			
				expContent.add(selectimg, 0, 2);
				
				
				ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
				ButtonType buttonTypeConfirm = new ButtonType("Confirm", ButtonData.OK_DONE);
				alert.getButtonTypes().setAll(buttonTypeCancel,buttonTypeConfirm);
				alert.getDialogPane().setContent(expContent);
				Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(icon);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get()!=buttonTypeCancel){
					Post post=new Post(textArea.getText(),strIMG.toString(), account);
					try {
						sw.setPostServer(account, post);
						account.getPosts().add(post);
						bp.setCenter(addCenter());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		final MenuButton bfs = new MenuButton("");
		bfs.setPrefSize(8, 60);
		bfs.setStyle("-fx-background-color:null;");
		bfs.setPadding(new Insets(0,0,0,80));
		Image bfsimage = new Image(new FileInputStream("src/icons/bfs_search.png"));
		ImageView sim=new ImageView(bfsimage);
		bfs.setGraphic(sim);
		bfs.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				try {
					bfs.getItems().clear();
					int level=BFS.Level(account.getGraph());
					for(int i=0;i<level;i++){
						MenuItem menuItem = new MenuItem("Level "+(i+1));
						final bfsShow bsfshow=new bfsShow(account, sw, cfp, AV, account, i+1);
						menuItem.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent arg0) {
								try {
									cfp.stageMain.setScene(bsfshow.getScene());
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
						bfs.getItems().add(menuItem);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		hbox.getChildren().addAll(simpleSearch, addPost,bfs);

		return hbox;
	}
	public Pane addDown() throws FileNotFoundException{
		DropShadow ds1 = new DropShadow();
		ds1.setOffsetY(-5.0f);
		ds1.setOffsetX(0.0f);
		ds1.setColor(Color.grayRgb(200));

		final Pane pane=new Pane();

		pane.setMinSize(800, 250);
		pane.setStyle("  -fx-background-color: linear-gradient(to bottom, derive(#e0e0e0,30%),#d7e3e5);");
		Line line=new Line();
		line.setStartX(0);
		line.setStartY(0);
		line.setEndX(900);
		line.autosize();
		line.setStrokeWidth(0.5);
		line.setStyle("-fx-stroke:#e0e0e0");
		line.setEffect(ds1);

		Text name=new Text(account.getName());
		name.setX(190);
		name.setY(100);
		name.setFont(font1);
		name.setFill(Color.BLACK); 
		if(!account.getEmail().equals("null")){
			Text email=new Text(account.getEmail());
			email.setX(190);
			email.setY(130);
			email.setFont(font4);
			email.setFill(Color.rgb(112, 112, 112)); 
			pane.getChildren().add(email);
		}else{
			final Button email=new Button("Add Email");
			email.setLayoutX(190);
			email.setLayoutY(120);
			email.setFont(font5);
			email.wrapTextProperty();
			email.setMaxSize(90, 10);
			email.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					TextInputDialog dialog = new TextInputDialog("");
					dialog.setTitle("Set Email");
					dialog.setHeaderText("Please enter your Email bellow.\nYou Can't change it later!!");
					dialog.setContentText("Please enter your email :");
					Stage stage2 = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(icon);
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
						boolean check;
						try {
							check = (sw.checkUniqueMail(account, result.get()));
							if(check){
								account.setEmail(result.get());
								email.setVisible(false);
								Text emailT=new Text(account.getEmail());
								emailT.setX(190);
								emailT.setY(130);
								emailT.setFont(font4);
								emailT.setFill(Color.rgb(112, 112, 112)); 
								pane.getChildren().add(emailT);
							}else{
								final Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Email Error");
								alert.setHeaderText("Server :");
								alert.setContentText("There is a acount with this email!\nPlease try again.");
								Stage stage3 = (Stage) alert.getDialogPane().getScene().getWindow();
								stage3.getIcons().add(icon); // To add an icon
								alert.showAndWait();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}


			});
			pane.getChildren().add(email);
		}
		if(!account.getNumber().equals("null")){
			Text number=new Text(account.getNumber());
			number.setX(190);
			number.setY(160);
			number.setFont(font4);
			number.setFill(Color.rgb(112, 112, 112)); 
			pane.getChildren().add(number);
		}else{
			final Button number=new Button("Add Number");
			number.setLayoutX(190);
			number.setLayoutY(160);
			number.setFont(font5);
			number.setMaxSize(110, 10);
			number.wrapTextProperty();
			number.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					TextInputDialog dialog = new TextInputDialog("");
					dialog.setTitle("Set Number");
					dialog.setHeaderText("Please enter your Number bellow.\nYou Can't change it later!!");
					dialog.setContentText("Please enter your Number :");
					Stage stage2 = (Stage) dialog.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(icon); 
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
						boolean check;
						try {
							check = (sw.checkUniqueNum(account, result.get()));
							if(check){
								account.setNumber(result.get());
								number.setVisible(false);
								Text numberT=new Text(account.getNumber());
								numberT.setX(190);
								numberT.setY(160);
								numberT.setFont(font4);
								numberT.setFill(Color.rgb(112, 112, 112)); 
								pane.getChildren().add(numberT);
							}else{
								final Alert alert = new Alert(AlertType.WARNING);
								alert.setTitle("Number Error");
								alert.setHeaderText("Server :");
								alert.setContentText("There is a acount with this number!\nPlease try again.");
								Stage stage3 = (Stage) alert.getDialogPane().getScene().getWindow();
								stage3.getIcons().add(icon); // To add an icon
								alert.showAndWait();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			});
			pane.getChildren().add(number);
		}
		Line line2=new Line();
		line2.setStartX(400);
		line2.setStartY(20);
		line2.setEndY(230);
		line2.setEndX(400);
		line2.autosize();
		line2.setStrokeWidth(1);
		line2.setStyle("-fx-stroke:#b7b7b7");
		line2.setEffect(ds1);

		final Button notif = new Button("");
		notif.setLayoutX(330);
		notif.setLayoutY(10);
		notif.setPrefSize(60, 60);
		notif.setStyle("-fx-background-color:null;");
		if(account.notifications.size()==0){
			Image notifOff = new Image(new FileInputStream("src/icons/notif_off.png"));
			notif.setGraphic(new ImageView(notifOff));
		}else{
			Image notifOn = new Image(new FileInputStream("src/icons/notif_on.png"));
			notif.setGraphic(new ImageView(notifOn));
		}
		notif.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {

							notifPage np=new notifPage(account, sw, cfp, AV, account);
							cfp.stageMain.setScene(np.getScene());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});
		pane.getChildren().add(notif);
		
		final Text notifCount=new Text(String.valueOf(account.notifications.size()));
		notifCount.setX(320);
		notifCount.setFill(Color.GRAY);
		notifCount.setY(45);
		notifCount.setFont(font4);
		pane.getChildren().add(notifCount);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
			while(true){
				notifCount.setText(String.valueOf(account.notifications.size()));

			}
			}
		}).start();
		
		Text biotitle=new Text("Biography");
		biotitle.setX(565);
		biotitle.setY(40);
		biotitle.setFont(font4);
		biotitle.setFill(Color.BLACK);

		if(!account.getBiography().equals("null")){
			Text bio=new Text(account.getBiography());
			bio.setFont(font4);
			bio.setWrappingWidth(300);
			bio.setX(450);
			bio.setY(63);
			bio.setTextAlignment(TextAlignment.CENTER);
			pane.getChildren().add(bio);
		}else{
			final Button bio=new Button("Add Biography");	
			bio.setLayoutX(530);
			bio.setLayoutY(63);
			bio.setFont(font4);
			bio.setMaxSize(140, 20);
			bio.setOnMouseClicked(new EventHandler<Event>() {
				public void handle(Event arg0) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Add Biography");
					alert.setHeaderText("Add Your Biography");
					alert.setContentText("Please add your biography");
					Label label = new Label("Please add your biography :");
					TextArea textArea = new TextArea("");
					textArea.setEditable(true);
					textArea.setWrapText(true);
					textArea.setMaxWidth(Double.MAX_VALUE);
					textArea.setMaxHeight(Double.MAX_VALUE);
					GridPane.setVgrow(textArea, Priority.ALWAYS);
					GridPane.setHgrow(textArea, Priority.ALWAYS);
					GridPane expContent = new GridPane();
					expContent.setMaxWidth(Double.MAX_VALUE);
					expContent.add(label, 0, 0);
					expContent.add(textArea, 0, 1);
					ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
					ButtonType buttonTypeConfirm = new ButtonType("Confirm", ButtonData.OK_DONE);
					alert.getButtonTypes().setAll(buttonTypeCancel,buttonTypeConfirm);
					alert.getDialogPane().setContent(expContent);
					Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
					stage2.getIcons().add(icon);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get()!=buttonTypeCancel){
						try {
							sw.setBioServer(account, textArea.getText());
							account.setBiography(textArea.getText());
							bio.setVisible(false);
							Text bioT=new Text(account.getBiography());
							bioT.setFont(font4);
							bioT.setWrappingWidth(300);
							bioT.setX(450);
							bioT.setY(63);
							bioT.setTextAlignment(TextAlignment.CENTER);
							pane.getChildren().add(bioT);	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
					}
				}
			});
			pane.getChildren().add(bio);
		}
		Line line3=new Line();
		line3.setStartX(565);
		line3.setStartY(45);
		line3.setEndY(45);
		line3.setEndX(638);
		line3.autosize();
		line3.setStrokeWidth(1);
		line3.setStyle("-fx-stroke:#000000");
		line3.setEffect(ds1);

		Text following=new Text("Followings");
		following.setFont(font4);
		following.setX(500);
		following.setY(180);
		following.setTextAlignment(TextAlignment.CENTER);
		following.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {

							followingPage fp=new followingPage(account, sw, cfp, AV, account);
							cfp.stageMain.setScene(fp.getScene());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});

		Text followers=new Text("Followers");
		followers.setFont(font4);
		followers.setX(625);
		followers.setY(180);
		followers.setTextAlignment(TextAlignment.CENTER);
		followers.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {

							followersPage fs=new followersPage(account, sw, cfp, AV, account);
							cfp.stageMain.setScene(fs.getScene());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});

		Text flwngCount=new Text(String.valueOf(account.getGraph().source.followings.size()));
		flwngCount.setFont(font4);
		flwngCount.setX(525);
		flwngCount.setY(200);
		flwngCount.setTextAlignment(TextAlignment.CENTER);

		Text flwsCount=new Text(String.valueOf(account.getFollowers().size()));
		flwsCount.setFont(font4);
		flwsCount.setX(650);
		flwsCount.setY(200);
		flwsCount.setTextAlignment(TextAlignment.CENTER);
		final Button imgChange=new Button("Change Photo");	
		imgChange.setLayoutX(30);
		imgChange.setLayoutY(55);
		imgChange.setFont(font4);
		imgChange.setMaxSize(140, 20);
		imgChange.setStyle(
				"-fx-background-radius: 5em; " +
						"-fx-min-width: 140px; " +
						"-fx-min-height: 140px; " +
						"-fx-max-width:140px; " +
						"-fx-max-height: 140px;"
				);
		imgChange.setVisible(false);

		pane.getChildren().add(imgChange);

		ImagePattern prof_pic=new ImagePattern(new Image(new FileInputStream(account.getIcon())));
		final Circle circle = new Circle(70);
		circle.setCenterX(100);
		circle.setCenterY(125);
		circle.setFill(prof_pic);
		circle.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				Thread imgButton=new Thread(new Runnable() {
					@Override
					public void run() {
						imgChange.setVisible(true);
						circle.setFill(null);
						try {
							Thread.sleep(5000);
							imgChange.setVisible(false);
							circle.setFill(new ImagePattern(new Image(new FileInputStream(account.getIcon()))));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				imgButton.start();
			}
		});
		pane.getChildren().addAll(name,line,circle,line2,biotitle,line3,following,followers,flwngCount,flwsCount);
		imgChange.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Choose a Image");
				fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
						);                 
				fileChooser.getExtensionFilters().addAll(
						new FileChooser.ExtensionFilter("JPG", "*.jpg"),
						new FileChooser.ExtensionFilter("PNG", "*.png")
						);
				File file=fileChooser.showOpenDialog( cfp.stageMain);
				try {
					sw.setIconServer(account,file.getPath());
					account.setIcon(file.getPath());
					circle.setFill(new ImagePattern(new Image(new FileInputStream(account.getIcon()))));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return pane;

	}
	private ScrollPane addCenter() throws FileNotFoundException, MalformedURLException {
		DropShadow ds1 = new DropShadow();
		ds1.setOffsetY(-5.0f);
		ds1.setOffsetX(0.0f);
		ds1.setColor(Color.grayRgb(200));

		VBox vbox=new VBox();
		if(account.getPosts().size()==0){
			Text nopost=new Text("No Post is here! :(");
			nopost.setFont(font4);
			nopost.setWrappingWidth(600);
			nopost.setX(100);
			nopost.setY(100);
			nopost.setTextAlignment(TextAlignment.CENTER);
			Pane pane=new Pane();
			pane.getChildren().addAll(nopost);
			vbox.getChildren().addAll(pane);
		}else{
			for(int i=account.getPosts().size()-1;i>=0;i--){

				ImagePattern prof_pic=new ImagePattern(new Image(new FileInputStream(account.getIcon())));
				Circle circle = new Circle(25);
				circle.setCenterX(40);
				circle.setCenterY(40);
				circle.setFill(prof_pic);
				File file=new File(account.getPosts().get(i).imgPath);
				URL url = file.toURI().toURL();
				Image imgPost=new Image(url.toExternalForm());
				ImageView postimg = new ImageView(imgPost);
				postimg.setFitHeight(400);
				postimg.setFitWidth(400);
				postimg.setX(200);
				postimg.setY(40);
				
				Text creatorName=new Text(account.getPosts().get(i).getCreator().getName()+" :");
				creatorName.setFont(font4);
				creatorName.setWrappingWidth(600);
				creatorName.setX(75);
				creatorName.setY(45);

				Text post=new Text(account.getPosts().get(i).getText());
				post.setFont(font4);
				post.setWrappingWidth(600);
				post.setX(100);
				post.setY(470);
				post.setTextAlignment(TextAlignment.CENTER);

				final Text likes=new Text(String.valueOf(account.getPosts().get(i).getLikes().size())+ " Likes");
				likes.setFont(font4);
				likes.setWrappingWidth(600);
				likes.setX(100);
				likes.setY(580);
				likes.setTextAlignment(TextAlignment.CENTER);

				Button like = new Button("");
				like.setPrefSize(100, 60);
				like.setStyle("-fx-background-color:null;");
				Image likeimg = new Image(new FileInputStream("src/icons/like_inactive.png"));
				final ImageView iv=new ImageView(likeimg);
				iv.setFitWidth(20);
				iv.setFitHeight(20);
				like.setGraphic(iv);
				like.setTextAlignment(TextAlignment.CENTER);
				like.setLayoutX(350);
				like.setLayoutY(565);
				//using String Buffer because of final type errors
				final StringBuffer clicked=new StringBuffer("true");
				final Integer j=i;
				for(int k=0;k<account.getPosts().get(j).getLikes().size();k++){
					if(account.getPosts().get(j).getLikes().get(k).getLiker().getName().equals(account.getName())){
						clicked.delete(0, clicked.length());
						clicked.append("falseRED");
						Image likeimg_active;
						try {
							likeimg_active = likeimg_active = new Image(new FileInputStream("src/icons/like_active.png"));
							iv.setImage(likeimg_active);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				like.setOnMouseClicked(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						//						Liker liker=new Liker();
						//						liker.like(account.getPosts().get(j), account, likes, clicked, iv, sw);


						if(clicked.toString().equals("true")){
							try {
								Image likeimg_active=likeimg_active = new Image(new FileInputStream("src/icons/like_active.png"));
								iv.setImage(likeimg_active);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							//							Like liked=new Like(post, account);
							account.getPosts().get(j).addLike(new Like(account.getPosts().get(j), account));
							try {
								sw.setLikeServer(account.getPosts().get(j));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							likes.setText(String.valueOf(account.getPosts().get(j).getLikes().size())+ " Likes");
							clicked.delete(0, clicked.length());
							clicked.append("false");
						}else{
							try {
								if(!clicked.equals("falseRED")){
									Image likeimg_inactive = new Image(new FileInputStream("src/icons/like_inactive.png"));
									iv.setImage(likeimg_inactive);
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							Like liked=new Like(account.getPosts().get(j), account);
							account.getPosts().get(j).deleteLike(liked);
							try {
								sw.deleteLikeServer(account.getPosts().get(j));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							likes.setText(String.valueOf(account.getPosts().get(j).getLikes().size())+ " Likes");
							clicked.delete(0, clicked.length());
							clicked.append("true");
						}

					}
				});

				Line line=new Line();
				line.setStartX(0);
				line.setStartY(620);
				line.setEndX(900);
				line.setEndY(620);
				line.setStrokeWidth(1);
				line.setStyle("-fx-stroke:#e0e0e0");
				line.setEffect(ds1);

				Pane pane=new Pane();
				pane.getChildren().addAll(post,circle,creatorName,line,likes,like,postimg);
				vbox.getChildren().addAll(pane);
			}
		}
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vbox);
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		return scrollPane;
	}

	@Override
	public void start(Stage arg0) throws Exception {

	}
	public void StrangeAccset(final Profile searchAgain,final ServerWorks sw,
			final clientFirstPage cfp,final AcountView AV,final  Profile account) throws Exception{

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					StrangeAccountView sav=new StrangeAccountView(searchAgain, sw, cfp,AV,account);
					cfp.stageMain.setScene(sav.getScene());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
	}
}
