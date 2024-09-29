package es.upm.etsiinf.proyectoPMD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ReviewsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reviews.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla de reseñas
    public static final String TABLE_REVIEWS = "reviews";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_VALORACION = "valoracion";

    // Sentencia SQL para la creación de la tabla
    private static final String DATABASE_CREATE = "create table "
            + TABLE_REVIEWS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITULO
            + " text not null, " + COLUMN_DESCRIPCION
            + " text not null, " + COLUMN_VALORACION
            + " real not null);";

    public ReviewsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ReviewsDBHelper.class.getName(),
                "Upgrading database from version" + oldVersion + "to" +
                        newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }


}


