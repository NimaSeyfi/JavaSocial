package SERVER;

import java.io.Serializable;

public class Edge  implements Serializable{
	public Profile v1, v2;
    int weight;
    public Edge addEdge(Profile ver1,Profile ver2,int W){
    	if(ver1.equals(ver2)){
    		return null;
    	}else{
    		ver1.followings.add(ver2);
    		return new Edge(ver1,ver2,1);
    	}
    }
	public Edge(Profile v1, Profile v2, int weight) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	public String toString(){
		return v1.getName() +"--->"+v2.getName();
	}
}
