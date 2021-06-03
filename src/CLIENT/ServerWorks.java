package CLIENT;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import SERVER.Like;
import SERVER.Post;
import SERVER.Profile;

public class ServerWorks {
	Socket s;
	Profile client;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	clientFirstPage cfp;
	AcountView AccView;
	Alerts Alerter=new Alerts();
	public ServerWorks(Socket s, ObjectOutputStream oos, ObjectInputStream ois,clientFirstPage clientfirst) {
		super();
		this.s = s;
		this.oos = oos;
		this.ois = ois;
		this.cfp=clientfirst;
	}
	public void sendLogin(final String user,final String pass,final String mode,final ServerWorks sw){

		Thread sendLog=new Thread(new Runnable() {
			public void run() {
				try {
					if(mode.equals("LOGIN")){
						oos.writeObject(mode);
						oos.flush();
						oos.writeObject(user);
						oos.flush();
						oos.writeObject(pass);
						oos.flush();
						client=(Profile) ois.readObject();
						if(client!=null){
							AccView=new AcountView(client, sw,cfp);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										cfp.stageMain.setScene(AccView.getScene());

									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						}else{
							Alerter.loginAlert();
						}
					}
					if(mode.equals("SIGNUP")){
						oos.writeObject(mode);
						oos.flush();
						oos.writeObject(user);
						oos.flush();
						oos.writeObject(pass);
						oos.flush();
						client=(Profile) ois.readObject();
						if(client!=null){
							AccView=new AcountView(client, sw,cfp);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									try {
										cfp.stageMain.setScene(AccView.getScene());
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							});
						}else{
							Alerter.signupAlert();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		sendLog.start();
	}
	public synchronized boolean checkUniqueMail(Profile profile,String string) throws IOException, ClassNotFoundException {

		oos.writeObject("CHECKMAIL");
		oos.flush();
		oos.writeObject(profile);
		oos.flush();
		oos.writeObject(string);
		oos.flush();
		boolean check=(Boolean) ois.readObject();
		return check;
	}
	public synchronized boolean checkUniqueNum(Profile profile,String string) throws IOException, ClassNotFoundException {
		oos.writeObject("CHECKNUM");
		oos.flush();
		oos.writeObject(profile);
		oos.flush();
		oos.writeObject(string);
		oos.flush();
		boolean check=(Boolean) ois.readObject();
		return check;
	}
	public synchronized void setBioServer(Profile account, String text) throws IOException {
		oos.writeObject("SETBIO");
		oos.flush();
		oos.writeObject(account);
		oos.flush();
		oos.writeObject(text);
		oos.flush();
	}
	public synchronized void setIconServer(Profile account, String path) throws IOException {
		oos.writeObject("SETICON");
		oos.flush();
		oos.writeObject(account);
		oos.flush();
		oos.writeObject(path);
		oos.flush();
	}
	public synchronized void setPostServer(Profile account, Post post) throws IOException {
		oos.writeObject("SETPOST");
		oos.flush();
		oos.writeObject(account);
		oos.flush();
		oos.writeObject(post);
		oos.flush();
	}
	public synchronized void setLikeServer(Post post) throws IOException{
		oos.writeObject("SETLIKE");
		oos.flush();
		oos.writeObject(post);
		oos.flush();
	}
	public synchronized void deleteLikeServer(Post post) throws IOException {
		oos.writeObject("DELLIKE");
		oos.flush();
		oos.writeObject(post);
		oos.flush();
	}
	public synchronized Profile simpleSearch(String username) throws IOException, ClassNotFoundException {
		oos.writeObject("SIMPLESEARCH");
		oos.flush();
		oos.writeObject(username);
		oos.flush();
		return (Profile) ois.readObject();
	}
	public synchronized Profile simpleSearchByNum(String number) throws IOException, ClassNotFoundException {
		oos.writeObject("SIMPLESEARCHBYNUM");
		oos.flush();
		oos.writeObject(number);
		oos.flush();
		return (Profile) ois.readObject();
	}
	public synchronized Profile simpleSearchByMail(String mail) throws IOException, ClassNotFoundException {
		oos.writeObject("SIMPLESEARCHBYMAIL");
		oos.flush();
		oos.writeObject(mail);
		oos.flush();
		return (Profile) ois.readObject();
	}
	public synchronized Profile sendGraph(Profile p) throws IOException, ClassNotFoundException {
		oos.writeObject("SETGRAPH");
		oos.flush();
		oos.writeObject(p);
		oos.flush();
		return (Profile) ois.readObject();
	}
	public synchronized  Profile updateProfile(Profile p) throws IOException, ClassNotFoundException {
		oos.writeObject("UPDATEPROFILE");
		oos.flush();
		oos.writeObject(p);
		oos.flush();
		return (Profile) ois.readObject();
	}
	public synchronized  Profile updater(Profile account) throws IOException, ClassNotFoundException {
		oos.writeObject("UPDATER");
		oos.flush();
		oos.writeObject(account);
		oos.flush();
		return (Profile) ois.readObject();
	}
}
