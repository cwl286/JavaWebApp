package com.demo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

// For Hong Kong Holidays data
public class JsonController {
    /**
     * Read json from file
     * @param path filepath
     * @return json object
     * @throws Exception
     */
    public static JSONObject readJsonFromFile(String path) throws Exception {
        File file = new File(path);
        if (file.exists()) {
            InputStream is = new FileInputStream(path);
            String jsonTxt = IOUtils.toString(is, "UTF-8");
            JSONObject json = new JSONObject(jsonTxt);    
            return json;
        }
        return null;
    }

    
    /**
     * Read json from url
     * @param url String
     * @return json object
     * @throws Exception
     */
    public static JSONObject readJsonFromUrl(String url) throws Exception {       

        InputStream input = new URL(url).openStream();
        try { 
            
            BufferedReader re = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            StringBuilder str = new StringBuilder();
            int temp;
            do {

                temp = re.read();
                str.append((char) temp);

            } while (temp != -1);
            String text = str.toString();
            JSONObject json = new JSONObject(text); 
            return json; 
            
        } catch (Exception e) {
            return null;
        } finally {
            input.close();
        }
    }
}
