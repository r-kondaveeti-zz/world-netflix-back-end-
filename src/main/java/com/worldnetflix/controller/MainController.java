package com.worldnetflix.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

@RestController
public class MainController {


    public String getMovieNames(String keyword) throws Exception {

        String encodedURL = URLEncoder.encode(keyword, "UTF-8");
        encodedURL = encodedURL.replace("+", "%20");

        System.out.println(keyword);
        System.out.println(encodedURL);
        HttpResponse<String> response = Unirest.get("https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q="+encodedURL+"-!1900%2C2018-!0%2C5-!0%2C10-!0-!Any-!Any-!Any-!gt100-!%7Bdownloadable%7D&t=ns&cl=all&st=adv&ob=Relevance&p=1&sa=and")
                .header("")
                .header("")
                .asString();
        return response.getBody();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path="/search")
    public String searchForm(@RequestParam(name = "keyword") String keyword) throws Exception {
        String movieList = getMovieNames(keyword);
        return movieList;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/")
    public String homePage() throws Exception {
        HttpResponse<String> response = Unirest.get("https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q=get%3Anew7-!1900%2C2018-!0%2C5-!0%2C10-!0-!Any-!Any-!Any-!gt100-!%7Bdownloadable%7D&t=ns&cl=all&st=adv&ob=Relevance&p=1&sa=and")
                .header("x-rapidapi-host", "unogs-unogs-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "60c2cc28femsh78aa8418212493fp1ba00fjsn4bedfd91e07d")
                .asString();
        return response.getBody();
    }


}
