package es.upm.etsiinf.proyectoPMD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaPpal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallappal);

        Button btnLogout = (Button) findViewById(R.id.btnlogout);
        Button btnResenas = (Button) findViewById(R.id.btnResenas);
        Button btnReservarMesa = (Button) findViewById(R.id.btnReservarMesa);
        Button btnContactanos = (Button) findViewById(R.id.btnContactanos);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        btnResenas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irResenas(); }
        });

        btnReservarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irReservarMesa(); }
        });

        btnContactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irContactanos() ;}
        });
    }

    private void logOut() {
        SharedPreferences sp = getSharedPreferences("prefs_login", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("active", "false");

        editor.apply();

        startActivity(new Intent(this, Login.class));
        finish();
    }

    private void irResenas() {
        startActivity(new Intent(this, Resenas.class));
        finish();
    }

    private void irReservarMesa() {
        startActivity(new Intent(this, ReservarMesa.class));
        finish();
    }

    public void mirarCarta(View view){
        startActivity(new Intent(this,MirarCartaActivity.class));
        finish();
    }

    public void irContactanos(){
        startActivity(new Intent(this,Contactar.class));
        finish();
    }
}