package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.model.Comida;

public class MirarCartaActivity extends AppCompatActivity {
    ComidaAdapter adapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirarcarta);
        Thread th = new Thread(new DownloadComidaThread(MirarCartaActivity.this));
                th.start();

        ListView list = findViewById(R.id.main_list_burger);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                switch (posicion){
                    case 0:
                        //Se ha pulsado en bebidas
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","drinks");
                        startActivity(intent);
                        break;
                    case 1:
                        //Se ha pulsado en aperitivos
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","appetizers");
                        startActivity(intent);
                        break;
                    case 2:
                        //Se ha pulsado en ensaladas
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","salad");
                        startActivity(intent);

                        break;
                    case 3:
                        //Se ha pulsado en sandwich
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","sandwich");
                        startActivity(intent);
                        break;
                    case 4:
                        //Se ha pulsado en postres
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","dessert");
                        startActivity(intent);
                        break;
                    case 5:
                        //Se ha pulsado en Desayuno
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","breakfast");
                        startActivity(intent);
                        break;
                    case 6:
                        //Se ha pulsado en Menu
                        intent = new Intent(MirarCartaActivity.this,HamburguesasActivity.class);
                        intent.putExtra("query","menu");
                        startActivity(intent);
                        break;
                    default:break;
                }
            }
        });

    }

    //Método para el botón Anterior
    // Añadimos el método en el atributo del botón llamado 'onClick'
    public void Anterior(View view){
        Intent anterior = new Intent(this,PantallaPpal.class);
        startActivity(anterior);
    }
    public void startDownload(){
        findViewById(R.id.main_progressbar_list).setVisibility(View.VISIBLE);
    }
    public void finishDownload(List<Comida> results) {

        adapter = new ComidaAdapter(this,results);
        ((ListView)findViewById(R.id.main_list_burger)).setAdapter(adapter);

        findViewById(R.id.main_progressbar_list).setVisibility(View.INVISIBLE);
    }

}