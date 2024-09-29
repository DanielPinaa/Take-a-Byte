package es.upm.etsiinf.proyectoPMD.Interfaz;

import es.upm.etsiinf.proyectoPMD.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("food/menuItems/search")
    Call<ApiResponse> getIngredients(
            @Query("query") String query,
            @Query("number") int number,
            @Query("apiKey") String apiKey,
            @Query("addMenuItemInformation") boolean addMenuItemInformation
    ) ;
}
