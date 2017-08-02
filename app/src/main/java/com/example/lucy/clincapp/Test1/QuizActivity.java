package com.example.lucy.clincapp.Test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lucy.clincapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class QuizActivity extends Activity {
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb ;
	RadioGroup rdgroup;
	Button butNext;
	String Name;
	String Contactno;
	int flage = 0;
	DbHelper db;
	SharedPreferences.Editor editor;
	SharedPreferences en;
	String nametxt,contactTxt;
	public static String radiotxt;
	private SQLiteDatabase database;
	static  String user_id;
	public static ArrayList<String> answerList = new ArrayList<String>();
	AlertDialog dialog;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		answerList.clear();
		DbHelper dba = new DbHelper(this,"a");
		db=new DbHelper(this);

		quesList=db.getAllQuestions();
		if(quesList == null){

			return;
		}

		currentQ=quesList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdgroup =(RadioGroup) findViewById(R.id.radioGroup1);


		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("										Register");


		final EditText name = new EditText(this);
		final TextView tvname = new TextView(this);
		final TextView tvcontactno = new TextView(this);
		final TextView tvgender= new TextView(this);
		final EditText contactno = new EditText(this);
		final RadioButton male = new RadioButton(this);
		final RadioButton female = new RadioButton(this);
		tvname.setText("Name:");
		tvname.setTextSize(20);
		tvgender.setText("Gender:");
		tvgender.setTextSize(20);
		tvcontactno.setText("Contact no:");
		tvcontactno.setTextSize(20);
		male.setHint("Male");
		female.setHint("Female");

		contactno.setInputType(InputType.TYPE_CLASS_PHONE );
		contactno.setMaxEms(10);

		contactno.setFilters(new InputFilter[] { new InputFilter.LengthFilter(10) });



		//divakar


		tvname.setInputType(InputType.TYPE_NULL );
		tvcontactno.setInputType(InputType.TYPE_NULL );
		tvgender.setInputType(InputType.TYPE_NULL);

	;



		LinearLayout layout = new LinearLayout(this);

		//Create a TextView to add to layout
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.addView(tvname);
		layout.addView(name);
		layout.addView(tvcontactno);
		layout.addView(contactno);
		layout.addView(tvgender);
		layout.addView(male);
		layout.addView(female);
		builder.setView(layout);

		male.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				female.setChecked(false);
				radiotxt = "male";

			}
		});

		female.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				male.setChecked(false);
				radiotxt = "female";
			}
		});



