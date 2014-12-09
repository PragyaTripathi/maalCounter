package com.maalapp;



import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
//import android.view.View;

import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.support.v4.app.NavUtils;


/**
 * AddPointsActivity lets user to add points for a hand in the game.
 * This activity's content is activity_add_points.xml.
 * 
 */
public class AddPointsActivity extends Activity {
	/**
	 * Determines whether to always show the simplified settings UI, where
	 * settings are presented in a single list. When false, settings are shown
	 * as a master/detail two-pane view on tablets. When true, a single pane is
	 * shown on tablets.
	 */
	String[] playerNames = new String[6]; //The names of the player
	View[] linearLayout = new View[6]; // The linearLayouts will be populated based on the number of players
	TextView[] player = new TextView[6];//The TextView placeholder in the UI to show different players
	
	
	HashMap<String,Double> maalMap;// To get all the Maal values
	int count;// the number of players
	
	//Different UI components for maals
	RadioButton poplu1;
	RadioButton poplu2;
	
	RadioButton tiplu1;
	RadioButton tiplu2;
	RadioButton tiplu3;
	
	RadioButton jhiplu1;
	RadioButton jhiplu2;
	RadioButton jhiplu3;
	
	RadioButton marriage1;
	RadioButton marriage2;
	
	RadioButton ordinaryTunnella;
	RadioButton tipluJhipluTunnella;
	RadioButton jokerTunnella;
	
	CheckBox notSeenJoker;
	CheckBox completedGame;
	CheckBox seenJoker;
	
	
	DatabaseMaalHandler dbHandler; // Database that stores all the MaalNames and corresponding points set by the user
	
