package guthboss.com.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        final ChatAdapter messageAdapter =  new ChatAdapter(this);
        listView.setAdapter(messageAdapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chat.add(chatbox.getText().toString());
                Log.i("ChatBox:",chat.toString());
                messageAdapter.notifyDataSetChanged();
                chatbox.setText("");
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String>
    {
        public ChatAdapter(Context ctx)
        {
               super(ctx,0);

        }
        @Override
        public int getCount()
        {
           return chat.size();
        }

        @Override
        public String getItem(int position)
        {
            return chat.get(position);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View result = null;
            LayoutInflater inflater = chatBox.this.getLayoutInflater();
            if(position % 2 == 0)
            {
                    result = inflater.inflate(R.layout.chat_row_incoming,null);
            }
            else
            {
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }
            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));

            return result;//Change this in step 9
        }

    }
}


