package api;

import dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {
// Выполнен.
    @GET("products/{id}")
    Call<Product> getProductById(@Path("id") int id);
// Выполнен.
    @GET("products")
    Call<ResponseBody> getProducts();
// Выполнен.
    @POST("products")
    Call<Product> createProduct(@Body Product createProductRequest);

// Выполнен
    @PUT("products")
    Call<Product> modifyProduct(@Body Product modifyProductRequest);

// Выполнен
    @DELETE("products/{id}")
    Call<ResponseBody> deleteProduct(@Path("id") int id);

}
