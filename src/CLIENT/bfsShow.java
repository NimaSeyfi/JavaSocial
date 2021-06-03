package CLIENT;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import SERVER.Edge;
import SERVER.Profile;
import SERVER.Vertex;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class bfsShow extends Application {
	Profile account;
	Profile reqAccount;
	clientFirstPage cfp;
	AcountView AV;
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
	public int level;
	public Scene getScene() throws Exception {
		//intializing nodes(components)
		File file = new File("src/LOGO/icon.png");
		final Image icon = new Image(file.toURI().toString());
		bp=new BorderPane();
		bp.setTop(addTop());
		bp.setCenter(addCenter());
		Scene scene=new Scene(bp,800,800);
		return scene;
	}
	private Node addCenter() throws InterruptedException {
		Font font1=new Font("Dolphian", 20);
		Font font2=new Font("2 Arabic Style", 15);
		Font font3=new Font("Dolphian", 45);
		Font font4=new Font("Tw Cen MT", 18);
		Font font5=new Font("Tw Cen MT", 14);

		int TX=0;
		int TY=0;
		//				while(true){
		Pane group=new Pane();
		final ArrayList<Profile> lvlprofiles=BFS.LevelArray(account.getGraph(),level);
		for(int i=0;i<lvlprofiles.size();i++){
			if(i==0){
				TX=160;
				TY=70;
			}

			Text t=new Text(lvlprofiles.get(i).getName());
			t.setX(TX);
			t.setY(TY);
			t.setFont(font4);

			Button showAcc=new Button("View Profile");
			showAcc.setLayoutX(TX+500);
			showAcc.setLayoutY(TY-20);
			showAcc.setFont(font5);
			showAcc.setMaxSize(110, 10);
			showAcc.wrapTextProperty();
			final int index=i;
			showAcc.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							try {

								StrangeAccountView asv=new StrangeAccountView(lvlprofiles.get(index), sw, cfp, AV, reqAccount);
								cfp.stageMain.setScene(asv.getScene());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				}
			});
			group.getChildren().add(showAcc);

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
				prof_pic = new ImagePattern(new Image(new FileInputStream(lvlprofiles.get(i).getIcon())));
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
		return scrollPane;
	}
	public bfsShow(Profile acount, ServerWorks sw,clientFirstPage cfp,AcountView AV,Profile reqAccount,int level) {
		super();
		this.account = acount;
		this.sw = sw;
		this.cfp=cfp;
		this.reqAccount=reqAccount;
		this.AV=AV;
		this.level=level;
	}
	public HBox addTop() throws FileNotFoundException{
		HBox hbox = new HBox();
		hbox.setStyle("-fx-background-color: #efefef;-fx-fill-height:true;");
		hbox.setSpacing(1);
		Button back = new Button("");
		back.setPrefSize(800, 60);
		back.setStyle("-fx-background-color:null;");
		Image searchimage = new Image(new FileInputStream("src/icons/back.png"));
		back.setGraphic(new ImageView(searchimage));
		back.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				final AcountView ac=new AcountView(account, sw, cfp);
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
return hbox;
	}
	@Override
	public void start(Stage arg0) throws Exception {

	}

}
