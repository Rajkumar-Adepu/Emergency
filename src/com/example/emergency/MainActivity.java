package com.example.emergency;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String MyPREFERENCES = "MyPrefs1" ;
	public static final String Word = "wordKey";
	SharedPreferences sharedpreferences;

	/*
	 * HOSPITAL, POLICE, FIRESTATION, and AMBULANCE are constant variables that are assigned
	 * to their respective emergency itent. It helps us to draw icons and perform web services
	 * related activities.
	 */
	private static final int HOSPITAL = 1;
	private static final int POLICE = 2;
	private static final int FIRESTATION = 3;
	private static final int AMBULANCE = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
			setContentView(R.layout.activity_main);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_settings:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Phone Number");

			// Set up the input
			final EditText input = new EditText(this);
			sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
			input.setText(sharedpreferences.getString("wordKey", ""));
			// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
			input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_PHONE);
			builder.setView(input);

			// Set up the buttons
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
					
				     String   m_Text = input.getText().toString();

					SharedPreferences.Editor editor = sharedpreferences.edit();
			            editor.putString(Word, m_Text);
			            editor.commit();
			            Toast.makeText(MainActivity.this,"Successfully Stored",Toast.LENGTH_LONG).show();
			           // finish();
			           
			     
			    }
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			        dialog.cancel();
			    }
			});

			builder.show();
			
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * This is the main screen of our application. This is the first screen shown after a user has registered.
	 * The registration screen only shows if it is the users first time using the application.
	 * @param v
	 */
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.sos_button) {
			startActivity(new Intent(this, Sos.class));
		} else if (id == R.id.firestation_button) {
			Intent firestation = new Intent(this, Maps.class);
			firestation.putExtra("key", FIRESTATION);
			startActivity(firestation);
		} else if (id == R.id.hospital_button) {
			Intent hospital = new Intent(this, Maps.class);
			hospital.putExtra("key", HOSPITAL);
			startActivity(hospital);
		} else if (id == R.id.ambulance_button) {
			Intent ambulance = new Intent(this, Maps.class);
			ambulance.putExtra("key", AMBULANCE);
			startActivity(ambulance);
		} else if (id == R.id.police_button) {
			Log.v("Shravan", "Police");
			Intent police = new Intent(this, Maps.class);
			police.putExtra("key", POLICE);
			startActivity(police);
		
		} 
    }

	/** used upon resuming the application. 
	 *
	 */
	public void onResume(){
		super.onResume();
		Log.v(null, "MainActivity's onResume Method !!!");
	}

	/**
	 * an empty onPause method
	 */
	public void onPause(){
		super.onPause();
		Log.v(null, "MainActivity's onPause Method !!!");
	}
	/**
	 * Called before interrupt to save data.
	 */
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
		// nothing needs to be saved here since nothing is changed or being used. 
	}
	/**
	 * Called during onResume() to restore data.
	 */
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
		// Restore session score
		// nothing needs to be restored here since nothing is used previously. 
	}
}