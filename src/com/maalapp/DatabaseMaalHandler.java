package com.maalapp;


import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * This Handler class creates and manages table of maal values which should never be empty and update itself when user edits the maal points.
 * @author Pragya
 *
 */
public class DatabaseMaalHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "maalapp_database";

	// Points table name
	private static final String TABLE_POINTS = "points";

	// Points Table Columns names
	private static final String KEY_POINTS_NAME_ID = "nameId";
	private static final String KEY_POINTS_VALUE = "value";
	
	// Points table name
		private static final String TABLE_PLAYER = "player";

		// Points Table Columns names
		private static final String KEY_PLAYER_NAME_ID = "nameId";
		private static final String KEY_PLAYER_VALUE = "value";
	

	public DatabaseMaalHandler(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_POINTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_POINTS + "(" + KEY_POINTS_NAME_ID
				+ " TEXT PRIMARY KEY, " + KEY_POINTS_VALUE + " DOUBLE " + ")";
		db.execSQL(CREATE_POINTS_TABLE);
		
		String CREATE_PLAYER_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ TABLE_PLAYER + "(" + KEY_PLAYER_NAME_ID
				+ " TEXT PRIMARY KEY, " + KEY_PLAYER_VALUE + " INTEGER " + ")";
		db.execSQL(CREATE_PLAYER_TABLE);
		

		String selectQuery = "SELECT " + KEY_POINTS_NAME_ID + " FROM "
				+ TABLE_POINTS;
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows to see if it is empty
		// if it empty, then maal points are added to list
		if (cursor.getCount() < 14) {
			ContentValues values1 = new ContentValues();
			values1.put(KEY_POINTS_NAME_ID, "singleTiplu");
			values1.put(KEY_POINTS_VALUE, 3);
			ContentValues values2 = new ContentValues();
			values2.put(KEY_POINTS_NAME_ID, "singleJhiplu");
			values2.put(KEY_POINTS_VALUE, 2);
			ContentValues values3 = new ContentValues();
			values3.put(KEY_POINTS_NAME_ID, "singlePoplu");
			values3.put(KEY_POINTS_VALUE, 2);
			ContentValues values4 = new ContentValues();
			values4.put(KEY_POINTS_NAME_ID, "singleMarriage");
			values4.put(KEY_POINTS_VALUE, 10);
			ContentValues values5 = new ContentValues();
			values5.put(KEY_POINTS_NAME_ID, "ordinaryCardTunnella");
			values5.put(KEY_POINTS_VALUE, 5);
			ContentValues values6 = new ContentValues();
			values6.put(KEY_POINTS_NAME_ID, "ordinaryJokerTunnella");
			values6.put(KEY_POINTS_VALUE, 10);
			ContentValues values7 = new ContentValues();
			values7.put(KEY_POINTS_NAME_ID, "popluJhipluTunnella");
			values7.put(KEY_POINTS_VALUE, 20);
			ContentValues values8 = new ContentValues();
			values8.put(KEY_POINTS_NAME_ID, "doubleTiplu");
			values8.put(KEY_POINTS_VALUE, 7);
			ContentValues values9 = new ContentValues();
			values9.put(KEY_POINTS_NAME_ID, "doubleJhiplu");
			values9.put(KEY_POINTS_VALUE, 5);
			ContentValues values10 = new ContentValues();
			values10.put(KEY_POINTS_NAME_ID, "doublePoplu");
			values10.put(KEY_POINTS_VALUE, 5);
			ContentValues values11 = new ContentValues();
			values11.put(KEY_POINTS_NAME_ID, "doubleMarriage");
			values11.put(KEY_POINTS_VALUE, 30);
			ContentValues values12 = new ContentValues();
			values12.put(KEY_POINTS_NAME_ID, "tripleJhiplu");
			values12.put(KEY_POINTS_VALUE, 10);
			ContentValues values13 = new ContentValues();
			values13.put(KEY_POINTS_NAME_ID, "triplePoplu");
			values13.put(KEY_POINTS_VALUE, 10);
			ContentValues values14 = new ContentValues();
			values14.put(KEY_POINTS_NAME_ID, "multiplier");
			values14.put(KEY_POINTS_VALUE, 0.01);

			// Inserting Row
			db.insert(TABLE_POINTS, null, values1);
			db.insert(TABLE_POINTS, null, values2);
			db.insert(TABLE_POINTS, null, values3);
			db.insert(TABLE_POINTS, null, values4);
			db.insert(TABLE_POINTS, null, values5);
			db.insert(TABLE_POINTS, null, values6);
			db.insert(TABLE_POINTS, null, values7);
			db.insert(TABLE_POINTS, null, values8);
			db.insert(TABLE_POINTS, null, values9);
			db.insert(TABLE_POINTS, null, values10);
			db.insert(TABLE_POINTS, null, values11);
			db.insert(TABLE_POINTS, null, values12);
			db.insert(TABLE_POINTS, null, values13);
			db.insert(TABLE_POINTS, null, values14);
			// db.close(); // Closing database connection
		}
		cursor.close();
	}
