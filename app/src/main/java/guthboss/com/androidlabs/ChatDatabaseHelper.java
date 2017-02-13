package guthboss.com.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by guthr_000 on 2017-02-12.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "Chats.db";
    private static final String DATABASE_CREATE = "CREATE TABLE integer primary key autoincrement text not null";
    private static final int VERSION_NUM = 1;
    ChatDatabaseHelper(Context ctx)
    {

        super(ctx,DATABASE_NAME,null,VERSION_NUM);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS");
        onCreate(db);
    }

}
