package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class SaveLoad {
    private static final Path SAVE_PATH = Path.of("save.json");

    public static void put(String key, String value) {
        Map<String, String> data = loadAll();
        data.put(key, value);
        writeAll(data);
    }

    public static String get(String key) {
        Map<String, String> data = loadAll();
        return data.get(key);
    }

    public static boolean fileMissingOrEmpty(String filename) {
        Path path = Path.of(filename);

        try {
            return !Files.exists(path) || Files.size(path) == 0;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> loadAll() {
        if (!Files.exists(SAVE_PATH)) {
            return new LinkedHashMap<>();
        }

        try {
            String json = Files.readString(SAVE_PATH).trim();
            return parseSimpleJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeAll(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");

        int count = 0;
        for (String key : data.keySet()) {
            sb.append("  \"")
                    .append(escapeJson(key))
                    .append("\": \"")
                    .append(escapeJson(data.get(key)))
                    .append("\"");

            count++;
            if (count < data.size()) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append("}");

        try {
            Files.writeString(SAVE_PATH, sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> parseSimpleJson(String json) {
        Map<String, String> map = new LinkedHashMap<>();

        if (json.isEmpty() || json.equals("{}")) {
            return map;
        }

        json = json.trim();

        if (json.charAt(0) != '{' || json.charAt(json.length() - 1) != '}') {
            throw new RuntimeException("Invalid JSON format.");
        }

        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) {
            return map;
        }

        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] kv = pair.split(":", 2);

            String key = clean(kv[0]);
            String value = clean(kv[1]);

            map.put(unescapeJson(key), unescapeJson(value));
        }

        return map;
    }

    private static String clean(String s) {
        s = s.trim();

        if (s.startsWith("\"")) {
            s = s.substring(1);
        }

        if (s.endsWith("\"")) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private static String unescapeJson(String s) {
        return s.replace("\\\"", "\"")
                .replace("\\\\", "\\");
    }
}