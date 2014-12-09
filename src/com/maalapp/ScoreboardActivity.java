package com.maalapp;



import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * ScoreboardActivity has a table like structure which shows points of different users for different hands.
 * This activity's content is activity_scoreboard.xml.
 * 
 */
public class ScoreboardActivity extends Activity {
	
	
// Set the UI components
	TextView[] player = new TextView[6];
	TextView[] set1 = new TextView[6];
	TextView[] set2= new TextView[6];
	TextView[] set3= new TextView[6];
	TextView[] set4= new TextView[6];
	TextView[] set5= new TextView[6];
	
	String[] playerNames; //the player names
	DatabasePointsHandler pointsHandler; //Database to keep track of points
	static int setNum = -1; // Keeps track of the set version to use
	/*
	 * Instead of calling the constructor. OnCreate is called to setup the state of this activity.
	 * @param Bundle savedInstanceState This parameter is trying to pass any saved state for this activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.activity_scoreboard);
		
		player[0] = (TextView) findViewById(R.id.textView_player1);
		player[1] = (TextView) findViewById(R.id.textView_player2);
		player[2] = (TextView) findViewById(R.id.textView_player3);
		player[3] = (TextView) findViewById(R.id.textView_player4);
		player[4]= (TextView) findViewById(R.id.textView_player5);
		player[5]= (TextView) findViewById(R.id.textView_player6);
		
		loadTitle();
		
		set1[0] = (TextView) findViewById(R.id.TextView_player1_7);
		set1[1] = (TextView) findViewById(R.id.TextView_player2_8);
		set1[2] = (TextView) findViewById(R.id.TextView_player3_9);
		set1[3] = (TextView) findViewById(R.id.TextView_player4_10);
		set1[4] = (TextView) findViewById(R.id.TextView_player5_11);
		set1[5] = (TextView) findViewById(R.id.TextView_player6_12);
		
		set2[0] = (TextView) findViewById(R.id.TextView_player1_13);
		set2[1] = (TextView) findViewById(R.id.TextView_player2_14);
		set2[2] = (TextView) findViewById(R.id.TextView_player3_15);
		set2[3] = (TextView) findViewById(R.id.TextView_player4_16);
		set2[4] = (TextView) findViewById(R.id.TextView_player5_17);
		set2[5] = (TextView) findViewById(R.id.TextView_player6_18);
		
		set3[0] = (TextView) findViewById(R.id.TextView_player1_19);
		set3[1] = (TextView) findViewById(R.id.TextView_player2_20);
		set3[2] = (TextView) findViewById(R.id.TextView_player3_21);
		set3[3] = (TextView) findViewById(R.id.TextView_player4_22);
		set3[4] = (TextView) findViewById(R.id.TextView_player5_23);
		set3[5] = (TextView) findViewById(R.id.TextView_player6_24);
		
		set4[0] = (TextView) findViewById(R.id.TextView_player1_25);
		set4[1] = (TextView) findViewById(R.id.TextView_player2_26);
		set4[2] = (TextView) findViewById(R.id.TextView_player3_27);
		set4[3] = (TextView) findViewById(R.id.TextView_player4_28);
		set4[4] = (TextView) findViewById(R.id.TextView_player5_29);
		set4[5] = (TextView) findViewById(R.id.TextView_player6_30);
		
		set5[0] = (TextView) findViewById(R.id.TextView_player1_31);
		set5[1] = (TextView) findViewById(R.id.TextView_player2_32);
		set5[2] = (TextView) findViewById(R.id.TextView_player3_33);
		set5[3] = (TextView) findViewById(R.id.TextView_player4_34);
		set5[4] = (TextView) findViewById(R.id.TextView_player5_35);
		set5[5] = (TextView) findViewById(R.id.TextView_player6_36);
		
		pointsHandler = new DatabasePointsHandler(this.getApplicationContext());
		Log.d("setNum",String.valueOf(setNum));
		Log.d("MainHandler.gameId",String.valueOf(MainHandler.gameId));
		int count = MainHandler.gameId;
		while (count>-1){
			fillTheTable(count);
			count = count -1;
		}
		
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
/*
 * (non-Javadoc)
 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
 */
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
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

	}
	/*
	 * This method selects the set of UI components based on the gameId provided
	 * @param int gameID the game ID 
	 */
	private void fillTheTable(int gameId){
		double[] pointsForThisHand = pointsHandler.getPlayerPoints(gameId);
			switch (gameId){
			case 0:
				loadPoints(set1,pointsForThisHand);
				return;
			case 1:
				loadPoints(set2,pointsForThisHand);
				return;
			case 2:
				loadPoints(set3,pointsForThisHand);
				return;
			case 3:
				loadPoints(set4,pointsForThisHand);
				return;
			case 4:
				loadPoints(set5,pointsForThisHand);
				return;
			}
	}
	/*
	 * This method displays the points for each player in the UI
	 * @param TextView[] set The set of UI components where the display is being made
	 * @param double[] points The points to be set in the display
	 */
	private void loadPoints(TextView[] set, double[] points){
		
		for (int i=0; i<6;i++){
			String s =String.valueOf(points[i]);
			Log.d("Settingpoints",s);
			set[i].setText(s);
		}
	}
	/*
	 * This method displays the name of each player in the UI
	 */
	private void loadTitle(){
		Intent intent = getIntent();
		playerNames = intent.getStringArrayExtra("playerNames");	
		Log.d("playerNames",String.valueOf(playerNames.length));
		for (int i=0;i<playerNames.length ;i++){
			player[i].setText(String.valueOf(playerNames[i]));
		}
		
		
	}
	/*
	 * This method is called when add points button is pressed. It calls the AddPointsActivitu class.
	 * @param View v the Current view that the app is showing
	 */
	public void btnAddPoints(View v) {
		setNum++;
		Intent intent = new Intent(v.getContext(), AddPointsActivity.class);
		intent.putExtra("playerNames", playerNames);
	    startActivity(intent);
	}
	/*
	 * This method is called when end game button is pressed. It gets the total points from the Database and
	 * puts them in a dialog box for display.
	 * @param View v the Current view that the app is showing
	 */
	public void endGame(View v){
		
		double[] finalScores = pointsHandler.findWinner();
		int win =pointsHandler.getWinner();
		final Intent intent = new Intent(v.getContext(), MenuActivity.class);
		String message="";
		for(int i =0; i<playerNames.length;i++){
			if (finalScores[i]<0){
				message = message +" "+playerNames[i]+ " owes "+finalScores[i]+ ";";
			}
			else{
				message = message +" "+playerNames[i]+ " wins "+finalScores[i]+ ";";
			}
		}
		message = message + "And, the winner with most points is "+playerNames[win];
		// Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
               .setPositiveButton(R.string.btn_ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   
               			startActivity(intent);
                   }
               });
        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
		
	}
	
}
