package com.maalapp;





import java.util.HashMap;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.MenuItem;
import android.widget.EditText;
//import android.widget.RadioButton;
import android.support.v4.app.NavUtils;

/**
 * This activity class lets the user edit the maal point values and puts the updates values in Points database.
 * This activity's content is activity_edit_maal.xml.
 * 
 * @see SystemUiHider
 */
public class EditMaalActivity extends Activity {
	
	//load the UI

	EditText edSinglePoplu;
	EditText edSingleTiplu;
	EditText edSingleJhiplu;
	EditText edDoublePoplu;
	EditText edDoubleTiplu;
	EditText edDoubleJhiplu;
	EditText edTriplePoplu;
	EditText edTripleJhiplu;
	EditText edSingleMarriage;
	EditText edDoubleMarriage;
	EditText edOrdinaryCardTunnella;
	EditText edOrdinaryJokerTunnella;
	EditText edPopluJhipluTunnella;
	EditText edMultiplier;

	DatabaseMaalHandler dbHandler;// database that has all the maal points

	HashMap<String,Double> maalLoader=new HashMap<String,Double>();// map to keep track of maal names and their values
	/*
	 * Instead of calling the constructor. OnCreate is called to setup the state of this activity.
	 * @param Bundle savedInstanceState This parameter is trying to pass any saved state for this activity.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_edit_maal);
		setupActionBar();

		
		initializeTextView();
		dbHandler= new DatabaseMaalHandler(getApplicationContext());
		maalLoader= dbHandler.getAllMaals();

		edSinglePoplu.setText(String.valueOf(maalLoader.get("singlePoplu")));
		edSingleTiplu.setText(String.valueOf(maalLoader.get("singleTiplu")));
		edSingleJhiplu.setText(String.valueOf(maalLoader.get("singleJhiplu")));
		edDoublePoplu.setText(String.valueOf(maalLoader.get("doublePoplu")));
		edDoubleTiplu.setText(String.valueOf(maalLoader.get("doubleTiplu")));
		edDoubleJhiplu.setText(String.valueOf(maalLoader.get("doubleJhiplu")));
		edTriplePoplu.setText(String.valueOf(maalLoader.get("triplePoplu")));
		edTripleJhiplu.setText(String.valueOf(maalLoader.get("tripleJhiplu")));
		edSingleMarriage.setText(String.valueOf(maalLoader.get("singleMarriage")));
		edDoubleMarriage.setText(String.valueOf(maalLoader.get("doubleMarriage")));
		edOrdinaryCardTunnella.setText(String.valueOf(maalLoader.get("ordinaryCardTunnella")));
		edOrdinaryJokerTunnella.setText(String.valueOf(maalLoader.get("ordinaryJokerTunnella")));
		edPopluJhipluTunnella.setText(String.valueOf(maalLoader.get("popluJhipluTunnella")));
		edMultiplier.setText(String.valueOf(dbHandler.getMultiplier()));
		
		

		
	}
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
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
 * This method simply initializes components of UI
 */
	private void initializeTextView(){
		edSinglePoplu = (EditText) findViewById(R.id.editTextSinglePoplu);
		edSingleTiplu = (EditText) findViewById(R.id.editTextSingleTiplu);
		edSingleJhiplu = (EditText) findViewById(R.id.editTextSingleJhiplu);
		edDoublePoplu = (EditText) findViewById(R.id.editTextDoublePoplu);
		edDoubleTiplu = (EditText) findViewById(R.id.editTextDoubleTiplu);
		edDoubleJhiplu = (EditText) findViewById(R.id.editTextDoubleJhiplu);
		edTriplePoplu = (EditText) findViewById(R.id.editTextTriplePoplu);
		edTripleJhiplu = (EditText) findViewById(R.id.editTextTripleJhiplu);
		edSingleMarriage = (EditText) findViewById(R.id.editTextSingleMarriage);
		edDoubleMarriage = (EditText) findViewById(R.id.editTextDoubleMarriage);
		edOrdinaryCardTunnella = (EditText) findViewById(R.id.editTextOrdinaryCardTunnella);
		edOrdinaryJokerTunnella = (EditText) findViewById(R.id.editTextOrdinaryJokerTunnella);
		edPopluJhipluTunnella = (EditText) findViewById(R.id.editTextPopluJhipluTunnella);
		edMultiplier = (EditText) findViewById(R.id.editTextMultiplier);
		
	}
	/*
	 * This method is called when save button is pressed. It gets the points set by the user and puts them in a map to be put in the database.
	 * @param View v the Current view that the app is showing
	 */
	public void btnSave(View v){
		
		maalLoader.put("singlePoplu",Double.valueOf(edSinglePoplu.getText().toString()));
		maalLoader.put("singleTiplu",Double.valueOf(edSingleTiplu.getText().toString()));
		maalLoader.put("singleJhiplu",Double.valueOf(edSingleJhiplu.getText().toString()));
		maalLoader.put("doublePoplu",Double.valueOf(edDoublePoplu.getText().toString()));
		maalLoader.put("doubleTiplu",Double.valueOf(edDoubleTiplu.getText().toString()));
		
		maalLoader.put("doubleJhiplu",Double.valueOf(edDoubleJhiplu.getText().toString()));
		maalLoader.put("triplePoplu",Double.valueOf(edTriplePoplu.getText().toString()));
		maalLoader.put("tripleJhiplu",Double.valueOf(edTripleJhiplu.getText().toString()));
		maalLoader.put("singleMarriage",Double.valueOf(edSingleMarriage.getText().toString()));
		maalLoader.put("doubleMarriage",Double.valueOf(edDoubleMarriage.getText().toString()));
		
		maalLoader.put("ordinaryCardTunnella",Double.valueOf(edOrdinaryCardTunnella.getText().toString()));
		maalLoader.put("ordinaryJokerTunnella",Double.valueOf(edOrdinaryJokerTunnella.getText().toString()));
		maalLoader.put("popluJhipluTunnella",Double.valueOf(edPopluJhipluTunnella.getText().toString()));
		maalLoader.put("multiplier",Double.valueOf(edMultiplier.getText().toString()));
		dbHandler.updateDatabase(maalLoader);
		
    	Intent intent = new Intent(v.getContext(), MenuActivity.class);
    	startActivity(intent);
    }
//	public void onRadioButtonClicked(View view) {
//	    // Is the button now checked?
//	    boolean checked = ((RadioButton) view).isChecked();
//	    
//	    // Check which radio button was clicked
//	    switch(view.getId()) {
//	        case R.id.radio_dollar:
//	            if (checked)
//	            	handler.setCurrency("$");
//	            break;
//	        case R.id.radio_rupees:
//	            if (checked)
//	            	handler.setCurrency("NRs.");
//	            break;
//	    }
//	}
	
}
