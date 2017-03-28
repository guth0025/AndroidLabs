package guthboss.com.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class ChatWindow extends AppCompatActivity {
    ListView listView;
    Button send;
    EditText chatbox;
    ArrayList<String> chat;
    private String[] allColumns = {ChatDatabaseHelper.KEY_ID,ChatDatabaseHelper.KEY_MESSAGE};


    /************************************* onCreate *****************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat_box);

        ChatDatabaseHelper db = new ChatDatabaseHelper(this);

        SQLiteDatabase writeableDB = db.getWritableDatabase();

        setChat(db,writeableDB);

        listView = (ListView) findViewById(R.id.list);

        send = (Button) findViewById(R.id.send);

        chatbox = (EditText) findViewById(R.id.chatbox);

        chat = new ArrayList<String>();

        final ChatAdapter messageAdapter =  new ChatAdapter(this);

        listView.setAdapter(messageAdapter);

        ContentValues values = new ContentValues();



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
    /************************************* CHAT ADAPTER ***********************************/
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
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
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

            return result;
        }

    }
    /*********************************set Chat ***********************************************/
    private void setChat(ChatDatabaseHelper db,SQLiteDatabase writeableDB)
    {
        Cursor cursor = writeableDB.query(db.TABLE_NAME,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Log.i("ChatWindow","SQL MESSAGE:" + cursor.getString( cursor.getColumnIndex( ChatDatabaseHelper.KEY_MESSAGE)));
            chat.add(cursor.getString(1));
            System.out.println(cursor.getString(1));
        }
    }

}


