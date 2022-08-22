import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Product;
import retrofit2.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {

    static String printResponse(Response<Product> response) throws IOException {

        String result;
        if (response.body() != null) {
            //Объект для хранения строки
            StringWriter writer = new StringWriter();
            //Объект Jackson, который выполняет сериализацию
            ObjectMapper mapper = new ObjectMapper();

            //Сама сериализация
                result = (String) mapper.writeValueAsString(response);

                //Сама сериализация: 1-куда, 2-что
                // mapper.serialize(writer, response.body());
                //преобразовываем все записанное во StringWriter в строку
            // result = writer.toString();
            System.out.println("Печать. Результат запроса:\n"+ result);
            } else {
                result = response.errorBody().string();
                System.out.println("Печать. Результат запроса c ошибкой:\n" + result);
            }
            return result;
        }

    public Product modify(Integer id, String title, Integer price, String categoryTitle){
        return new Product()
                .withId(id)
                .withTitle(title)
                .withPrice(price)
                .withCategoryTitle(categoryTitle);
    }

    public String generateStringFromSource (String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    }

