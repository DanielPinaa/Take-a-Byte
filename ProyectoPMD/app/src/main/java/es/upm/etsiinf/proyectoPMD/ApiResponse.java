package es.upm.etsiinf.proyectoPMD;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import es.upm.etsiinf.proyectoPMD.model.Burger;

public class ApiResponse {
    @SerializedName("menuItems")
    private List<Burger> menuItems;

    public List<Burger> getResults(){
        return menuItems;
    }
}
