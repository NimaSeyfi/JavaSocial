package SERVER;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.image.Image;

public class Post implements Serializable{
	public Profile creator;
	public long id;
	public String imgPath="\\src\\icons\\defalutPostImg.jpg";
	public String text;
	public ArrayList<Like> likes=new ArrayList<Like>();
	public ArrayList<Long> ids=new ArrayList<Long>();
	public Profile getCreator() {
		return creator;
	}
	public void setCreator(Profile creator) {
		this.creator = creator;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<Like> getLikes() {
		return likes;
	}
	public void setLikes(ArrayList<Like> likes) {
		this.likes = likes;
	}
	public void addLike(Like liked) {
			this.likes.add(liked);

	}
	public void deleteLike(Like liked) {
			for(int i=0;i<this.likes.size();i++){
				if(this.likes.get(i).getLiker().getName().equals(liked.getLiker().getName())){
					System.out.println(this.getLikes().size()+"del");
					for(int j=0;j<this.getLikes().size();j++){
						System.out.println("TEST:"+this.getLikes().get(j).getLiker().getName());
					}
					this.likes.remove(i);
				}
			}
			System.out.println(this.getLikes().size()+"DEL");
	}
	public void idGenerator() {//creates a new id that doesn't exist in id's
		long min = 100000000L;
		long max = 999999999L;
		long randomLong = min + (long) (Math.random() * (max - min));//generate a random long with range
		for(int i=0;i<ids.size();i++){
			if(ids.get(i)==randomLong){
				randomLong = min + (long) (Math.random() * (max - min));//generate a random long with range
				i=0;//search from first
			}
		}
		ids.add(randomLong);
		this.id=randomLong;
	}
	public Post(String text,Profile creator) {
		super();
		this.creator = creator;
		this.text = text;
		idGenerator();
	}
	public Post(String text,String path,Profile creator) {
		super();
		this.creator = creator;
		this.text = text;
		this.imgPath=path;
		idGenerator();
	}
	public Post(String text, Profile creator, long ID) {
		super();
		this.creator = creator;
		this.text = text;
		this.id=ID;
	}
}
