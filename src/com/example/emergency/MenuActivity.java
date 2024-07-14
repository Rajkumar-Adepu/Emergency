package com.example.emergency;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuActivity  extends Activity {
	private SharedPreferences prefs;
	private String prefName = "Settings";
	GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageView iv=(ImageView)findViewById(R.id.imageView1);
        ImageButton iv2=(ImageButton)findViewById(R.id.imageView2);
        ImageButton iv3=(ImageButton)findViewById(R.id.imageButton1);

        gps = new GPSTracker(this);
        final Location nwLocation = gps.getLocation(LocationManager.NETWORK_PROVIDER);

        iv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MenuActivity.this,SettingsActivity.class);
				startActivity(it);
			}
		});
        iv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it=new Intent(MenuActivity.this,MainActivity.class);
				startActivity(it);
			}
		});
          iv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				prefs = getSharedPreferences(prefName, MODE_PRIVATE);
				String status=String.valueOf(prefs.getString("Status", ""));
				String num=prefs.getString("PhoneNumber", "");
				System.out.println("$%#%$#%$#"+num+status);
				Toast.makeText(getApplicationContext(), status, 90).show();
       			if(status.equals("SMS")){
       			 String stringLatitude = String.valueOf( nwLocation.getLatitude());

		            String stringLongitude = String.valueOf(nwLocation.getLongitude());
					String body = "http://maps.google.fr/maps?f=q&source=s_q&hl=fr&geocode=&q="
							+ stringLatitude + "," + stringLongitude;

					SmsManager sms1 = SmsManager.getDefault();
					sms1.sendTextMessage(num, null,
							"PLESAE HELP ME AM AT :"+ body, null, null);
					Toast.makeText(getApplicationContext(), "Sent SMS", Toast.LENGTH_LONG).show();


       				
       			}else{
       				Intent it=new Intent(Intent.ACTION_CALL);
       				it.setData(Uri.parse("tel:"+num));
       				startActivity(it);
       			}

			}
		});
    }

    
}
