package guthboss.com.androidlabs;

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
        email = (EditText) findViewById(R.id.email);
        Button login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.getString("DefaultEmail",email.getText().toString());
                Log.i("Email",email.getText().toString());
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
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart");
        sharedPref.getString("DefaultEmail","email@domain.com");

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
