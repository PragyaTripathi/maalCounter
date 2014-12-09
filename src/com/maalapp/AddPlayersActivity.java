package com.maalapp;



import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * AddPlayersActivity gets player names input from the user.
 * This activity's content is activity_add_players.xml.
 * 
 */
@SuppressLint("InlinedApi")
public class AddPlayersActivity extends Activity{
	/**
	 * Set the UI components to be manipulated.
	 */
	
	EditText player1;
	EditText player2;
	EditText player3;
	EditText player4;
	EditText player5;
	EditText player6;
	/*
	 * Instead of calling the constructor. OnCreate is called to setup the state of this activity.
	 * @param Bundle savedInstanceState This parameter is trying to pass any saved state for this activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(R.layout.activity_add_players);
		
		player1 = (EditText) findViewById(R.id.editTextPlayer1);
		player2 = (EditText) findViewById(R.id.editTextPlayer2);
		player3 = (EditText) findViewById(R.id.editTextPlayer3);
		player4 = (EditText) findViewById(R.id.editTextPlayer4);
		player5 = (EditText) findViewById(R.id.editTextPlayer5);
		player6 = (EditText) findViewById(R.id.editTextPlayer6);

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
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	
	/*
	 * This method is called when next button is pressed. It calls the ScoreboardActivity class.
	 * This method also extracts all the player names input by the user and puts them in an array.
	 * It then puts it in a bundle and passes it to ScoreboardActivity class.
	 * @param View v the Current view that the app is showing
	 */
	public void btnNext(View v) {
		int count = 0;
		ArrayList<EditText> arrayOfValues=new ArrayList<EditText>();
		String[] players=new String[6];
		arrayOfValues.add(player1);
		arrayOfValues.add(player2);
		arrayOfValues.add(player3);
		arrayOfValues.add(player4);
		arrayOfValues.add(player5);
		arrayOfValues.add(player6);
		for (EditText value:arrayOfValues){
			String name = value.getText().toString();
			if( name != null){
				players[count]=name;
				count = count +1;
			}
		}
		Log.d("count",""+count);
		if (count<2){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
			builder.setTitle("Error");
			builder.setMessage("You need to enter atleast two players!");
			
			builder.setNeutralButton(R.string.dummy_button, new DialogInterface.OnClickListener() {

			    public void onClick(DialogInterface dialog, int which) {
			        // Do do my action here

			        dialog.dismiss();
			    }

			});

			AlertDialog alert = builder.create();
			alert.show();
		}
		else{
			
			Intent intent = new Intent(v.getContext(), ScoreboardActivity.class);
			intent.putExtra("playerNames", players);
		    startActivity(intent);
			
			}
			
		}
		
		
	}
