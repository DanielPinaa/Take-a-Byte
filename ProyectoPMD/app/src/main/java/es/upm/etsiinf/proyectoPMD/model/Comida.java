package es.upm.etsiinf.proyectoPMD.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

public class Comida {
    @SerializedName("location")
    private String location;
    @SerializedName("image")
    private String imageName;

    //para descragar la imagen vamos a crear un atributo bitmap
    private Bitmap imageBmp;
    public Bitmap getImageBmp() {
        return imageBmp;
    }

    public void setImageBmp(Bitmap imageBmp) {
        this.imageBmp = imageBmp;
    }

    public Comida(String name, int distance, int gravity, int diameter, String imageName) {
        this.location = location;
        this.imageName = imageName;
    }


    public String getLocation() {
        return location;
    }

    public void setName(String location) {
        this.location = location;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "location='" + location + '\'' +
                ", imageName=" + imageName +
                '}';
    }
}

