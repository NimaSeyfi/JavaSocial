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
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import SERVER.Edge;
import SERVER.Like;
import SERVER.Post;
import SERVER.Profile;
import SERVER.ServerUp;
import SERVER.Vertex;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
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
import javafx.util.Pair;

public class StrangeAccountView extends Application {
	//	Profile account=new Profile("nima");
	Profile account;
	Profile reqAccount;
	clientFirstPage cfp;
	AcountView AV;
	int COUNTFORLOADING=0;
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
final Thread updater=new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(9999);
						account=sw.updater(account);
						COUNTFORLOADING++;
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
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					
					try {
						Thread.sleep(11999);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {

							try {
								
								bp.setCenter(addCenter());
								bp.setBottom(addDown());
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					});
				
				
				}
			}
		}).start();

		Scene scene=new Scene(bp,800,800);
		return scene;
	}

	public StrangeAccountView(Profile acount, ServerWorks sw,clientFirstPage cfp,AcountView AV,Profile reqAccount) {
		super();
		this.account = acount;
		this.sw = sw;
		this.cfp=cfp;
		this.reqAccount=reqAccount;
		this.AV=AV;
	}
	public HBox addTop() throws FileNotFoundException{
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: #efefef;-fx-fill-height:true;");
		hbox.setSpacing(1);
		Button back = new Button("");
		back.setPrefSize(400, 60);
		back.setStyle("-fx-background-color:null;");
		Image searchimage = new Image(new FileInputStream("src/icons/back.png"));
		back.setGraphic(new ImageView(searchimage));
		back.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				final AcountView ac=new AcountView(reqAccount, sw, cfp);
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							cfp.stageMain.setScene(ac.getScene());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

			}
		});
		hbox.getChildren().addAll(back);

		Button follow = new Button("");
		follow.setPrefSize(400, 60);
		follow.setStyle("-fx-background-color:null;");
		Image followImg = new Image(new FileInputStream("src/icons/follow.png"));
		Image unfollow = new Image(new FileInputStream("src/icons/unFollow.png"));

		Button unfollowButton = new Button("");
		unfollowButton.setPrefSize(400, 60);
		unfollowButton.setStyle("-fx-background-color:null;");
		Image unfollowImg = new Image(new FileInputStream("src/icons/unFollow.png"));

		final Vertex v2=account.getGraph().source;

		final Edge edge=new Edge(reqAccount, account, 1);
		edge.addEdge(reqAccount, account, 1);
		if(checkGraph(account)==true){
			unfollowButton.setGraphic(new ImageView(unfollowImg));
			hbox.getChildren().addAll(unfollowButton);
			unfollowButton.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					for(int i=0;i<reqAccount.getGraph().source.followings.size();i++){
						if(reqAccount.getGraph().source.followings.get(i).profile.getName().equals(	v2.profile.getName())){
							reqAccount.getGraph().source.followings.remove(reqAccount.getGraph().source.followings.get(i));
						
						}
					}
					for(int i=0;i<			account.followers.size();i++){
						if(		account.followers.get(i).getName().equals(reqAccount.getName())){
							account.followers.remove(account.followers.get(i));
						}
					}
					for(int i=0;i<	reqAccount.graph.edges.size();i++){
						if(reqAccount.graph.edges.get(i).v2.getName().equals(account)){
							reqAccount.graph.edges.remove(reqAccount.graph.edges.get(i));
						}
					}
					try {
						reqAccount=sw.sendGraph(reqAccount);
						account=sw.updateProfile(account);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						AV.StrangeAccset(account, sw, cfp, AV, reqAccount);
						System.out.println("changed");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}else{
			follow.setGraphic(new ImageView(followImg));
			follow.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {

					reqAccount.graph.source.followings.add(v2);
					Edge edge=new Edge(reqAccount, account, 1);
					edge.addEdge(reqAccount, account, 1);
					reqAccount.graph.edges.add(edge);
					account.followers.add(reqAccount);
					try {
						reqAccount=sw.sendGraph(reqAccount);
						account=sw.updateProfile(account);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						AV.StrangeAccset(account, sw, cfp, AV, reqAccount);
						System.out.println("changed");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for(int i=0;i<reqAccount.graph.vertexs.size();i++){
						for(int j=0;j<reqAccount.graph.vertexs.get(i).followings.size();j++){
							System.out.println(	reqAccount.graph.source.profile.name+"--->"+reqAccount.graph.vertexs.get(i).followings.get(j).profile.name);
						}
					}

				}
			});
			hbox.getChildren().addAll(follow);
		}


		return hbox;
	}
	private boolean checkGraph(Profile account2) {

		for(int i=0;i<reqAccount.getGraph().source.followings.size();i++){
			if(reqAccount.getGraph().source.followings.get(i).profile.getName().equals(account2.getName())){
				System.out.println("found");
				return true;
				
			}else{
				System.out.println("notfound");
			}
		}
		return  false;
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
			Text email=new Text("-----");
			email.setX(190);
			email.setY(130);
			email.setFont(font4);
			email.setFill(Color.rgb(112, 112, 112)); 
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
			Text number=new Text("-----");
			number.setX(190);
			number.setY(160);
			number.setFont(font4);
			number.setFill(Color.rgb(112, 112, 112)); 
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
			Text bio=new Text("---------");
			bio.setFont(font4);
			bio.setWrappingWidth(300);
			bio.setX(450);
			bio.setY(63);
			bio.setTextAlignment(TextAlignment.CENTER);
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

							followingPage fp=new followingPage(account, sw, cfp, AV, reqAccount);
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

							followersPage fs=new followersPage(account, sw, cfp, AV, reqAccount);
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

		pane.getChildren().addAll(name,line,circle,line2,biotitle,line3,following,followers,flwngCount,flwsCount);
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

			if(COUNTFORLOADING==0)
				nopost.setText("Loading . . .");
			else
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
						if(clicked.toString().equals("true")){
							try {
								Image likeimg_active=likeimg_active = new Image(new FileInputStream("src/icons/like_active.png"));
								iv.setImage(likeimg_active);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
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
}
