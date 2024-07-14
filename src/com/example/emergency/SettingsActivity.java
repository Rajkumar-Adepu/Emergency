package com.example.emergency;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	static final int PICK_CONTACT = 1;
	String cNumber;
	private RadioGroup radioSexGroup;
	private RadioButton radioSexButton;
	private SharedPreferences prefs;
	private String prefName = "Settings";

	EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		et = (EditText) findViewById(R.id.editText1);
		radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		prefs = getSharedPreferences(prefName, MODE_PRIVATE);

		String status = String.valueOf(prefs.getString("Status", ""));
		String num = prefs.getString("PhoneNumber", "");
		et.setText(num);
		int selectedId = radioSexGroup.getCheckedRadioButtonId();

		radioSexButton = (RadioButton) findViewById(selectedId);

		
		Button bt2 = (Button) findViewById(R.id.button2);
		bt2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				startActivityForResult(intent, PICK_CONTACT);
			}
		});
		Button bt3 = (Button) findViewById(R.id.button3);
		bt3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String number = et.getText().toString();
				int selectedId=radioSexGroup.getCheckedRadioButtonId();
	            radioSexButton=(RadioButton)findViewById(selectedId);
	            Toast.makeText(SettingsActivity.this,radioSexButton.getText(),Toast.LENGTH_SHORT).show();
				prefs = getSharedPreferences(prefName, MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();

				// ---save the values in the EditText view to preferences---
				editor.putString("PhoneNumber", number);
				editor.putString("Status", radioSexButton.getText().toString());

				// ---saves the values---
				editor.commit();
				Intent it = new Intent(SettingsActivity.this,
						MenuActivity.class);
				startActivity(it);
				Toast.makeText(getBaseContext(), "Saved"+ radioSexButton.getText().toString(), Toast.LENGTH_SHORT)
						.show();

			}
		});

	}

	@Override
	public void onActivityResult(int reqCode, int resultCode, Intent data) {
		super.onActivityResult(reqCode, resultCode, data);

		switch (reqCode) {
		case (PICK_CONTACT):
			if (resultCode == Activity.RESULT_OK) {

				Uri contactData = data.getData();
				@SuppressWarnings("deprecation")
				Cursor c = managedQuery(contactData, null, null, null, null);
				if (c.moveToFirst()) {

					String id = c
							.getString(c
									.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

					String hasPhone = c
							.getString(c
									.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

					if (hasPhone.equalsIgnoreCase("1")) {
						Cursor phones = getContentResolver()
								.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
										null,
										ContactsContract.CommonDataKinds.Phone.CONTACT_ID
												+ " = " + id, null, null);
						phones.moveToFirst();
						cNumber = phones.getString(phones
								.getColumnIndex("data1"));
						et.setText(cNumber);
						System.out.println("number is:" + cNumber);
					}
					// String name =
					// c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				}
			}
			break;
		}
	}
}
