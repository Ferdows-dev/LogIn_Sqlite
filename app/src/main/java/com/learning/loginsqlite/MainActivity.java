package com.learning.loginsqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button loginButton , signUpButton,changeLanguage;
    boolean lang_selected = true;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        loginButton = findViewById(R.id.loginbtn);
        signUpButton = findViewById(R.id.signupbtn);
        changeLanguage = findViewById(R.id.changeLanguagebtn);

        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] language = {"English","বাংলা"};
                int checkedItem;
                if (lang_selected){
                    checkedItem = 0;
                }else {
                    checkedItem = 1;
                }
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a language")
                        .setSingleChoiceItems(language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeLanguage.setText(language[which]);
                                if (language[which].equals("বাংলা")){

                                    context = LocalHelper.setLocale(MainActivity.this, "bn");
                                    resources = context.getResources();
                                    loginButton.setText(resources.getString(R.string.login));
                                    signUpButton.setText(resources.getString(R.string.signup));
                                    changeLanguage.setText(resources.getString(R.string.changelanguage));
                                } else if (language[which].equals("English")){

                                    context = LocalHelper.setLocale(MainActivity.this, "En");
                                    resources = context.getResources();
                                    loginButton.setText(resources.getString(R.string.login));
                                    signUpButton.setText(resources.getString(R.string.signup));
                                    changeLanguage.setText(resources.getString(R.string.changelanguage));


                                }

                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , LoginActivity.class));
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , SignUpActivity.class));
            }
        });

    }



}