// Set up the buttons
//		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//
//				nametxt = name.getText().toString().trim();
//				contactTxt = contactno.getText().toString().trim();
//				userUpdate(nametxt,contactTxt,radiotxt);
//				if (nametxt.equalsIgnoreCase("") && contactTxt.equalsIgnoreCase("")) {
//					name.setError("This field can not be blank");
//					contactno.setError("This field can not be blank");
//				}
//
//
//				contactno.addTextChangedListener(new TextWatcher()  {
//
//					@Override
//					public void onTextChanged(CharSequence s, int start, int before, int count) {
//					}
//
//					@Override
//					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//					}
//
//					@Override
//					public void afterTextChanged(Editable s)  {
//						if (name.getText().toString().length() <= 0) {
//							name.setError("Enter FirstName");
//						} else {
//							name.setError(null);
//						}
//					}
//				});
//
//
//			}
//		});





		// code for alert dialog


		builder.setNeutralButton(android.R.string.ok, null);
		dialog = builder.create();


		//dialog.setCancelable(false);


	//dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
	//	.setEnabled(true);



		dialog.setOnShowListener(new DialogInterface.OnShowListener() {

			@Override
			public void onShow(final DialogInterface dialogreal) {

				Button b = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
				b.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						// TODO Do something

						//Dismiss once everything is OK.
						if(TextUtils.isEmpty(name.getText().toString()))
						{
							name.setError("Please Enter your name");
						}
						else if(TextUtils.isEmpty(contactno.getText().toString()) )
						{
							contactno.setError("Please Enter your Contact Number");

						}

						else  if(TextUtils.isEmpty(radiotxt)){

							Toast.makeText(QuizActivity.this, "Please Enter Gender", Toast.LENGTH_SHORT).show();
						}else if(contactno.getText().toString().length() < 10 || contactno.getText().toString().length()>10){
							contactno.setError("Please Enter exact 10 numbers");
						}else
						{
							nametxt = name.getText().toString().trim();
							contactTxt = contactno.getText().toString().trim();
							userUpdate(nametxt,contactTxt,radiotxt);
							dialog.dismiss();

						}

						contactno.addTextChangedListener(new TextWatcher()  {

					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					}

					@Override
					public void afterTextChanged(Editable s)  {
						if (contactno.length() <= 10) {
							contactno.setError("please enter 10 numbers");
						}
					}
				});

					}
				});
			}
		});


		dialog.show();

		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialogInterface) {

				finish();
			}
		});

		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(name.getText().toString().isEmpty()){

				}
				else
				{
					flage++;
					if(flage == 2){
						dialog.getButton(AlertDialog.BUTTON_POSITIVE)
								.setEnabled(true);
					}
				}
			}
		});

		contactno.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {
				if(contactno.getText().toString().isEmpty()){

				}
				else if(isNumeric(contactno.getText().toString())){
					flage=flage+1;
					if(flage == 2){
						dialog.getButton(AlertDialog.BUTTON_POSITIVE)
								.setEnabled(true);
					}
				}
			}
		});


		rdgroup.clearCheck();
		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				if(rdgroup.getCheckedRadioButtonId() != -1){
				RadioGroup grp = (RadioGroup) findViewById(R.id.radioGroup1);
				RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
				Log.d("yourans"," " + answer.getText().toString());
					answerList.add(answer.getText().toString());
				if (currentQ.getANSWER().equals(answer.getText())) {
					score++;
					rdgroup.clearCheck();

//					Log.d("score", "Your score"+score);
				}
				if (qid < 5) {
					currentQ = quesList.get(qid);
					setQuestionView();
					grp.clearCheck();

				} else {
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score); //Your score
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
			}else{

					Toast.makeText(QuizActivity.this,
							"Please seelct any one option..", Toast.LENGTH_LONG).show();



				}
		}
		});




	}

	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPYES());
		rdb.setText(currentQ.getOPNO());
		qid++;
	}
	public static boolean isNumeric(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}



	// Rigister api

	private void userUpdate(String name, String phone,String gender) {

		String URL_UPDATE ="http://52.89.46.93/shutClinicApp/?methodName=user.register&name="+name+"&mobile="+phone+"&gender="+gender+"";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

                // System.out.println("this is responce"+response);
						try
						{
							JSONObject j = null;
							j = new JSONObject(response);

							JSONObject res = j.getJSONObject("responseMsg");
							 user_id = res.getString("user_id");
							//Toast.makeText(QuizActivity.this, ""+user_id, Toast.LENGTH_SHORT).show();

						}
						catch(Exception e)
						{


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



				//Creating parameters
				Map<String,String> params = new Hashtable<String, String>();




				//returning parameters
				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
	}



	@Override
	protected void onDestroy() {
		super.onDestroy();
		DbHelper.RetriveQuestions();
		//Toast.makeText(QuizActivity.this, "i am destroyed", Toast.LENGTH_SHORT).show();
		if(user_id != null && !answerList.isEmpty())
			{
			SendResultToServer(user_id, answerList.get(0), answerList.get(1), answerList.get(2), answerList.get(3),answerList.get(4));
			answerList.clear();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("this is Paused");

	}

	private void SendResultToServer(String user_id, String op1,String op2,String op3,String op4,String op5) {

		String URL_UPDATE ="http://52.89.46.93/shutClinicApp/?methodName=add.testAnswers&user_id="+user_id+"&test_data_id=1&question_answers=1-"+op1+"%2C2-"+op2+"%2C3-"+op3+"%2C4-"+op4+"%2C5-"+op5+"&date_time=2017-06-30+03%3A40%3A20+PM";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						// System.out.println("this is responce"+response);
						try
						{
							JSONObject j = null;
							j = new JSONObject(response);

							String res = j.getString("responseMsg");



						}
						catch(Exception e)
						{


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



				//Creating parameters
				Map<String,String> params = new Hashtable<String, String>();




				//returning parameters
				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(stringRequest);
	}

	// static method to send baack 2nd test details
	public static void SendResultToServerTest2( String op1,String op2,String op3,String op4,Context mcontext) {

		String URL_UPDATE ="http://52.89.46.93/shutClinicApp/?methodName=add.testAnswers&user_id="+user_id+"&test_data_id=2&question_answers=1-"+op1+"%2C2-"+op2+"%2C3-"+op3+"%2C4-"+op4+"&date_time=2017-06-30+03%3A40%3A20+PM";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						// System.out.println("this is responce"+response);
						try
						{
							JSONObject j = null;
							j = new JSONObject(response);

							String res = j.getString("responseMsg");



						}
						catch(Exception e)
						{


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



				//Creating parameters
				Map<String,String> params = new Hashtable<String, String>();




				//returning parameters
				return params;
			}

		};

		RequestQueue requestQueue = Volley.newRequestQueue(mcontext);
		requestQueue.add(stringRequest);
	}

}
