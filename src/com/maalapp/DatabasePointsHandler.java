package com.maalapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This Handler class creates and manages table of player points which should be created empty when a user starts a new scoreboard.
 * @author Pragya
 *
 */

public class DatabasePointsHandler extends SQLiteOpenHelper {
	// All Static variables
		// Database Version
		private static final int DATABASE_VERSION = 1;

		// Database Name
		private static final String DATABASE_NAME = "points_database";

		
		// Points table name
			private static final String TABLE_POINTS = "playerPoints";

			// Points Table Columns names
			private static final String KEY_GAME_ID = "gameId";
			private static final String KEY_PLAYER_1 = "player1";
			private static final String KEY_PLAYER_2 = "player2";
			private static final String KEY_PLAYER_3 = "player3";
			private static final String KEY_PLAYER_4 = "player4";
			private static final String KEY_PLAYER_5 = "player5";
			private static final String KEY_PLAYER_6 = "player6";
		
			int winner=0;// The array index of final winner

		public DatabasePointsHandler(Context context) {

			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		/*
		 * Instead of calling the constructor, OnCreate is called to setup the state of this object.
		 * @param Bundle savedInstanceState This parameter is trying to pass any saved state for this object.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			String CREATE_PLAYER_TABLE = "CREATE TABLE IF NOT EXISTS "
					+ TABLE_POINTS + "(" + KEY_GAME_ID
					+ " INTEGER PRIMARY KEY, " + KEY_PLAYER_1 + " DOUBLE, "
					+ KEY_PLAYER_2 + " DOUBLE, " 
					+ KEY_PLAYER_3 + " DOUBLE, "
					+ KEY_PLAYER_4 + " DOUBLE, "
					+ KEY_PLAYER_5 + " DOUBLE, "
					+ KEY_PLAYER_6 + " DOUBLE "+")";
			db.execSQL(CREATE_PLAYER_TABLE);
			String selectQuery = "SELECT " + KEY_GAME_ID + " FROM "
					+ TABLE_POINTS;
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.getCount() < 5) {
				addGameID();
			}
			cursor.close();
			
		}
//load gameID and update
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);

			// Create tables again
			onCreate(db);

		}

		/*
		 * Creates game ID primary keys in the table points before hand so that the points can be loaded to database easily
		 */
		public void addGameID() {
			SQLiteDatabase db = this.getWritableDatabase();
			
			ContentValues values1 = new ContentValues();// only content values can be inserted to SQLiteDatabase
			values1.put(KEY_GAME_ID, 1);
			ContentValues values2 = new ContentValues();
			values2.put(KEY_GAME_ID, 2);
			ContentValues values3 = new ContentValues();
			values3.put(KEY_GAME_ID, 3);
			ContentValues values4 = new ContentValues();
			values4.put(KEY_GAME_ID, 4);
			ContentValues values5 = new ContentValues();
			values5.put(KEY_GAME_ID, 5);
			
	
			db.insert(TABLE_POINTS, null, values1);
			db.insert(TABLE_POINTS, null, values2);
			db.insert(TABLE_POINTS, null, values3);
			db.insert(TABLE_POINTS, null, values4);
			db.insert(TABLE_POINTS, null, values5);
			db.close();
		}
		/*
		 * Adds points of different players passed for a specific gameID into the Database
		 * @param int gameId game Id of a specific hand
		 * @param double[] points the points for all the players
		 */
		public void updateDatabase(int gameId, double[] points) {
			int id =gameId+1;
			String selectQuery;
			SQLiteDatabase db = this.getWritableDatabase();
			
				selectQuery = "UPDATE " + TABLE_POINTS + " SET " + KEY_PLAYER_1
						+ " = " + points[0] +", "+KEY_PLAYER_2+ " = " + points[1] +", "
						+KEY_PLAYER_3+ " = " + points[2] +", "
						+KEY_PLAYER_4+ " = " + points[3] +", "
						+KEY_PLAYER_5+ " = " + points[4] +", "
						+KEY_PLAYER_6+ " = " + points[5] + " WHERE "+KEY_GAME_ID + " = " + id;
				db.execSQL(selectQuery);
				Log.d("DB gameID",String.valueOf(gameId+1));
				Log.d("player1Points",String.valueOf(points[0]));
				Log.d("player2Points",String.valueOf(points[1]));
				Log.d("player3Points",String.valueOf(points[2]));
				Log.d("player4Points",String.valueOf(points[3]));
				Log.d("player5Points",String.valueOf(points[4]));
				Log.d("player6Points",String.valueOf(points[5]));
			
			db.close();
		}
		/*
		 * Gets points of different players passed for a specific gameID from the Database
		 * @param int gameId game Id of a specific hand
		 * @return double[] points the points for all the players for that gameID
		 */
		
		public double[] getPlayerPoints(int gameID) {
			gameID= gameID+1;
			
			String selectQuery = "SELECT "+ KEY_PLAYER_1+", "
					+ KEY_PLAYER_2+", "
					+ KEY_PLAYER_3+", "
					+ KEY_PLAYER_4+", "
					+ KEY_PLAYER_5+", "
					+ KEY_PLAYER_6+" FROM "
					+ TABLE_POINTS + " WHERE " + KEY_GAME_ID
					+ " = "+gameID;
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);
			cursor.moveToFirst();
			double[] arr = new double[6];
			
			    arr[0]  = cursor.getDouble(0);
			    arr[1]  = cursor.getDouble(1);
			    arr[2]  = cursor.getDouble(2);
			    arr[3]  = cursor.getDouble(3);
			    arr[4]  = cursor.getDouble(4);
			    arr[5]  = cursor.getDouble(5);
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(0)));
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(1)));
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(2)));
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(3)));
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(4)));
			    Log.d("ExtractingFRomDB",String.valueOf(cursor.getDouble(5)));
			  
			db.close();
			cursor.close();
			return arr;
		}
		/*
		 * Totals up all the points for each player Stored in the database and returns an array of total points
		 * @param double[] the final total points for all the players
		 */
		
		public double[] findWinner(){
			int count =MainHandler.gameId;
			double[] player= {0,0,0,0,0,0};
			while (count>-1){
				double[] points = getPlayerPoints(count);
				player[0] = player[0] + points[0];
				player[1] = player[1] + points[1];
				player[2] = player[2] + points[2];
				player[3] = player[3] + points[3];
				player[4] = player[4] + points[4];
				player[5] = player[5] + points[5];
				count = count -1;
			}
			int numOfPlayers =5 ;
			
			//find the winner player's index
			while (numOfPlayers>-1){
				if (player[numOfPlayers]>player[winner]){
					winner = numOfPlayers;
				}
				numOfPlayers = numOfPlayers-1;
			}
			deleteTables(); // to make sure the same scores are not used for next play
			
			return player;
		}
		/*
		 * Returns the index of winner
		 * @return int the index of winner
		 */
		
		public int getWinner(){
			return winner;
		}
		/*
		 * Sets the index of winner
		 * @param int the index of winner
		 */
		public void setWinner(int winner){
			this.winner=winner;
		}
		/*
		 * This method deletes point table to make sure the same scores are not used for next play
		 */
		public void deleteTables(){
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);

			// Create tables again
			onCreate(db);

		}


}
