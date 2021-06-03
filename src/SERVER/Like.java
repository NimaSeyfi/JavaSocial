package SERVER;

import java.io.Serializable;

public class Like implements Serializable {
	public Post post;
	public Profile liker;
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Profile getLiker() {
		return liker;
	}
	public Like() {
		super();
	}
	public Like(Post post, Profile liker) {
		this.post = post;
		this.liker = liker;
	}
	public void setLiker(Profile liker) {
		this.liker = liker;
	}
}
