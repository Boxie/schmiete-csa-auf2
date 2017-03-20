package com.jsonsurfer.converter;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;


/**
 * Created by Lukas on 09.03.17.
 */
public class JSON {

    public JSONObject getJSONObject(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        URL asd = this.getClass().getResource(path);
        Object obj = parser.parse(new FileReader(asd.getPath()));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;

    }
}
