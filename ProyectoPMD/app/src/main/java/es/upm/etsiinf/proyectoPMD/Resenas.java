package es.upm.etsiinf.proyectoPMD;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.util.JobUtil;

public class Resenas extends AppCompatActivity {

    private ReviewsDataSource dataSource;
    private EditText etTitulo, etDescripcion, etNota;
    private ListView listView;
    private ReviewsAdapter reviewsAdapter;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenas);

        // Inicializar la fuente de datos
        dataSource = new ReviewsDataSource(this);
        dataSource.open();

        // Obtener referencias de los elementos de la interfaz
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etNota = findViewById(R.id.etNota);
        listView = findViewById(R.id.listView);

        // Crear un ReviewsAdapter y asignarlo a tu ListView
        List<Review> listaResenas = dataSource.getAllReviews();
        reviewsAdapter = new ReviewsAdapter(this, listaResenas);
        listView.setAdapter(reviewsAdapter);

        JobUtil.scheduleJob(this);

        registerBroadcastReceiver();

        // Botón para agregar reseña
        Button btnAgregarResena = findViewById(R.id.btnAgregarResena);
        btnAgregarResena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarResena();
            }
        });
    }

    private void agregarResena() {
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        float nota;

        try {
            nota = Float.parseFloat(etNota.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese una nota válida", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear una nueva reseña y guardarla en la base de datos
        Review nuevaResena = new Review(titulo, descripcion, nota);
        long id = dataSource.insertReview(nuevaResena);

        Toast.makeText(this, "Reseña agregada con ID: " + id, Toast.LENGTH_SHORT).show();

        // Limpiar campos después de agregar la reseña
        etTitulo.setText("");
        etDescripcion.setText("");
        etNota.setText("");

        // Actualizar la lista de reseñas en el adapter
    }

    public void actualizarListaResenas() {
        // Obtener todas las reseñas de la base de datos
        List<Review> listaResenas = dataSource.getAllReviews();

        // Actualizar el adapter con la nueva lista de reseñas
        reviewsAdapter = new ReviewsAdapter(this, listaResenas);
        listView.setAdapter(reviewsAdapter);
        Log.i("Reseñas","Reseñas actualizadas");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la fuente de datos al destruir la actividad
        dataSource.close();
        unregisterReceiver(broadcastReceiver);
    }

    public void Volver (View view){
        startActivity(new Intent(this,PantallaPpal.class));
        //finish(); ///RECORDATORIO LUCIA; PONER FINISH EN LOS DE LA CARTA
    }

    private void registerBroadcastReceiver() {
        Log.i("hola","hola");
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                actualizarListaResenas();
            }
        };

        IntentFilter intentFilter = new IntentFilter("reseñas_actualizadas");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }
}
