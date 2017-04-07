package guthboss.com.androidlabs;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherForecast extends AppCompatActivity {
    String dataURL = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
    String min,max,current;
    ProgressBar progBar;
    TextView minView,maxView,currentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
         progBar = (ProgressBar)findViewById(R.id.prog_bar);
        minView = (TextView)findViewById(R.id.min_temp);
        maxView = (TextView)findViewById(R.id.max_temp);
        currentView = (TextView)findViewById(R.id.current_temp);
        progBar.setVisibility(View.VISIBLE);
        progBar.setProgress(0);
        ForecastQuery forecast = new ForecastQuery();
        forecast.execute(dataURL);
    }

    public class ForecastQuery extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... params) {

                try{
                    URL url = new URL(params[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(conn.getInputStream(), null);
                    parser.nextTag();

                    readFeed(parser);
                }catch(Exception e)
                {
                    e.printStackTrace();
                }

                return "Finished";



        }

        protected void onPostExecute(String check)
        {
            if(check.equals("Finished"))
            {
                if(min != null)
                {
                    minView.setText("Min: "+ min);
                    maxView.setText("Max: "+max);
                    currentView.setText("Current: "+current);

                }
                else
                    {
                        Log.i("Empty","Min");
                    }
                progBar.setVisibility(View.INVISIBLE);
            }
        }

        private void readFeed(XmlPullParser parser) throws XmlPullParserException, IOException, InterruptedException {


            parser.require(XmlPullParser.START_TAG, null, null);
            Log.i("Entered","Read Feed");
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if(name != null)
                {
                    Log.i("Name:",name);
                }
                if(name.equals("temperature"))
                {
                    Log.i("Entered","Name equaled Temperature");
                    current = parser.getAttributeValue(null,"value");
                    publishProgress(25);//set progress bar 25%
                    Thread.sleep(1000);
                    min = parser.getAttributeValue(null,"min");
                    publishProgress(50);//set progress to 50%
                    Thread.sleep(1000);
                    max = parser.getAttributeValue(null,"max");
                    publishProgress(75);
                    Thread.sleep(1000);
                }
                else
                    {
                        parser.next();
                    }



            }
                Log.i("While","Ended");
        }

    }
}