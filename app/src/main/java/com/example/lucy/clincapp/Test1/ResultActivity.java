package com.example.lucy.clincapp.Test1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lucy.clincapp.R;
import com.example.lucy.clincapp.Test2.QuizActivityTest;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class ResultActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		//get rating bar object
		RatingBar bar = (RatingBar) findViewById(R.id.ratingBar1);
		bar.setNumStars(5);
		bar.setStepSize(0.5f);
		//get text view
		TextView t = (TextView) findViewById(R.id.textResult);
		Button test2Btn = (Button) findViewById(R.id.test1btn);


		test2Btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent OpenTest2 = new Intent(ResultActivity.this, QuizActivityTest.class);
				OpenTest2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OpenTest2);
			}
		});
		//get score
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		//display score
		bar.setRating(score);



		switch (score) {
			case 0:
				test2Btn.setVisibility(View.INVISIBLE);
				t.setText(" Congratulations! You have a healthy usage of Internet. \uF04A");

			case 1:
				test2Btn.setVisibility(View.INVISIBLE);
				t.setText(" Congratulations! You have a healthy usage of Internet. \uF04A");
			case 2:
				test2Btn.setVisibility(View.INVISIBLE);
				t.setText(" Congratulations! You have a healthy usage of Internet. \uF04A");
			case 3:
				test2Btn.setVisibility(View.INVISIBLE);
				t.setText(" Congratulations! You have a healthy usage of Internet. \uF04A");
			case 4:
				test2Btn.setVisibility(View.INVISIBLE);
				t.setText(" Congratulations! You have a healthy usage of Internet. \uF04A");
				break;
			case 5:
				test2Btn.setVisibility(View.VISIBLE);
				t.setText(" Please check the impact of your online activities on your lifestyle by answering the following questions ");
				break;
		}
	}

	//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_result, menu);
//		return true;
//	}
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 finish();
	}




}
