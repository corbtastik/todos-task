package io.corbs;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;

import java.io.IOException;

public class TodosTask implements CommandLineRunner {

    private static final Log logger = LogFactory.getLog(TodosTask.class);

    private final ObjectMapper objectMapper;

    private final TodosTaskProperties properties;

    private final OkHttpClient client;

    public TodosTask (TodosTaskProperties properties, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.client = new OkHttpClient();
        logger.info("Todos Task Booting with endpoint: "
                + properties.getEndpoint() + ", limit: " + properties.getLimit());
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting Todos Task, generating " + properties.getLimit() + " todos.");
        for(int i = 0; i < properties.getLimit(); i++) {
            String title;
            if(i % 10 == 0) {
                title = RandomStringUtils.randomAlphanumeric(8) + " #howdy #amigo";
            } else {
                title = RandomStringUtils.randomAlphanumeric(8);
            }
            logger.info(post(properties.getEndpoint() + "/", Todo.builder().id(i).completed(false).title(title).build()));
        }
        logger.info("Todos Task complete.");
    }

    private String post(String url, Todo todo) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                objectMapper.writeValueAsString(todo));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}