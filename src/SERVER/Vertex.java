package SERVER;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertex implements Serializable {
	public Profile profile;
	public ArrayList<Vertex> followings=new ArrayList<Vertex>();
	public Vertex(Profile vertex,Vertex added){
		this.profile=profile;
		followings.add(added);
	}
	public Vertex(Profile profile) {
		super();
		this.profile = profile;
	}
	
}
