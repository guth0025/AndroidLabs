package guthboss.com.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    SQLiteDatabase writeableDB;
    ChatDatabaseHelper db;
    /************************************* onCreate *****************************************/
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

        ContentValues values = new ContentValues();



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chat.add(chatbox.getText().toString());
               // messageAdapter.insertChat(chatbox.getText().toString());
                Log.i("ChatBox:",chat.toString());
                messageAdapter.notifyDataSetChanged();
                chatbox.setText("");
            }
        });
    }
    /************************************* CHAT ADAPTER ***********************************/
    private class ChatAdapter extends ArrayAdapter<String>
    {
        Context mCtx;
/*********************CHATADAPTER CONSTRUCTOR**************/
        public ChatAdapter(Context ctx)
        {
               super(ctx,0);
               this.mCtx = ctx;
        }

/*********************************************OPEN DB********************************************/
        public ChatAdapter open() throws SQLException
        {
            db = new ChatDatabaseHelper(mCtx);
            writeableDB = db.getWritableDatabase();
            return this;
        }
/**************************************** CLOSE DB ********************************************/
        public void close()
        {
            if(db != null)
            {
                db.close();
            }
        }
        /**************UPDATE DB**************************/
        public void update() throws SQLException
        {
            db = new ChatDatabaseHelper(mCtx);// open
            writeableDB = db.getWritableDatabase();
            db.onUpgrade(writeableDB,1,0);
        }

        /******************INSERT TO DB********************/
        public long insertChat(ContentValues initialValues)
        {
                return writeableDB.insertWithOnConflict(db.TABLE_NAME,null,initialValues,SQLiteDatabase.CONFLICT_IGNORE);
        }
        /*****************DELTE FROM DB********************/
        public boolean updateChat(int id, ContentValues newValues)
        {
            String[] selectionArgs = {String.valueOf(id)};
            return writeableDB.update(db.TABLE_NAME,newValues,db.KEY_ID+"=?",selectionArgs)>0;
        }
        /*****************UPDATEQUERY*********************/
        public boolean deleteChat(int id)
        {
            String[] selectionArgs = {String.valueOf(id)};
            return writeableDB.delete(db.TABLE_NAME,db.KEY_ID+"=?",selectionArgs)>0;
        }
        /************Grab existing chat********************/
        public Cursor getChat()
        {
            return writeableDB.query(db.TABLE_NAME,db.CHAT_FIELDS,null,null,null,null,null);

        }
        public String getChatFromCursor(Cursor cursor)
        {
            String chatMSG = new String();
            chatMSG = cursor.getString(cursor.getColumnIndex(db.KEY_MESSAGE));
            return chatMSG;
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


}


