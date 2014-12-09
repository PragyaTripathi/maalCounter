/**
 * 
 */
package com.maalapp;

/**
 * @author Admin
 *This class creates a player object with a name. It also keeps track of a user's gameScore and the points scored in different hands.
 */
public class Player {
	private String name; //the name of the player
	  private double[] gameId; //the id of the game
	  private int sizeOfArray = 50; // Maximum number of games
	  private int gameScore=0;//10 means didn't see joker nor complete game; 3 means saw joker but didn't complete game; 0 means completed game
	  
	 public Player(String name){
	  this.name=name;
	  gameId = new double[sizeOfArray]; // initialize
	 }
	/** Return name of the player
	  * @return String name of the player
	  * */
	 public String getName() {
	  return name;
	 }
	/** Return gameScore set for the player
	  * @return int gameScore set for the player
	  * */
	 public int getGameScore() {
	  return gameScore;
	 }
	 /** Set gameScore to 0 if the player completed the game first
	  * */
	 public void completedGame(){
	   gameScore=0;
	 }
	 /** Set gameScore to 3 if the player didn't complete the game first but has seen the joker
	  * */
	 public void seenJoker(){
	   gameScore=3;
	 }
	 /** Set gameScore to 10 if the player didn't complete the game first and hasn't seen the joker
	  * */
	 public void notSeenJoker(){
	   gameScore=10;
	 }
	 /** Return the points for a given id
	   * @param int id of the game
	  * @return int points of the player for given id
	  * */
	 public double getPoints(int id) {
	  return gameId[id];
	 }
	/** Set points for a given id
	   * @param int id of the game
	  * @param int points of the player for given id
	  * */
	 public void setPoints(int id, double points) {
	  if (id>sizeOfArray-1){ //if id is greater than the size of array then we need to double the size of array
	   int newSize = sizeOfArray * 2;
	   double[] newArray = new double[newSize];
	   for (int i=0;i<sizeOfArray;i++){
	     newArray[i]=gameId[i];
	   }
	   sizeOfArray=newSize;
	   gameId=newArray;
	  }
	  gameId[id] = points;
	 }
	 


}
