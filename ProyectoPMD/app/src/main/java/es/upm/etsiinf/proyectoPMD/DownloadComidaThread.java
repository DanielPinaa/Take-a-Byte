package es.upm.etsiinf.proyectoPMD;


import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import es.upm.etsiinf.proyectoPMD.model.Comida;
import es.upm.etsiinf.proyectoPMD.util.NetUtils;


public class DownloadComidaThread implements Runnable{

    private MirarCartaActivity ma;
    public DownloadComidaThread(MirarCartaActivity ma){
        this.ma = ma;
    }
    private static final String TAG = DownloadComidaThread.class.getName();
    @Override
    public void run() {
        //Preparamos el interfaz
        ma.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ma.startDownload();
            }
        });
        //Realizamos la tarea
        List<Comida> cls=null;
        try {
            Thread.sleep(500);
            String githubRawUrl = "https://raw.githubusercontent.com/luciaguti/JSON_PMD/main/comida.php";
            String jsonPlanetList = NetUtils.getURLText(githubRawUrl);

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy hh:mm a");
            Gson gson = gsonBuilder.create();

            cls = Arrays.asList(gson.fromJson(jsonPlanetList, Comida[].class));
            //Pedimos las imagenes de cada opción de comida
            for (Comida comida : cls){
                String imagenADescargar = comida.getImageName();

                Bitmap imagenPlaneta = NetUtils.getURLImage(imagenADescargar);
                comida.setImageBmp(imagenPlaneta);
            }
            Log.i(TAG,jsonPlanetList);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        final List<Comida> res = cls;
        //devolvemos los resultados al interfaz
        ma.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ma.finishDownload(res);
            }
        });

    }
}

