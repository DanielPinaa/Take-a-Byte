package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BebidasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);
    }

    public void Volver(View view){
        Intent volver = new Intent(this,MirarCartaActivity.class);
        startActivity(volver);
    }
}