package com.example.lucy.clincapp.Test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.lucy.clincapp.R;
import com.example.lucy.clincapp.Suggestions.SuggestionActivity;

public class ResultActivityTest2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_test2);
		//get rating bar object
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
		bar.setNumStars(4);
		bar.setStepSize(0.5f);
		//get text view
		TextView t=(TextView)findViewById(R.id.textResult);
		Button test1Btn = (Button) findViewById(R.id.test2btn);


		test1Btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent OpenTest1 = new Intent(ResultActivityTest2.this, SuggestionActivity.class);
				OpenTest1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(OpenTest1);
			}
		});


		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		//display score
		bar.setRating(score);
		switch (score)
		{
			case 0:
				test1Btn.setVisibility(View.INVISIBLE);
				t.setText("You are just beginning to get addicted!!!");
				break;

			case 1:
				test1Btn.setVisibility(View.INVISIBLE);
				t.setText("You are just beginning to get addicted!!!  ");
				break;
		case 2:
			test1Btn.setVisibility(View.INVISIBLE);
			t.setText("You are just beginning to get addicted!!!  ");
			break;
		case 3:
			test1Btn.setVisibility(View.VISIBLE);
			t.setText("You Need Help!!!\n Help yourself by following the suggessions\n"
					 );
			break;
		case 4:
			test1Btn.setVisibility(View.VISIBLE);
			t.setText(" You Need Help!!!\n Help yourself by following the suggessions\n"
					 );
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
