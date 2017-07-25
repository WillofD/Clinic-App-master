package com.example.lucy.clincapp.Test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lucy.clincapp.R;
import com.example.lucy.clincapp.Test1.DbHelper;
import com.example.lucy.clincapp.Test1.Question;
import com.example.lucy.clincapp.Test1.QuizActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivityTest extends Activity {
	List<QuestionTest2> quesList;
	int score=0;
	int qid=0;
	QuestionTest2 currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc;
	Button butNext;
	public static ArrayList<String> answerListTest2 = new ArrayList<String>();
	RadioGroup grp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_test2);
		DbHelperTest2 dba = new DbHelperTest2(this,"a");
		DbHelperTest2 db=new DbHelperTest2(this);

		quesList=db.getAllQuestions();
		currentQ=quesList.get(qid);
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);

		butNext=(Button)findViewById(R.id.button1);
		setQuestionView();
		grp = (RadioGroup) findViewById(R.id.radioGroup1);

		grp.clearCheck();

		butNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (grp.getCheckedRadioButtonId() != -1) {

					RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
					Log.d("yourans", " " + answer.getText());
					answerListTest2.add(answer.getText().toString());
					if (currentQ.getANSWER().equals(answer.getText())) {
						score++;
						Log.d("score", "Your score" + score);
						grp.clearCheck();

					}
					if (qid < 4) {
						currentQ = quesList.get(qid);
						setQuestionView();
						grp.clearCheck();
					} else {
						Intent intent = new Intent(QuizActivityTest.this, ResultActivityTest2.class);
						Bundle b = new Bundle();
						b.putInt("score", score); //Your score
						intent.putExtras(b); //Put your score to your next Intent
						startActivity(intent);
						finish();
					}
				}else
				{

					Toast.makeText(QuizActivityTest.this,
							"Please seelct any one option..", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_quiz, menu);
//		return true;
//	}
	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPYES());
		rdb.setText(currentQ.getOPNO());
		qid++;


	}

	protected void onDestroy() {
		super.onDestroy();
		DbHelperTest2.cleararray();
		DbHelperTest2.RetriveQuestionsTest2();

		if(!answerListTest2.isEmpty()) {

			QuizActivity.SendResultToServerTest2(answerListTest2.get(0), answerListTest2.get(1), answerListTest2.get(2), answerListTest2.get(3), this);
			answerListTest2.clear();
		}

	}
}
