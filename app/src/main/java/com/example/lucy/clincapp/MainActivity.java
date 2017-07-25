package com.example.lucy.clincapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucy.clincapp.Suggestions.SuggestionActivity;
import com.example.lucy.clincapp.Test1.DbHelper;
import com.example.lucy.clincapp.Test1.QuizActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    ImageButton questions,suggestion,about,contact;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rl = (RelativeLayout)findViewById(R.id.activity_mainrl_layout);

        questions = (ImageButton) findViewById(R.id.questionbtn);
        suggestion =(ImageButton) findViewById(R.id.suggestionid);
        about = (ImageButton)  findViewById(R.id.aboutbtn);
        contact = (ImageButton) findViewById(R.id.contactid);


        questions.setOnClickListener(this);
        suggestion.setOnClickListener(this);
        about.setOnClickListener(this);
        contact.setOnClickListener(this);

// fist run
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun)
        {
            // show only once in lifetime
            showDialogagree(this);
        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.questionbtn:
                ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

                if (activeNetworkInfo != null && activeNetworkInfo.isConnected() && !DbHelper.questionlist.isEmpty()) {
                    Intent openquestions = new Intent(MainActivity.this, QuizActivity.class);
                    startActivity(openquestions);
                }else

                if(DbHelper.questionlist == null)
                {

                SnackbarView();
                }
                else
                {
                    SnackbarView();




                }
                break;
            case R.id.suggestionid:
                Intent opensuggestion = new Intent(MainActivity.this,SuggestionActivity.class);
                startActivity(opensuggestion);
                break;

            case R.id.aboutbtn:
                Intent openabout = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(openabout);
                break;
            case R.id.contactid:
                Intent openContact = new Intent(MainActivity.this,ContactActivity.class);
                startActivity(openContact);

                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.exitid:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);


    }


    public void showDialogagree(Activity activity){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.aggrementcontent);

//        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
//        text.setText(msg);

        Button dialogButton = (Button) dialog.findViewById(R.id.agreebtn);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }



    public void SnackbarView(){

        Snackbar snackbar = Snackbar
                .make(rl, "No internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


//                                    Snackbar snackbar1 = Snackbar.make(rl, "Message is restored!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();
                    }
                });

        snackbar.show();
    }
}
