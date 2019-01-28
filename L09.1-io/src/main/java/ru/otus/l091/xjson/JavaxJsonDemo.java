package ru.otus.l091.xjson;



import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

/**
 * Created by tully.
 */
public class JavaxJsonDemo {
    public static void main(String[] args) throws FileNotFoundException {
        JsonStructure jsonIn = read();
        System.out.println("jsonIn:" + jsonIn);
        System.out.println("******");

        JsonStructure jsonCreated = create();
        System.out.println("jsonCreated:" + jsonCreated);
        System.out.println("******");

        navigateTree(jsonCreated, "base");

        String jsonData = writeToString((JsonObject) jsonCreated);
        System.out.println("jsonData:" + jsonData);
    }

    private static JsonStructure read() throws FileNotFoundException {
        JsonReader reader = Json.createReader(new FileReader("jsondata.txt"));
        return reader.read();
    }

    private static JsonStructure create() {
        return Json.createObjectBuilder()
                .add("firstName", "Duke")
                .add("age", 28)
                .add("streetAddress", "100 Internet Dr")
                .add("phoneNumbers", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "home")
                                .add("number", "222-222-2222")))
                .build();
    }

    private static void navigateTree(JsonValue tree, String key) {
        if (key != null)
            System.out.print("Key " + key + ": ");
        switch (tree.getValueType()) {
            case OBJECT:
                System.out.println("OBJECT");
                JsonObject object = (JsonObject) tree;
                for (String name : object.keySet())
                    navigateTree(object.get(name), name);
                break;
            case ARRAY:
                System.out.println("ARRAY");
                JsonArray array = (JsonArray) tree;
                for (JsonValue val : array)
                    navigateTree(val, null);
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                System.out.println("STRING " + st.getString());
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                System.out.println("NUMBER " + num.toString());
                break;
            case TRUE:
            case FALSE:
            case NULL:
                System.out.println(tree.getValueType().toString());
                break;
        }
    }


    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }

        return stWriter.toString();
    }
}
