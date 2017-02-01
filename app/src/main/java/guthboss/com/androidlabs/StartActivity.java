package guthboss.com.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent startIntent  = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(startIntent);
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
