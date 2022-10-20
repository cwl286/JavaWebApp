package com.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.model.Holidays;
import com.demo.model.HolidaysRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class MainController {
    // https://spring.io/guides/gs/accessing-data-mysql/
    @Autowired // This means to get the bean called holidaysRepository
    private HolidaysRepository holidaysRepository;

    @RequestMapping(value = { "/" }, method = { RequestMethod.GET })
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }

    /**
     * load data from json to database
     * @return String
     * @throws Exception
     */
//    @PostMapping(path = "/index/addData")
    @RequestMapping(value = { "/index/addData" }, method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody String addData() throws Exception {

        // Get data from json api
        String url = "https://apicwldemo.herokuapp.com/api/v1/importjson?apikey=AbHTJFYu0QDsr9u6Ax0i";
        JSONObject jo = JsonController.readJsonFromUrl(url);

        // loop json object for storing database 
        Iterator<String> keys = jo.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jo.get(key) instanceof JSONObject) {
                JSONObject subjo = (JSONObject) jo.get(key);
                Holidays holi = new Holidays();
                Iterator<String> subkeys = subjo.keys();
                HashMap<String, String> map2 = new HashMap<>();
                while (subkeys.hasNext()) {
                    String subkey = subkeys.next();

                    switch (subkey) {
                        case "uid":
                            holi.setUid((String) subjo.get(subkey));
                            break;
                        case "dtstart":
                            holi.setStartDate((String) subjo.get(subkey));
                            break;
                        case "dtend":
                            holi.setEndDate((String) subjo.get(subkey));
                            break;
                        case "summary":
                            holi.setSummary((String) subjo.get(subkey));
                            break;
                        default:
                            break;
                    }

                    map2.put(subkey, (String) subjo.get(subkey));
                }
                holidaysRepository.save(holi);
            }
        }
        return "saved";
    }

    /**
     * Add data to database, get Data from database and return
     * @return JSONarray
     * @throws Exception
     */
//    @PostMapping(path = "/index/getData")
    @RequestMapping(value = { "/index/getData" }, method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody ArrayList<HashMap> getAllUsers() throws Exception {
        // addData
        addData();
        
        // Get data
        Iterator<Holidays> it = holidaysRepository.findAll().iterator();
        
        // Convert the date format and build output
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        ArrayList<HashMap> ls = new ArrayList<HashMap>();
        while (it.hasNext()) {
            Holidays holiday = it.next();
            HashMap<String, String> map2 = new HashMap<>();
            map2.put("uid", holiday.getUid());
            map2.put("dtend", dateFormat.format(holiday.getEndDate()));
            map2.put("dtstart", dateFormat.format(holiday.getStartDate()));
            map2.put("summary", holiday.getSummary());
            ls.add(map2);
        }

        return ls;
    }

    /**
     * Get all data directly from json
     * @return JSONarray
     * @throws Exception
     */
    @RequestMapping(value = { "/index/getDataDirect" }, method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<ArrayList<HashMap>> getData() throws Exception {
        ArrayList<HashMap> ls = new ArrayList<HashMap>();

        // Get data
        String url = "https://apicwldemo.herokuapp.com/api/v1/importjson?apikey=AbHTJFYu0QDsr9u6Ax0i";
        JSONObject jo = JsonController.readJsonFromUrl(url);

        // Loop json result to build output
        Iterator<String> keys = jo.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (jo.get(key) instanceof JSONObject) {
                JSONObject subjo = (JSONObject) jo.get(key);
                Iterator<String> subkeys = subjo.keys();
                HashMap<String, String> map2 = new HashMap<>();
                while (subkeys.hasNext()) {
                    String subkey = subkeys.next();
                    map2.put(subkey, (String) subjo.get(subkey));
                }
                ls.add(map2);
            }
        }
        return new ResponseEntity<>(ls, HttpStatus.OK);
    }
}
