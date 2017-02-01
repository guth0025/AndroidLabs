package guthboss.com.androidlabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    SharedPreferences sharedPref;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("email",email.getText().toString());
                editor.apply();
                Log.i("Email",sharedPref.getString("email","").toString());
            }
        });
    }
    protected void onResume()
    {
        super.onResume();
        Log.i(ACTIVITY_NAME,"In onResume");
    }
    protected void onStart()
    {
        email = (EditText) findViewById(R.id.email);
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart");
        sharedPref = getSharedPreferences("Log Info",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("DefaultEmail","email@domain.com");
        editor.apply();

        if(sharedPref.getString("email","")=="")
        {
           String temp = sharedPref.getString("DefaultEmail","");
            Log.i("Temp:",temp);
            email.getText().clear();
            email.setText(temp);
        }
        else
        {
            String temp = sharedPref.getString("email","");
            Log.i("Temp:",temp);
            email.getText().clear();
            email.setText(temp);
        }

    }
    protected void onPause()
    {
        super.onPause();
        Log.i(ACTIVITY_NAME,"In onPause");
    }
    protected void onStop()
    {
        super.onStop();
        Log.i(ACTIVITY_NAME,"In onStop");
    }
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i(ACTIVITY_NAME,"In onDestroy");
    }

}
