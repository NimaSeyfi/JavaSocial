package CLIENT;

import java.io.File;
import java.util.Optional;

import SERVER.Profile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Alerts extends Application {
	private File file = new File("src/LOGO/icon.png");
	final private Image icon = new Image(file.toURI().toString());
	@Override
	public void start(Stage arg0) throws Exception {
	}
	public void loginAlert() {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	final Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Login Error");
				alert.setHeaderText("We have problem with logging you in!");
				alert.setContentText("Username or Password is incorrect!\nPlease try again.");
				Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(icon); // To add an icon
				alert.showAndWait();
		    }
		});
	}

	public void signupAlert() {
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
		    	final Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("SignUp Error");
				alert.setHeaderText("We have problem with Signing you up!");
				alert.setContentText("a profile with this Username exist!\nPlease try again.");
				Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
				stage2.getIcons().add(icon); // To add an icon
				alert.showAndWait();
		    }
		});
	}
}