/*
 * (non-Javadoc)
 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POINTS);

		// Create tables again
		onCreate(db);

	}
/*
 * Returns a map with all the maals stored in the table
 */
	public HashMap<String, Double> getAllMaals() {
		HashMap<String, Double> maalList = new HashMap<String, Double>();
		// Select All Query

		String selectQuery = "SELECT  * FROM " + TABLE_POINTS + " WHERE NOT "
				+ KEY_POINTS_NAME_ID + "= 'multiplier'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				int value = (int) cursor.getDouble(1);
				// Adding maal to list
				maalList.put(cursor.getString(0), Double.valueOf(value));
			} while (cursor.moveToNext());
		}
		db.close();
		cursor.close();
		// return maal list
		return maalList;
	}
	/*
	 * Adds user update maal values into the Database
	 * @param HashMap<String, Double> maals the map of maals to be updated
	 */
	public void updateDatabase(HashMap<String, Double> maals) {
		String selectQuery;
		SQLiteDatabase db = this.getWritableDatabase();
		Map<String, Double> map = maals;
		for (Map.Entry<String, Double> entry : map.entrySet()) {
			selectQuery = "UPDATE " + TABLE_POINTS + " SET " + KEY_POINTS_VALUE
					+ " = " + entry.getValue() + " WHERE "
					+ KEY_POINTS_NAME_ID + " = '" + entry.getKey()+"'";
			db.execSQL(selectQuery);
		}
		db.close();
	}
	/*public void addPlayers(ArrayList<Player> players){
		SQLiteDatabase db = this.getWritableDatabase();

		for (Player player : players) {
			ContentValues values = new ContentValues();
			values.put(KEY_PLAYER_NAME_ID, player.getName());
			values.put(KEY_PLAYER_VALUE, 0);
			db.insert(TABLE_PLAYER,null,values);
		}
		db.close();
	}*/
	/*
	 * Returns the multiplier
	 * @return double the value of multiplier 
	 */
	public double getMultiplier() {
		String selectQuery = "SELECT " + KEY_POINTS_VALUE + " FROM "
				+ TABLE_POINTS + " WHERE " + KEY_POINTS_NAME_ID
				+ " = 'multiplier'";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		double d= cursor.getDouble(0);
		db.close();
		cursor.close();
		return d;
	}
	/*public ArrayList<String> getPlayerNames() {
		
		String selectQuery = "SELECT " + KEY_PLAYER_NAME_ID + " FROM "
				+ TABLE_PLAYER;
		SQLiteDatabase db = this.getWritableDatabase();
		ArrayList<String> s = new ArrayList<String>();
		Cursor cursor = db.rawQuery(selectQuery, null);
		cursor.moveToFirst();
		for (int i =0; i<cursor.getCount(); i++){
			s.add(cursor.getString(i));
		}
		
		db.close();
		cursor.close();
		return s;
	}*/

}
