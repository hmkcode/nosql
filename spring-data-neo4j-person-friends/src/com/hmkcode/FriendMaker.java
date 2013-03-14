package com.hmkcode;



import java.util.LinkedList;

public class FriendMaker {

	public static int[][] friendsOf = null;
	//This method build unique friends for person
	public static int[][] makeFriends(){

		if(friendsOf != null)
			return friendsOf;
		
		friendsOf = new int[100][10];
		int randomPersonId;
		LinkedList<String> randomFriends;
		int f = 0;

		//for each person randomly pick 50 friends
		for(int i = 0; i < 100; i++){
			System.out.println("Working one.... "+(i+1));
			
			//pick 50 unique friends add them to linkedList
			//LinkedList.contains() will be used to make sure we getting unique friends
			randomFriends = new LinkedList<String>();
			f = 0;
			while(f < 10){
				randomPersonId = (int) (Math.random()*100);
				if(!randomFriends.contains(randomPersonId+"") && randomPersonId != i && randomPersonId > 0){
					randomFriends.add(randomPersonId+"");
					f++;
				}
			}
			
			//add friends to person i

			for(int x = 0 ; x < 10; x++){
				friendsOf[i][x] =		Integer.parseInt(randomFriends.get(x));
				//System.out.println(i+" - "+x+" = "+friendsOf[i][x]);
				//System.out.println("\n------------------------");

			}
			
		}
		return friendsOf;
	}
	
}
