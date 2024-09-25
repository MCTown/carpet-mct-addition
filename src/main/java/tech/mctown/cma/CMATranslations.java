package tech.mctown.cma;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

// Code part from carpet extra mod

public class CMATranslations {
    public static Map<String, String> getTranslationFromResourcePath(String lang) {
        InputStream langFile = CMATranslations.class.getClassLoader().getResourceAsStream("assets/cma/lang/%s.json".formatted(lang));
        if (langFile == null) {
            langFile = CMATranslations.class.getClassLoader().getResourceAsStream("assets/cma/lang/en_us.json");
            if (langFile == null) {
                throw new IllegalStateException("Could not find default translation file");
            }
        }
        String jsonData;
        try {
            jsonData = IOUtils.toString(langFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return Collections.emptyMap();
        }
        Gson gson = new GsonBuilder().setLenient().create(); // lenient allows for comments
        return gson.fromJson(jsonData, new TypeToken<Map<String, String>>() {
        }.getType());
    }
}
