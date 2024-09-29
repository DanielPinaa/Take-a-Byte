package es.upm.etsiinf.proyectoPMD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ReviewsDataSource {
    private SQLiteDatabase database;
    private ReviewsDBHelper dbHelper;

    public ReviewsDataSource(Context context) {
        dbHelper = new ReviewsDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }

    // Insertar una nueva reseña
    public long insertReview(Review review) {
        ContentValues values = new ContentValues();
        values.put(ReviewsDBHelper.COLUMN_TITULO, review.getTitulo());
        values.put(ReviewsDBHelper.COLUMN_DESCRIPCION, review.getDescripcion());
        values.put(ReviewsDBHelper.COLUMN_VALORACION, review.getValoracion());

        return database.insert(ReviewsDBHelper.TABLE_REVIEWS, null, values);
    }

    // Obtener todas las reseñas
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<Review>();

        Cursor cursor = database.query(ReviewsDBHelper.TABLE_REVIEWS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Review review = cursorToReview(cursor);
            reviews.add(review);
            cursor.moveToNext();
        }
        cursor.close();
        return reviews;
    }

    private Review cursorToReview(Cursor cursor) {
        Review review = new Review(
                cursor.getString(cursor.getColumnIndex(ReviewsDBHelper.COLUMN_TITULO)),
                cursor.getString(cursor.getColumnIndex(ReviewsDBHelper.COLUMN_DESCRIPCION)),
                cursor.getFloat(cursor.getColumnIndex(ReviewsDBHelper.COLUMN_VALORACION))
        );
        review.setId(cursor.getInt(cursor.getColumnIndex(ReviewsDBHelper.COLUMN_ID)));
        return review;
    }

}
