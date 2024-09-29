package es.upm.etsiinf.proyectoPMD;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Contactar extends AppCompatActivity {

    protected static final String ATTR_COMENTARIO = "ESTO ES UN COMENTARIO";

    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactar);

        ImageView iv = findViewById(R.id.imagenBocata);
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), //Los contratos (cómo quiero que me devuelva el resultado)
                new ActivityResultCallback<ActivityResult>() {
                    @Override //Cuando el usuario abra una imagen se ejecuta este método
                    public void onActivityResult(ActivityResult result) {

                        if(result.getResultCode()== Activity.RESULT_OK){
                            //Abrimos la imagen
                            Uri fichero = result.getData().getData();
                            InputStream stream=null;
                            try {
                                stream = getContentResolver().openInputStream(fichero);
                                Bitmap bmp = BitmapFactory.decodeStream(stream);
                                iv.setImageBitmap(bmp);

                            }catch(FileNotFoundException e){
                                throw new RuntimeException(e);
                            }
                            String texto = getResources().getString(R.string.Se_ha_seleccionado_la_imagen);
                            //La mostramos en el ImageView
                            Toast.makeText(Contactar.this,
                                    texto,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //El usuario ha cancelado la selección
                            Toast.makeText(Contactar.this,
                                    getResources().getString(R.string.Se_ha_cancelado_la_seleccion_de_la_imagen),
                                    Toast.LENGTH_SHORT).show();
                            iv.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bocadillo_de_ternera_e3c0e511_1200x1200));
                        }
                    }
                }

        );
        Button btnSelImg = findViewById(R.id.seleccionarFoto);
        btnSelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");//Abre cualquier tipo de imagen
                launcher.launch(i);
            }
        });



        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(Contactar.this,
                    android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contactar.this,
                        new String[]{Manifest.permission.CAMERA},101);
            }
        }

        Button btnImg = findViewById(R.id.botonImagen);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent();
                    i.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(i);

                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

        Button enviarImagen = findViewById(R.id.botonFoto);
        ImageView image = findViewById(R.id.imagenBocata);
        image.buildDrawingCache();
        enviarImagen.setOnClickListener(new View.OnClickListener() {
            Bitmap bm = image.getDrawingCache();

            @Override
            public void onClick(View v) {
                // Obtener el Bitmap de la imagen
                image.buildDrawingCache();
                Bitmap bm = image.getDrawingCache();

                // Convertir el Bitmap en una URI
                Uri imageUri = getImageUri(bm);

                // Crear un Intent para compartir la imagen
                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_STREAM, imageUri);
                i.setType("image/jpeg");

                // Iniciar la actividad para compartir
                startActivity(Intent.createChooser(i, "Compartir imagen"));
            }
        });




        EditText comentario = findViewById(R.id.campoComentario);

        Button btnCmnt = findViewById(R.id.botonComentario);
        btnCmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coment = comentario.getText().toString();

                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_TEXT,coment);
                i.setType("text/plain");
                startActivity(i);
            }
        });
    }
    public void Volver(View view){
        Intent volver = new Intent(this,PantallaPpal.class);
        startActivity(volver);
    }


    private Uri getImageUri(Bitmap inBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), inBitmap, "Title", null);
        return Uri.parse(path);
    }

}
