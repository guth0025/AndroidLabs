package guthboss.com.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class chatBox extends AppCompatActivity {
    ListView listView;
    Button send;
    EditText chatbox;
    ArrayList<String> chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_box);
        listView = (ListView) findViewById(R.id.list);
        send = (Button) findViewById(R.id.send);
        chatbox = (EditText) findViewById(R.id.chatbox);
        chat = new ArrayList<String>();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chat.add(chatbox.getText().toString());
                Log.i("ChatBox:",chat.toString());
            }
        });
    }

    private static class ChatAdapter extends ArrayAdapter<String>
    {
        public ChatAdapter(Context ctx)
        {
               super(ctx,0);

        }
        @Override
        public int getCount()
        {
           // return listView.size();
        }

    }
}


