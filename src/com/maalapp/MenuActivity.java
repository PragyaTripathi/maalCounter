package com.maalapp;



import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;


import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

/**
 * An activity that shows all the buttons to interact with this app.
 * 
 * @see SystemUiHider
 */
public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);
		setupActionBar();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);


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
	/*
	 * This method is called when Edit Maal button is pressed. It calls EditMaalActivity class.
	 * @param View v the Current view that the app is showing
	 */
	public void btnEditMaal(View v){
		Intent intent = new Intent(v.getContext(), EditMaalActivity.class);
    	startActivity(intent);
	
	}
	/*
	 * This method is called when Add Players button is pressed. It calls EditMaalActivity class.
	 * @param View v the Current view that the app is showing
	 */
	public void btnAddPlayers(View v){
		Intent intent = new Intent(v.getContext(), AddPlayersActivity.class);
    	startActivity(intent);
	
	}
}
