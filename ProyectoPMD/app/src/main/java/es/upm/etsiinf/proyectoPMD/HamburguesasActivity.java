package es.upm.etsiinf.proyectoPMD;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.Interfaz.ApiService;
import es.upm.etsiinf.proyectoPMD.model.Burger;
import es.upm.etsiinf.proyectoPMD.util.NetUtils;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HamburguesasActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://api.spoonacular.com/";

    private static final String API_KEY = "a07cbde6352e464985ff0f9121abf7a9";
    private static final String API_KEY2="cc8823b33be1496aaea5cf882bfae146";
    private static final String API_KEY3="6f71c00a53c94c05a466b27fbe2fa5aa";
    private String query;
    List<Burger> ingredientes;
    Button btnCerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hamburguesas);
        Intent intent = getIntent();
        if(intent!=null){
            query=intent.getStringExtra("query");
        }
        new APICallTask().execute();

        ListView list = findViewById(R.id.main_list_burger);
        btnCerrar= findViewById(R.id.close_fragment);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                switch (pos){
                    case 0:
                        ((TextView)findViewById(R.id.textView2)).setText(ingredientes.get(0).getNutrition().toString());
                        findViewById(R.id.contenedorFragment).setVisibility(View.VISIBLE);
                        btnCerrar.setVisibility(View.VISIBLE);
                        break;
                    case 1:

                        ((TextView)findViewById(R.id.textView2)).setText(ingredientes.get(1).getNutrition().toString());
                        findViewById(R.id.contenedorFragment).setVisibility(View.VISIBLE);
                        btnCerrar.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        ((TextView)findViewById(R.id.textView2)).setText(ingredientes.get(2).getNutrition().toString());
                        findViewById(R.id.contenedorFragment).setVisibility(View.VISIBLE);
                        btnCerrar.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        ((TextView)findViewById(R.id.textView2)).setText(ingredientes.get(3).getNutrition().toString());
                        findViewById(R.id.contenedorFragment).setVisibility(View.VISIBLE);
                        btnCerrar.setVisibility(View.VISIBLE);
                    case 4:
                        ((TextView)findViewById(R.id.textView2)).setText(ingredientes.get(4).getNutrition().toString());
                        findViewById(R.id.contenedorFragment).setVisibility(View.VISIBLE);
                        btnCerrar.setVisibility(View.VISIBLE);
                        break;
                    default:break;
                }
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.contenedorFragment).setVisibility(View.INVISIBLE);
                btnCerrar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private class APICallTask extends AsyncTask<Void, Void, List<Burger>> {

        @Override
        protected List<Burger> doInBackground(Void... voids) {
            try {
                // Vamos a conectarnos con la API de manera síncrona
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                ApiService apiService = retrofit.create(ApiService.class);

                // Realizamos la llamada de manera síncrona
                Response<ApiResponse> response = apiService.getIngredients(query, 5, API_KEY3,true).execute();


                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        ingredientes = apiResponse.getResults();
                        if (ingredientes != null) {
                            try {
                                for (Burger burger : ingredientes) {
                                    String imagenADescargar = burger.getImage();
                                    Bitmap imagenBurger = NetUtils.getURLImage(imagenADescargar);
                                    burger.setImageBmp(imagenBurger);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("La lista de ingredientes es nula");
                        }
                    }
                } else {
                    // Manejar errores de respuesta
                    System.out.println("Error en la llamada a la API");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return ingredientes;
        }

        @Override
        protected void onPostExecute(List<Burger> burgers) {
            // Actualizar la interfaz de usuario después de la descarga de imágenes
            if(burgers!=null){
                BurgerAdapter adapter = new BurgerAdapter(HamburguesasActivity.this, burgers);
                ((ListView) findViewById(R.id.main_list_burger)).setAdapter(adapter);
            }

        }
    }

    public void Volver(View view){
        Intent anterior = new Intent(this,MirarCartaActivity.class);
        startActivity(anterior);
    }

}