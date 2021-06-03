package SERVER;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;

public class ClientWorks implements Runnable {
	Socket s;
	Profile client;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	ServerMainGUI smg;
	public ClientWorks(Socket s, ObjectOutputStream oos, ObjectInputStream ois,ServerMainGUI smg) {
		super();
		this.s = s;
		this.oos = oos;
		this.ois = ois;
		this.smg=smg;
	}
	@Override
	public synchronized void run() {
		String mode;
		while(true){
			try {

				mode=(String) ois.readObject();
				//login mode
				if(mode.equals("SETGRAPH")){
					Profile temp=(Profile) ois.readObject();
					oos.writeObject(setGraphMode(temp));
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();

				}
				if(mode.equals("UPDATEPROFILE")){
					Profile temp=(Profile) ois.readObject();

					oos.writeObject(updateProfileFollowers(temp));
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();

				}
				
				if(mode.equals("UPDATER")){
					synchronized(this){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					oos.writeObject(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)));
//					System.out.println(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).getName()+"- "
//					+ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).notifications.size()+" - sent");
					}
				}
				if(mode.equals("SIMPLESEARCH")){
					ServerUp.loadProfiles();
					String username=(String) ois.readObject();
					Profile profile=SearchProfileOnlyName(username);
					if(profile!=null)
					oos.writeObject(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)));
					else
						oos.writeObject(null);
				}
				if(mode.equals("SIMPLESEARCHBYNUM")){
					ServerUp.loadProfiles();
					String number=(String) ois.readObject();
					Profile profile=SearchProfileOnlyNum(number);
					if(profile!=null)
					oos.writeObject(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)));
					else
						oos.writeObject(null);
				}
				if(mode.equals("SIMPLESEARCHBYMAIL")){
					ServerUp.loadProfiles();
					String mail=(String) ois.readObject();
					Profile profile=SearchProfileOnlyMail(mail);
					if(profile!=null)
					oos.writeObject(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)));
					else
						oos.writeObject(null);
				}
				if(mode.equals("SETBIO")){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					String givenText=(String) ois.readObject();
					ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).setBiography(givenText);
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();
				}
				if(mode.equals("SETLIKE")){
					Post temp=(Post) ois.readObject();
					changePost(temp);					
					ServerUp.saveProfiles();
					ServerUp.saveProfiles();
				}
				if(mode.equals("DELLIKE")){
					Post temp=(Post) ois.readObject();
					changePost(temp);
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();
				}
				if(mode.equals("SETPOST")){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					Post givenPost=(Post) ois.readObject();
					ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).getPosts().add(givenPost);
					for(int i=0;i<	ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).followers.size();i++){
						for(int j=0;j<ServerUp.profiles.size();j++){
							if(profile!=null){
							if(ServerUp.profiles.get(j).getName().equals(ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).followers.get(i).getName())){
								ServerUp.profiles.get(j).notifications.add(givenPost);
								ServerUp.saveProfiles();
								ServerUp.loadProfiles();
							}
							}
						}
					}
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();
				}
				if(mode.equals("SETICON")){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					String givenPath=(String) ois.readObject();
					ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).setIcon(givenPath);
					ServerUp.saveProfiles();
					ServerUp.loadProfiles();
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							smg.stageMain.setScene(smg.suTemp.getScene());
						}
					});
				}
				if(mode.equals("CHECKMAIL")){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					String givenMail=(String) ois.readObject();
					boolean check=true;
					for(int i=0;i<ServerUp.profiles.size();i++){
						if(ServerUp.profiles.get(i).getEmail()!=null)
							if(ServerUp.profiles.get(i).getEmail().equals(givenMail)){
								check=false;
								break;
							}
					}
					oos.writeObject(check);
					if(check){
						ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).setEmail(givenMail);
						ServerUp.saveProfiles();
						ServerUp.loadProfiles();
					}
				}
				if(mode.equals("CHECKNUM")){
					Profile temp=(Profile) ois.readObject();
					Profile profile=SearchProfile(temp.getName(),temp.getPass());
					String givenNum=(String) ois.readObject();
					boolean check=true;
					for(int i=0;i<ServerUp.profiles.size();i++){
						if(ServerUp.profiles.get(i).getNumber()!=null)
							if(ServerUp.profiles.get(i).getNumber().equals(givenNum)){
								check=false;
								break;
							}
					}
					oos.writeObject(check);
					if(check){
						ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).setNumber(givenNum);
						ServerUp.saveProfiles();
						ServerUp.loadProfiles();
					}
				}
				if(mode.equals("LOGIN")){
					System.out.println(mode);
					String user=(String) ois.readObject();
					String pass=(String) ois.readObject();
					Profile profile=SearchProfile(user,pass);
					oos.writeObject(profile);
					if(profile!=null){

						ServerUp.profiles.get(ServerUp.profiles.indexOf(profile)).setOnline(true);
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								smg.stageMain.setScene(smg.suTemp.getScene());
							}
						});
						System.out.println("found");
					}else{
						System.out.println("not found");
					}
				}
				if(mode.equals("SIGNUP")){
					System.out.println(mode);
					String user=(String) ois.readObject();
					String pass=(String) ois.readObject();
					Profile profile=SearchProfile(user,pass);
					if(profile!=null){
						oos.writeObject(null);
					}else{
						profile=signUpProfile(user,pass);
						oos.writeObject(profile);
					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							smg.stageMain.setScene(smg.suTemp.getScene());
						}
					});
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	private Profile updateProfileSetLike(Profile temp) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).getName().equals(temp.getName())){
				//				ServerUp.profiles.remove(ServerUp.profiles.get(i));
				//				ServerUp.profiles.add(i, temp);
				ServerUp.profiles.get(i).posts=temp.posts;
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private Profile setGraphMode(Profile temp) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).getName().equals(temp.getName())){
				ServerUp.profiles.get(i).setGraph(temp.getGraph());
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private Profile updateProfileFollowers(Profile temp) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).getName().equals(temp.getName())){
				//				ServerUp.profiles.remove(ServerUp.profiles.get(i));
				//				ServerUp.profiles.add(i, temp);
				ServerUp.profiles.get(i).followers=temp.followers;
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private Profile SearchProfileOnlyName(String username) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).name.equals(username)){
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private Profile SearchProfileOnlyNum(String number) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).number.equals(number)){
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private Profile SearchProfileOnlyMail(String mail) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).email.equals(mail)){
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
	private void replacePost(Post temp, Post temp2) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			for(int j=0;j<ServerUp.profiles.get(i).getPosts().size();j++){
				if(ServerUp.profiles.get(i).getPosts().get(j).equals(temp)){
					System.out.println("EQULIZED");
					ServerUp.profiles.get(i).getPosts().remove(ServerUp.profiles.get(i).getPosts().get(j));
					ServerUp.profiles.get(i).getPosts().add(temp2);
				}
			}
		}
	}
	private void changePost(Post temp) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			for(int j=0;j<ServerUp.profiles.get(i).getPosts().size();j++){
				if(ServerUp.profiles.get(i).getPosts().get(j).getId()==temp.getId()){
					ServerUp.profiles.get(i).getPosts().remove(ServerUp.profiles.get(i).getPosts().get(j));
					ServerUp.profiles.get(i).getPosts().add(j,temp);
				}
			}
		}
	}
	private Profile signUpProfile(String user, String pass) throws IOException {
		Profile prof=new Profile(user);
		prof.setPass(pass);
		prof.setOnline(true);
		prof.setIcon("null");
		prof.setBiography("null");
		prof.setEmail("null");
		prof.setNumber("null");
		ServerUp.profiles.add(prof);
		ServerUp.saveProfiles();
		return prof;
	}
	private Profile SearchProfile(String user, String pass) {
		for(int i=0;i<ServerUp.profiles.size();i++){
			if(ServerUp.profiles.get(i).name.equals(user) && ServerUp.profiles.get(i).pass.equals(pass)){
				return ServerUp.profiles.get(i);
			}
		}
		return null;
	}
}
