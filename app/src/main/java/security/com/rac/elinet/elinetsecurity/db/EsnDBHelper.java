package security.com.rac.elinet.elinetsecurity.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import security.com.rac.elinet.elinetsecurity.Util.Utility;

/**
 * Created by rac on 01/11/17.
 */

public class EsnDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Ens.db";
    private static final int DATABASE_VERSION = 2;
    public static final String RECORDING_TABLE_NAME = "recording";
    public static final String RECORDING_COLUMN_ID = "_id";
    public static final String RECORDING_COLUMN_IMG = "img";
    public static final String RECORDING_COLUMN_lat = "lat";
    public static final String RECORDING_COLUMN_LON = "lon";
    public static final String RECORDING_COLUMN_BATTERY = "battery";
    public static final String CREATED_AT = "created_at";

    private static final String CREATE_TABLE_RECORDINGG = "CREATE TABLE "
            + RECORDING_TABLE_NAME + "(" + RECORDING_COLUMN_ID + " INTEGER PRIMARY KEY,"
            + RECORDING_COLUMN_IMG + " TEXT,"
            + RECORDING_COLUMN_lat + " TEXT,"
            + RECORDING_COLUMN_LON + " TEXT,"
            + RECORDING_COLUMN_BATTERY + " TEXT,"
            + CREATED_AT + " DATETIME"
            + ")";

    public EsnDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RECORDINGG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RECORDINGG);
        onCreate(db);
    }

    public boolean insertRecording(String img, Double lat, Double lon, String battery) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECORDING_COLUMN_IMG, img);
        contentValues.put(RECORDING_COLUMN_lat, lat.toString());
        contentValues.put(RECORDING_COLUMN_LON, lon.toString());
        contentValues.put(RECORDING_COLUMN_BATTERY, battery);
        db.insert(RECORDING_TABLE_NAME, null, contentValues);

        return true;
    }
}
