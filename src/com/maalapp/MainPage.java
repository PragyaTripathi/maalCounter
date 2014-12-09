package com.maalapp;


import com.maalapp.R;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
/**
 * 
 * @author Pragya
 *This activity shows the splash screen.
 */

public class MainPage extends Activity {
	private volatile Thread mSplash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		final MainPage splashScreen = this;
	    
	      mSplash=new Thread(){
	    	
	    	  @Override
	    	  public void run(){
	    		  try{
	    			  synchronized(this){
	    				  wait(4000);
	    				  }
	    			  }
	    		  catch(InterruptedException e){}
	    		  finish();
	    	
	    	Intent mIntent = new Intent();
	    	mIntent.setClass(splashScreen, MenuActivity.class);
	    	startActivity(mIntent);
	    	finish();
	    }
	};
	
	mSplash.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_page, menu);
		return true;
	}
	@Override
	public boolean onTouchEvent(MotionEvent mEvent){
		if(mEvent.getAction()==MotionEvent.ACTION_DOWN){
			synchronized(mSplash){
				mSplash.notifyAll();
			}
		}
		return true;
	}
	

	
	    
	    
	   
}
