package utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.File;

public class CucumberResultMerger {

    public static void main(String[] args) throws Exception {
        merge();
        System.out.println("✅ Cucumber results merged successfully");
    }
    public static void merge() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode finalResult = mapper.createArrayNode();

        File main = new File("target/cucumber.json");
        File rerun = new File("target/cucumber-rerun.json");

        if (main.exists()) {
            finalResult.addAll((ArrayNode) mapper.readTree(main));
        }

        if (rerun.exists()) {
            finalResult.addAll((ArrayNode) mapper.readTree(rerun));
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("target/cucumber-final.json"), finalResult);
    }
}

