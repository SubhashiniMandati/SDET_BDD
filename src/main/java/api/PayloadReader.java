package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class PayloadReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode read(String env, String fileName) {
        try (InputStream is =
                     PayloadReader.class
                             .getClassLoader()
                             .getResourceAsStream(
                                     "payloads/" + env + "/" + fileName)) {

            if (is == null) {
                throw new RuntimeException("Payload file not found");
            }

            return mapper.readTree(is);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read payload", e);
        }
    }
}

