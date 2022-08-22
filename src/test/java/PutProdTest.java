import api.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import dto.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import utils.RetrofitUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;


public class PutProdTest extends Controller {
    static ProductService productService;
    // Product product = null;
    Faker faker = new Faker();
    int id, price;
    String title, categoryTitle;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @SneakyThrows
    @Test
    @DisplayName("Тест 4. Изменяется цена продукта с ID=1")
    @Description("Тест 4. Изменяется цена продукта с ID=1")
    @Link("http://localhost:8189/market/swagger-ui.html#/product-controller/modifyProductUsingPUT")
    @Issue("localhost")
    @Tag("modifyIdProductInFoodCategoryTest")
    void modifyIdProductInFoodCategoryTest() throws IOException {
        // Запрос товара с ID=1:

        Response<Product> response = productService.getProductById(1).execute();
        id = response.body().getId();               // сохранение ID продукта
        MatcherAssert.assertThat(response.isSuccessful(), CoreMatchers.is(true));

        // Печать результата запроса.
        String myResp = printResponse(response);

        // Из JSON-файла в класс
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(response.toString());

        // Product productReader = mapper.readValue (reader, Product.class);
        // Integer newID = (int) (Math.random() * 1000);
        // Изменение price продукта:
        // Integer newID = 1000;
        // product = modify(newID,
        //        productReader.getTitle(),
        //        productReader.getPrice(),
        //        productReader.getCategoryTitle());

        Product productFromFile =  mapper.readValue(new File("src/main/resources/modifyBread.json"), Product.class);

        // Запрос на запись обновления
         response = productService.modifyProduct(productFromFile).execute();
         MatcherAssert.assertThat(response.isSuccessful(), CoreMatchers.is(true));

        // Получение объекта с обновлёнными данными
         response = productService.getProductById(id).execute();
         MatcherAssert.assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }



}
