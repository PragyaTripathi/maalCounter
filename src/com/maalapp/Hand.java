/**
 * 
 */
package com.maalapp;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
/**
 * @author Pragya
 * This class calculates all players' points including the winner's.
 *
 */
public class Hand {
	private int gameId;
	  HashMap<Player,Double> pointsMap;
	  Set<Player> players;
	  int totalPlayers;
	  
	  /**
	   * Initialize the gameId, maalMap, a set of players and the total number of players
	   * @param int gameId the current gameId passed by the Game class
	   * @param HashMap<Player,Double> maalMap a map for all the maals earned by players in this game
	   * */
	  public Hand(int gameId, HashMap<Player,Double> pointsMap){
	    this.gameId=gameId;
	    this.pointsMap=pointsMap;
	    players = pointsMap.keySet();
	    totalPlayers=players.size();
	  }
	  /**
	   * Calculates the first phase: the total maal in the pot and puts it inside each Player class
	   */
	   private double totalMaal(){
	     double totalMaal = 0;
	     Iterator<Player> player=players.iterator();
	     while(player.hasNext()){
	       Player newPlayer = (Player)player.next();
	       double maalForThisPlayer=(double) pointsMap.get(newPlayer);
	       totalMaal=totalMaal+maalForThisPlayer;
	       newPlayer.setPoints(gameId,maalForThisPlayer); // set total maal in Player class
	     }
	     return totalMaal;                             
	   }
	   /**
	    * Calculates all total points for each player including the winner's
	    */
	  public void calculatePoints(){
	    double totalPoints = totalMaal(); // total maal in the pot
	    double totalWinnerPoints=0;
	    Iterator<Player> player=players.iterator();
	     while(player.hasNext()){
	       Player newPlayer = (Player) player.next();
	       // if a player has gamescore of 0 means the winner has completed the game
	       if (newPlayer.getGameScore()!=0){
	         double newScore=totalPlayers*newPlayer.getPoints(gameId)-totalPoints-newPlayer.getGameScore(); // using the formula
	         totalWinnerPoints +=newScore;
	         newPlayer.setPoints(gameId,newScore); // set the new total points
	       }
	     }
	     keepWinnerPoints(totalWinnerPoints); 
	  }
	  /**
	   * Sets the winner points which is the sum of all the other players' scores
	   * @param int totalWinnerPoints the sum of all other players's points
	   * */
	  private void keepWinnerPoints(double totalWinnerPoints){
	    Iterator<Player> player=players.iterator();
	     while(player.hasNext()){
	       Player newPlayer = (Player) player.next();
	       if (newPlayer.getGameScore()==0){
	         newPlayer.setPoints(gameId,totalWinnerPoints*-1);
	       }     }
	  }

}
