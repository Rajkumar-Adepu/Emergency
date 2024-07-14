
package com.example.emergency;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "services_db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String NAME = "NAME";
	public static final String CONTACT = "CONTACT";
	public static final String LAT = "LAT";
	public static final String LNG = "LNG";
	public static final String TABLE_NAME = "ambulances";
	public static final String TABLE_HEADER = NAME+", "+CONTACT+", "+LAT+", "+LNG;
	
	private ArrayList<String> result = new ArrayList<String>();
	private SQLiteDatabase dbase;
	
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		dbase = this.getWritableDatabase();
//		onCreate(dbase);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		result = new ArrayList<String>();

		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + NAME + " VARCHAR(255),"
				+ CONTACT + " VARCHAR(255)," + LAT + " FLOAT," + LNG + " FLOAT);");
		
		
		//db.execSQL("INSERT INTO "+TABLE_NAME+"("+taxiHeaders+") VALUES ('Car', 1, 4, 4, 3, 2, 3, 1)");
		addAmbulance("Abhay Ambulance", "090321 07107",
				Float.parseFloat("17.426797"), Float.parseFloat("78.452480"),
				db);
		addAmbulance("Anjali Ambulance Services", "098664 47033",
				Float.parseFloat("17.426498"), Float.parseFloat("78.451132"),
				db);
		addAmbulance("Hyderabad Ambulance Services", "080191 49494",
				Float.parseFloat("17.394158"), Float.parseFloat("78.505739"),
				db);
		addAmbulance("Abhay Cab & Ambulance Services", "090321 04014",
				Float.parseFloat("17.426163"), Float.parseFloat("78.452824"),
				db);
		addAmbulance("Air Ambulance services", "098454 46634",
				Float.parseFloat("17.426797"), Float.parseFloat("78.452480"),
				db);
		addAmbulance("Amma Sri Meena Ambulance Services", "076809 95421",
				Float.parseFloat("17.480197"), Float.parseFloat("78.417103"),
				db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	// WRAPPER METHOD FOR ADDING A STUDENT
	public void addAmbulance(String name, String contact, float lat, float lng,
			SQLiteDatabase db) {
		// CREATE A CONTENTVALUE OBJECT
		/*
		 * ContentValues cv = new ContentValues(); cv.put(Database.NAME, name);
		 * cv.put(Database.CONTACT, contact); cv.put(Database.LAT, lat);
		 * cv.put(Database.LNG, lng); // RETRIEVE WRITEABLE DATABASE AND INSERT
		 * long result = sqdb.insert(Database.TABLE_NAME, null, cv); return
		 * result;
		 */

		String insertQuery = "INSERT INTO " + Database.TABLE_NAME + "("
				+ Database.NAME + ", " + Database.CONTACT + ", " + Database.LAT
				+ ", " + Database.LNG + ") VALUES ('" + name + "', " + contact
				+ ", " + lat + ", " + lng + ")";
		db.execSQL(insertQuery);
	}

	public ArrayList<String> sort(){
		dbase = this.getWritableDatabase();
		Cursor c = dbase.rawQuery("SELECT NAME, CONTACT, LAT, LNG  FROM AMBULANCES ", null );
		
		if (c != null ) {
    		if  (c.moveToFirst()) {
    			do {
    				String NAME = c.getString(c.getColumnIndex("NAME"));
    				String CONTACT = c.getString(c.getColumnIndex("CONTACT"));
    				String LAT = c.getString(c.getColumnIndex("LAT"));
    				String LONG = c.getString(c.getColumnIndex("LNG"));
    				result.add("" + NAME + "," + CONTACT + ","+ LAT + ","+ LONG);
    			}while (c.moveToNext());
    		} 
    	}
		c.close();
		return result;
	}
}
