package SERVER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Profile implements Serializable{
	public String name;
	public String email;
	public String biography;
	public String number;
	public boolean online=false;
	public String icon;
	public Vertex profileVertex;
	public FriendsGraph graph=new FriendsGraph();
	String pass;
	public ArrayList<Post> posts=new ArrayList<Post>();
	public ArrayList<Post> notifications=new ArrayList<Post>();
	public ArrayList<Profile> followers=new ArrayList<Profile>();
	public ArrayList<Profile> followings=new ArrayList<Profile>();
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) throws FileNotFoundException {

		Path filePath= Paths.get(icon);
		if(Files.exists(filePath)){
			this.icon=icon;
		}else{
			this.icon="src/SERVER/img/default_prof.png";
		}

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public ArrayList<Post> getPosts() {
		return posts;
	}
	public void setPosts(ArrayList<Post> posts) {
		this.posts = posts;
	}
	public ArrayList<Profile> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<Profile> followers) {
		this.followers = followers;
	}
	public Vertex getProfileVertex() {
		return profileVertex;
	}
	public void setProfileVertex(Vertex profileVertex) {
		this.profileVertex = profileVertex;
	}
	public FriendsGraph getGraph() {
		return graph;
	}
	public void setGraph(FriendsGraph graph) {
		this.graph = graph;
	}
	public ArrayList<Profile> getFollowings() {
		return followings;
	}
	public void setFollowings(ArrayList<Profile> followings) {
		this.followings = followings;
	}
	public Profile(String name) {
		super();
		this.name = name;
		profileVertex=new Vertex(this);
		this.graph.source=profileVertex;
		this.graph.vertexs.add(profileVertex);
	}

}
