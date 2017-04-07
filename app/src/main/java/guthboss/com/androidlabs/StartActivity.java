package guthboss.com.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button b1 = (Button)findViewById(R.id.button);
        Button startChat = (Button) findViewById(R.id.start_chat);
        Button weather = (Button) findViewById(R.id.weather_button);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),WeatherForecast.class);
                Log.i("Leaving","Leaving Start Activity heading to Weather Forecast");
                startActivity(startIntent);
            }
        });
        startChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("ACTIVITY_NAME","User clicked Start Chat");
                Intent startIntent  = new Intent(getApplicationContext(),chatBox.class);
                Log.i("Leaving","Start Activity*************");
                startActivity(startIntent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent startIntent  = new Intent(getApplicationContext(),ListItemsActivity.class);
                startActivityForResult(startIntent,5);

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
        SharedPreferences sharedPreferences = getSharedPreferences("Log Info", Context.MODE_PRIVATE);
        super.onStart();
        Log.i(ACTIVITY_NAME,"In onStart");
        Log.i("Saved Content",sharedPreferences.getString("email",""));

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
       public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        Log.i("In result","");
        if(requestCode == 5)
        {
            Log.i(ACTIVITY_NAME,"Returned to StartActivity.onActivityResult");
        }
        if(responseCode == Activity.RESULT_OK)
        {
            String messagePassed = data.getStringExtra("Response");
            Toast toast = Toast.makeText(getApplicationContext(),messagePassed,Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
