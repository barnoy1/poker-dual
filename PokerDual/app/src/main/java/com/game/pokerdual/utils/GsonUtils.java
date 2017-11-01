package com.game.pokerdual.utils;

import com.google.gson.Gson;

import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

public class GsonUtils {

    private static Gson gson = new Gson();

    public static String readJsonFile(File localFile)
    {
        if (localFile.canRead()){

            FileReader reader = null;
            try {

                reader = new FileReader(localFile);
                JSONParser jsonParser = new JSONParser();
                Object jsonObject = jsonParser.parse(reader);
                return jsonObject.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public static Gson getGson() {
        return gson;
    }
}
