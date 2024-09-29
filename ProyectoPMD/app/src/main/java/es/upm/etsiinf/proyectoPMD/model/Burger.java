package es.upm.etsiinf.proyectoPMD.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Burger {

    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("restaurantChain")
    private String restaurantChain;
    @SerializedName("image")
    private String image;
    @SerializedName("imageType")
    private String imageType;

    @SerializedName("nutrition")
    private Nutrition nutrition;


    //para descargar la imagen vamos a crear un atributo bitmap
    private Bitmap imageBmp;

    public Burger(int id, String title, String restaurantChain, String image, String imageType, Nutrition nutrition, Bitmap imageBmp) {
        this.id = id;
        this.title = title;
        this.restaurantChain = restaurantChain;
        this.image = image;
        this.imageType = imageType;
        this.nutrition = nutrition;
        this.imageBmp = imageBmp;
    }

    public Bitmap getImageBmp(){
        return imageBmp;
    }
    public void setImageBmp(Bitmap imageBmp){
        this.imageBmp=imageBmp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRestaurantChain() {
        return restaurantChain;
    }

    public void setRestaurantChain(String restaurantChain) {
        this.restaurantChain = restaurantChain;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }


    public static  class Nutrition{
        @SerializedName("nutrients")
        private List<Nutrient> nutrients;

        public Nutrition(List<Nutrient> nutrients) {
            this.nutrients = nutrients;
        }

        public List<Nutrient> getNutrients() {
            return nutrients;
        }

        public void setNutrients(List<Nutrient> nutrients) {
            this.nutrients = nutrients;
        }


        public String toString() {
            if (nutrients != null && !nutrients.isEmpty()) {
                String res = "";
                for (Nutrient nutrient : nutrients) {
                    res = res+"•"+nutrient.getName()+"\n";
                }
                return res.toString().trim();
            } else {
                return "No nutrients available";
            }
        }
    }

    public static class Nutrient{
        @SerializedName("name")
        private String name;

        public Nutrient(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}