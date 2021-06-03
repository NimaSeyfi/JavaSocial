package CLIENT;


import java.util.ArrayList;

import sun.misc.Queue;
import SERVER.FriendsGraph;
import SERVER.Profile;
import SERVER.Vertex;


public class BFS{

	public static int Level(FriendsGraph g) throws InterruptedException{
		Queue<ArrayList<Profile>> q=new  Queue<ArrayList<Profile>>();
		ArrayList<Vertex> main=new ArrayList<Vertex>();
		ArrayList<String> mainTemp=new ArrayList<String>();
		int level=0;
		Vertex tmp=g.source;
		main.add(tmp);
		while(main.size()!=0){
			System.out.println("main size:"+main.size());
			
			ArrayList<Profile> arr=new ArrayList<Profile>();
			for(int i=0;i<main.size();i++){
				System.out.println(main.get(i).profile.getName()+main.get(i).followings.size());
				for(int j=0;j<main.get(i).followings.size();j++){
					if(!mainTemp.contains(main.get(i).followings.get(j).profile.getName())){
						arr.add(main.get(i).followings.get(j).profile);
						main.add(main.get(i).followings.get(j));
					}
				}

				mainTemp.add(main.get(i).profile.getName());
				main.remove(main.get(i));
				System.out.println(mainTemp.toString());
			}
			q.enqueue(arr);
			level++;
		}

		while(!q.isEmpty()){
			ArrayList<Profile> a=q.dequeue();
			for(int l=0;l<a.size();l++){
				System.out.print(a.get(l).getName()+" ,");
				
			}
			System.out.println();
		}
		level--;
		return level;
	}

	public static ArrayList<Profile> LevelArray(FriendsGraph graph,int index) throws InterruptedException {
		Queue<ArrayList<Profile>> q=new  Queue<ArrayList<Profile>>();
		ArrayList<Vertex> main=new ArrayList<Vertex>();
		ArrayList<String> mainTemp=new ArrayList<String>();
		int level=0;
		Vertex tmp=graph.source;
		main.add(tmp);
		while(main.size()!=0){
			ArrayList<Profile> arr=new ArrayList<Profile>();
			for(int i=0;i<main.size();i++){
				for(int j=0;j<main.get(i).followings.size();j++){
					if(!mainTemp.contains(main.get(i).followings.get(j).profile.getName())){
						arr.add(main.get(i).followings.get(j).profile);
						main.add(main.get(i).followings.get(j));
					}
				}

				mainTemp.add(main.get(i).profile.getName());
				main.remove(main.get(i));
			}
			q.enqueue(arr);
			level++;
		}

		for(int k=0;k<index-1;k++){
			q.dequeue();
		}
		level--;
		return q.dequeue();
	}
}