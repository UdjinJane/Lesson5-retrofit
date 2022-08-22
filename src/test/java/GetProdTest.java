import api.ProductService;
import com.github.javafaker.Faker;
import dto.Product;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import retrofit2.Response;
import utils.RetrofitUtils;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;



public class GetProdTest extends Controller{

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }


    @Test
    @DisplayName("Тест 1. Листинг всех продуктов во всех категориях")
    @Order(1)

    void getAllProdInAllCategoryTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts()
                .execute();
        // Проверка что респонс прошел.
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        // Проверка боди на данные через мат ассерт
        MatcherAssert.assertThat(response.body(), not(nullValue()));
        MatcherAssert.assertThat(response.errorBody(), nullValue());
        MatcherAssert.assertThat(response.isSuccessful(), CoreMatchers.is(true));

        // String result = printResponse(response);
        //  MatcherAssert.assertThat(result, not(nullValue()));

    }

    @SneakyThrows
    @ParameterizedTest
    @DisplayName("Тест 2. GET item by Id positive")
    @Description("Тест 2. GET item by Id positive")
    @CsvSource({"2", "4"})
    @Issue("localhost")
    @Order(2)
    void getProductByValidIdPositive(Integer parameter) throws IOException {
        Response<Product> response = productService.getProductById(parameter).execute();
        // Пример проверки тела запроса на даннные / ошибки
        MatcherAssert.assertThat(response.body(), not(nullValue()));
        MatcherAssert.assertThat(response.errorBody(), nullValue());
        MatcherAssert.assertThat(response.isSuccessful(), CoreMatchers.is(true));


        // String result = printResponse(response);


    }

}




