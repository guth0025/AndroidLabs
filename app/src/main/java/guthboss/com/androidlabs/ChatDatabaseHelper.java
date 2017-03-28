package guthboss.com.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guthr_000 on 2017-02-12.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "chat";
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "message";
    private static  final String DATABASE_NAME = "Chats.db";
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME +"("+KEY_ID+" integer primary key autoincrement, "+ KEY_MESSAGE+" text not null)";
    private static final int VERSION_NUM = 1;
    ChatDatabaseHelper(Context ctx)
    {

        super(ctx,DATABASE_NAME,null,VERSION_NUM);
        System.out.println("Creating DB");
    }

    public void onCreate(SQLiteDatabase db)
    {
        System.out.println("Creating DB");
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

}
