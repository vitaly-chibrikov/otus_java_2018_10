package ru.otus.l091.simpleJson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by tully.
 * Updated by sergey
 */
public class DemoSimpleJson {
    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("current dir: " + System.getProperty("user.dir"));
        JSONObject jsonObjectIn = read();
        System.out.println("jsonObjectIn:" + jsonObjectIn);

        JSONObject jsonObjectCreated = create();
        System.out.println("jsonObjectCreated:" + jsonObjectCreated);

        navigateTree(jsonObjectCreated, "root");
    }

    private static JSONObject read() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse((new FileReader("jsondata.txt")));
    }

    @SuppressWarnings("unchecked")
    private static JSONObject create() {
        JSONObject root = new JSONObject();
        root.put("Key23", "Value42");
        JSONObject address = new JSONObject();
        address.put("Street", "Test");
        root.put("address", address);
        return root;
    }

    @SuppressWarnings("unchecked")
    private static void navigateTree(Object aware, String key) {
        // System.out.println(key + ": " + aware);

        String awareClassName = aware.getClass().getSimpleName();
        switch (awareClassName) {
            case "JSONArray":
                JSONArray array = (JSONArray) aware;
                array.forEach(element -> navigateTree(element, "array_element"));
                break;
            case "JSONObject":
                JSONObject jsonObject = (JSONObject) aware;
                jsonObject.entrySet().forEach(element -> navigateTree(element, "set_element"));
                break;
            case "Node":
                Map.Entry entry = (Map.Entry) aware;
                navigateTree(entry.getValue(), entry.getKey().toString());
                break;
            default:
                System.out.println(key + ": " + aware);

        }
    }
}
