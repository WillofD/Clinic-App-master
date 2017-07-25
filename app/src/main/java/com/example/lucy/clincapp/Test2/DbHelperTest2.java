package com.example.lucy.clincapp.Test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DbHelperTest2 extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "triviaQuizz";
	// tasks table name
	private static final String TABLE_QUEST = "quest";
	// tasks Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPYES= "opyes"; //option a
	private static final String KEY_OPNO= "opno"; //option b
	static ArrayList<String> questionlist  = new ArrayList<String>();;
	public static String questions;
	static Context  mContext;
	private SQLiteDatabase dbase;

	public  DbHelperTest2(Context context,String a){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		if(dbase == null)
		{
			return;
		}
		dbase.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		onCreate(dbase);
	}

	public DbHelperTest2(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPYES +" TEXT, "
				+KEY_OPNO +" TEXT)";
		dbase.execSQL(sql);
		addQuestions();
		//db.close();
	}
	private void addQuestions() {

		ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
			QuestionTest2 q1 = new QuestionTest2(""+ questionlist.get(0)
					, "YES", "NO", "YES");
			this.addQuestion(q1);
			QuestionTest2 q2 = new QuestionTest2("" + questionlist.get(1)
					, "YES", "NO", "YES");
			this.addQuestion(q2);
			QuestionTest2 q3 = new QuestionTest2("" + questionlist.get(2)
					, "YES", "NO", "YES");
			this.addQuestion(q3);
			QuestionTest2 q4 = new QuestionTest2("" + questionlist.get(3)
					, "YES", "NO", "YES");
			this.addQuestion(q4);

		} else

		{
			Toast.makeText(mContext, "Internet Connection Is Required", Toast.LENGTH_LONG).show();

		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}
	// Adding new question
	public void addQuestion(QuestionTest2 quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION()); 
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPYES, quest.getOPYES());
		values.put(KEY_OPNO, quest.getOPNO());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);		
	}
	public List<QuestionTest2> getAllQuestions() {
		List<QuestionTest2> quesList = new ArrayList<QuestionTest2>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				QuestionTest2 quest = new QuestionTest2();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPYES(cursor.getString(3));
				quest.setOPNO(cursor.getString(4));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}

	public static void RetriveQuestionsTest2() {



		System.out.print("is there any errorr??");
		String URL_UPDATE ="http://52.89.46.93/shutClinicApp/?methodName=get.testQuestions&test_data_id=2";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						System.out.println("this is responce"+response);
						try {
							JSONObject j = null;
							j = new JSONObject(response);

							JSONArray resopnseObject = j.getJSONArray("responseMsg");

							for(int i = 0; i < resopnseObject.length(); i++)
							{
								JSONObject jresponse = resopnseObject.getJSONObject(i);
								questions = jresponse.getString("question");
								questionlist.add(questions);

								//Toast.makeText(mContext, ""+questionlist, Toast.LENGTH_SHORT).show();


							}

						}catch(Exception e) {


						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						//Toast.makeText(Adddata.this,error.toString(), Toast.LENGTH_LONG ).show();
					}
				}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> params = new Hashtable<String, String>();
				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(mContext);
		requestQueue.add(stringRequest);
	}

	public static void cleararray()
	{
		questionlist.clear();

	}
}