	/*
	 * Instead of calling the constructor, OnCreate is called to setup the state of this activity.
	 * @param Bundle savedInstanceState This parameter is trying to pass any saved state for this activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.activity_add_points);
		Intent intent = getIntent();
		playerNames = intent.getStringArrayExtra("playerNames");
		Log.d("AddPointsplayerNames",String.valueOf(playerNames.length));
		count=playerNames.length;
		dbHandler= new DatabaseMaalHandler(this.getApplicationContext());
		maalMap=dbHandler.getAllMaals();
		loadUI();
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			// TODO: If Settings has multiple levels, Up should navigate up
			// that hierarchy.
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}

	


	/**
	 * A preference value change listener that updates the preference's summary
	 * to reflect its new value.
	 */
	private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
		@Override
		public boolean onPreferenceChange(Preference preference, Object value) {
			String stringValue = value.toString();

			if (preference instanceof ListPreference) {
				// For list preferences, look up the correct display value in
				// the preference's 'entries' list.
				ListPreference listPreference = (ListPreference) preference;
				int index = listPreference.findIndexOfValue(stringValue);

				// Set the summary to reflect the new value.
				preference
						.setSummary(index >= 0 ? listPreference.getEntries()[index]
								: null);

			} else if (preference instanceof RingtonePreference) {
				// For ringtone preferences, look up the correct display value
				// using RingtoneManager.
				if (TextUtils.isEmpty(stringValue)) {
					// Empty values correspond to 'silent' (no ringtone).
					preference.setSummary(R.string.pref_ringtone_silent);

				} else {
					Ringtone ringtone = RingtoneManager.getRingtone(
							preference.getContext(), Uri.parse(stringValue));

					if (ringtone == null) {
						// Clear the summary if there was a lookup error.
						preference.setSummary(null);
					} else {
						// Set the summary to reflect the new ringtone display
						// name.
						String name = ringtone
								.getTitle(preference.getContext());
						preference.setSummary(name);
					}
				}

			} else {
				// For all other preferences, set the summary to the value's
				// simple string representation.
				preference.setSummary(stringValue);
			}
			return true;
		}
	};

	/**
	 * Binds a preference's summary to its value. More specifically, when the
	 * preference's value is changed, its summary (line of text below the
	 * preference title) is updated to reflect the value. The summary is also
	 * immediately updated upon calling this method. The exact display format is
	 * dependent on the type of preference.
	 * 
	 * @see #sBindPreferenceSummaryToValueListener
	 */
	private static void bindPreferenceSummaryToValue(Preference preference) {
		// Set the listener to watch for value changes.
		preference
				.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

		// Trigger the listener immediately with the preference's
		// current value.
		sBindPreferenceSummaryToValueListener.onPreferenceChange(
				preference,
				PreferenceManager.getDefaultSharedPreferences(
						preference.getContext()).getString(preference.getKey(),
						""));
	}

	/**
	 * This fragment shows general preferences only. It is used when the
	 * activity is showing a two-pane settings UI.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class GeneralPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_general);

			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
			// to their values. When their values change, their summaries are
			// updated to reflect the new value, per the Android Design
			// guidelines.
			bindPreferenceSummaryToValue(findPreference("example_text"));
			bindPreferenceSummaryToValue(findPreference("example_list"));
		}
	}

	/**
	 * This fragment shows notification preferences only. It is used when the
	 * activity is showing a two-pane settings UI.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class NotificationPreferenceFragment extends
			PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_notification);

			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
			// to their values. When their values change, their summaries are
			// updated to reflect the new value, per the Android Design
			// guidelines.
			bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
		}
	}

	/**
	 * This fragment shows data and sync preferences only. It is used when the
	 * activity is showing a two-pane settings UI.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class DataSyncPreferenceFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.pref_data_sync);

			// Bind the summaries of EditText/List/Dialog/Ringtone preferences
			// to their values. When their values change, their summaries are
			// updated to reflect the new value, per the Android Design
			// guidelines.
			bindPreferenceSummaryToValue(findPreference("sync_frequency"));
		}
	}
	/**
	 * This method loads UI based on the number of players in the game.
	 */
	private void loadUI(){
		linearLayout[0] = (LinearLayout) findViewById(R.id.linearLayout1);
		linearLayout[1] = (LinearLayout) findViewById(R.id.linearLayout2);
		linearLayout[2] = (LinearLayout) findViewById(R.id.linearLayout3);
		linearLayout[3] = (LinearLayout) findViewById(R.id.linearLayout4);
		linearLayout[4]= (LinearLayout) findViewById(R.id.linearLayout5);
		linearLayout[5]= (LinearLayout) findViewById(R.id.linearLayout6);
		
		player[0] = (TextView) findViewById(R.id.textView_player1);
		player[1] = (TextView) findViewById(R.id.textView_player2);
		Log.d("nachaleko textview",player[1].toString());
		player[2] = (TextView) findViewById(R.id.textView_player3);
		player[3] = (TextView) findViewById(R.id.textView_player4);
		player[4]= (TextView) findViewById(R.id.textView_player5);
		player[5]= (TextView) findViewById(R.id.textView_player6);
		
		for (int i=0;i<count ;i++){
			linearLayout[i].setVisibility(View.VISIBLE);
		}
		for (int i=0;i<count ;i++){
			Log.d("herewego","begin");
			String name = String.valueOf(playerNames[i]);
			Log.d("name",name);
			Log.d("textview",player[i].toString());
			player[i].setText(name);
		}
		
	}
	/**
	 * This method checks the state of each player and sets a point value so that the MaalHandler class would know whether the player has seen the joker or not.
	 * @return int gamePoints 
	 */
	private int checkGamePoints(){
		int gamePoints=-2;
		if(notSeenJoker.isChecked()){
			gamePoints = -1; // the player has not see the joker
		}else if(seenJoker.isChecked()){
			gamePoints = 0; // the player has seen the joker but not completed the game
		}else if (completedGame.isChecked()){
			gamePoints =1; // the player completed the game
		}
		return gamePoints; 
	}
	/**
	 * This method tries to find the appropriate gameScore for a player based on the checkboxes clicked by the user.
	 * @param playerNumber the player for whom we are trying to calculate the gamescore
	 * @return int the game points
	 */
	private int getGamePoints(int playerNumber){
		
		switch(playerNumber){
		case 0:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker1);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker1);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame1);
			return checkGamePoints();
		case 1:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker2);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker2);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame2);
			return checkGamePoints();
		case 2:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker3);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker3);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame3);
			return checkGamePoints();
		case 3:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker4);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker4);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame4);
			return checkGamePoints();
		case 4:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker5);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker5);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame5);
			return checkGamePoints();
		case 5:
			seenJoker= (CheckBox) findViewById(R.id.cbSeenJoker6);
			notSeenJoker = (CheckBox) findViewById(R.id.cbNotSeenJoker6);
			completedGame = (CheckBox) findViewById(R.id.cbCompletedGame6);
			return checkGamePoints();
		
		}
		return checkGamePoints();
	}
	/**
	 * This method counts the total number of points calculated based on the buttons checked by the user.
	 * @param playerNumber a particular palyer form whom we are trying to find total maal points
	 * @return maal points for a player
	 */
	private double countMaalPoints(int playerNumber){
		switch(playerNumber){
		case 0:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu1_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu1_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu1_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu1_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu1_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu1_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu1_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu1_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage1_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage1_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella1_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella1_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella1_joker);
			return calculateMaalValues();
		case 1:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu2_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu2_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu2_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu2_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu2_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu2_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu2_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu2_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage2_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage2_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella2_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella2_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella2_joker);
			return calculateMaalValues();
		case 2:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu3_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu3_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu3_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu3_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu3_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu3_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu3_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu3_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage3_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage3_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella3_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella3_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella3_joker);
			return calculateMaalValues();
		case 3:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu4_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu4_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu4_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu4_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu4_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu4_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu4_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu4_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage4_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage4_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella4_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella4_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella4_joker);
			return calculateMaalValues();
		case 4:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu5_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu5_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu5_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu5_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu5_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu5_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu5_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu5_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage5_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage5_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella5_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella5_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella5_joker);
			return calculateMaalValues();
		case 5:
			poplu1 = (RadioButton) findViewById(R.id.rbPoplu6_1);
			poplu2 = (RadioButton) findViewById(R.id.rbPoplu6_2);
			tiplu1 = (RadioButton) findViewById(R.id.rbTiplu6_1);
			tiplu2 = (RadioButton) findViewById(R.id.rbTiplu6_2);
			tiplu3= (RadioButton) findViewById(R.id.rbTiplu6_3);
			jhiplu1= (RadioButton) findViewById(R.id.rbJhiplu6_1);
			jhiplu2 = (RadioButton) findViewById(R.id.rbJhiplu6_2);
			jhiplu3= (RadioButton) findViewById(R.id.rbJhiplu6_3);
			marriage1 = (RadioButton) findViewById(R.id.rbMarriage6_1);
			marriage2 = (RadioButton) findViewById(R.id.rbMarriage6_2);
			ordinaryTunnella= (RadioButton) findViewById(R.id.rbTunnella6_ord);
			tipluJhipluTunnella= (RadioButton) findViewById(R.id.rbTunnella6_tj);
			jokerTunnella = (RadioButton) findViewById(R.id.rbTunnella6_joker);
			return calculateMaalValues();			
		}
		return calculateMaalValues();
	}
	/**
	 * Filters the buttons checked and deciphers the points of a particular maal from the database 
	 * @return double maalPoints
	 */
	private double calculateMaalValues(){
		double maalPoints=0;
		if (poplu1.isChecked()){
			
			maalPoints = maalPoints + (double) maalMap.get("singleTiplu");
		}
		if (poplu2.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("doubleTiplu");
		}
		if (tiplu1.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("singlePoplu");
		}
		if (tiplu2.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("doublePoplu");
		}
		if (tiplu3.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("triplePoplu");
		}
		if (jhiplu1.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("singleJhiplu");
		}
		if (jhiplu2.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("doubleJhiplu");
		}
		if (jhiplu3.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("tripleJhiplu");
		}
		if (marriage1.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("singleMarriage");
		}
		if (marriage2.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("doubleMarriage");
		}
		if (ordinaryTunnella.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("ordinaryCardTunnella");
		}
		if (tipluJhipluTunnella.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("popluJhipluTunnella");
		}
		if (jokerTunnella.isChecked()){
			maalPoints = maalPoints + (double) maalMap.get("ordinaryJokerTunnella");
		}
		return maalPoints;
	}
	/**
	 * This method is called after Save button is clicked on UI.
	 * It calls the MainHandler class, adds players to it and their respective maal values.It calculates the score for this hand and puts it on a database.
	 * Then, it calls the Scoreboard activity class
	 * @param v
	 */
	public void btnSave(View v){
		for (int i=0;i<count ;i++){
			linearLayout[i].setVisibility(2);
	    }
		MainHandler handler=new MainHandler();
		handler.addPlayers(playerNames);
		int[] playerGamePoints= new int[6];
		double[] playerMaalPoints= new double[6];
		for (int i=0; i<count; i++){
			playerGamePoints[i]=getGamePoints(i);
			Log.d("playerNum",String.valueOf(i));
			Log.d("gamePoints",String.valueOf(getGamePoints(i)));
			playerMaalPoints[i]=countMaalPoints(i);
			Log.d("maalPOints",String.valueOf(countMaalPoints(i)));
		}
		double[] pointsForThisHand= new double[6];
		pointsForThisHand=handler.newHand(playerMaalPoints,playerGamePoints);
		DatabasePointsHandler pointsHandler = new DatabasePointsHandler(this.getApplicationContext());
		pointsHandler.updateDatabase(MainHandler.gameId, pointsForThisHand);
		Intent intent = new Intent(v.getContext(), ScoreboardActivity.class);
		intent.putExtra("playerNames", playerNames);
	    startActivity(intent);
	}
	
}
