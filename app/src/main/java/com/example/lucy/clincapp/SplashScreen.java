package com.example.lucy.clincapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.lucy.clincapp.Test1.DbHelper;
import com.example.lucy.clincapp.Test1.QuizActivity;
import com.example.lucy.clincapp.Test2.DbHelperTest2;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences.Editor editor;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView myImageView= (ImageView)findViewById(R.id.imageView);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myImageView.startAnimation(myFadeInAnimation);

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            DbHelper db = new DbHelper(this);
            DbHelper.cleararray();
            db.RetriveQuestions();

            DbHelperTest2 dbtest2 = new DbHelperTest2(this);
            DbHelperTest2.cleararray();
            dbtest2.RetriveQuestionsTest2();
        }else
        {

        }
//        editor = this.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putBoolean("istablethere", true);
//        editor.apply();


     //   DbHelper.RetriveQuestions();

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);

                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }



    protected void onStart() {
        super.onStart();

    }

}
