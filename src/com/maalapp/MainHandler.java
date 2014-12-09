/**
 * 
 */
package com.maalapp;
import java.util.HashMap;

import android.util.Log;

/**
 * @author Admin
 * This class makes interaction between the UI and java classes possible.
 * It creates players.
 * It can add a new hand and calculate points based on player input passed from Activity classes.
 *
 */
public class MainHandler {
	
	public String currency; // currency that the user wants to calculate points in
	private HashMap<Player,Double> maals;// the maal points that each player has for a hand
	private Player[] players; // the players in the game
	static int gameId = -1; // track of how many hands has been played
	
	public MainHandler(){
		//initialize
		players = new Player[6];
		currency="";
		maals = new HashMap<Player, Double>();
	}
	/*
	 * This method creates Player objects based on the names provided.
	 * @param String[] names The names of the players
	 */
	public void addPlayers(String[] names){
		for (int i=0;i<names.length;i++){
			Player player = new Player(names[i]);
			players[i]=player;
		}
		
	}
	/*
	 * Adds a new hand and calculates the total points for each player for this hand
	 * @param double[] pointsForPlayer The maal points for each player
	 * @param int[] gameScoreForPlayer The game Score points for each player
	 */
	public double[] newHand(double[] pointsForPlayer, int[] gameScoreForPlayer){
		gameId= gameId + 1;
		//read the game score first
		for (int i=0; i<gameScoreForPlayer.length;i++){
			setGameScore(players[i],gameScoreForPlayer[i]);
			Log.d("Handlerplayer1Points",String.valueOf(gameScoreForPlayer[0]));
			
		}
		// send the total maal for each player in the pot
		for (int i=0;i<pointsForPlayer.length;i++){
			Double d = Double.valueOf(pointsForPlayer[i]);
			maals.put(players[i], d);
			
			Log.d("player1Points",String.valueOf(maals.get(players[i])));
			
		}
		//create a new hand
		Hand hand = new Hand(gameId,maals);
		hand.calculatePoints(); // calculates the total points and puts them in players's state
		double[] pointsForThisHand = new double[6];
		for (int i=0;i<players.length;i++){
			pointsForThisHand[i]=players[i].getPoints(gameId);
		}
		return pointsForThisHand;
	}
	/*
	 * Set the player's state based on gameScore provided
	 * @param Player player the player whose state is set
	 * @param int gameScore the gameScore for that player
	 */
	private void setGameScore(Player player, int gameScore){
		switch(gameScore){
		case -1:
			player.notSeenJoker();
			return;
		case 0:
			player.seenJoker();
			return;
		case 1:
			player.completedGame();
			return;
		}
	}
	
	//TODO: read gamescores and add them to player's state
	//
	/*
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	*/
	

}
