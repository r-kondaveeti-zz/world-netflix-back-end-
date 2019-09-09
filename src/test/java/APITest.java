
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.worldnetflix.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class APITest {
    public static void main(String[] args) throws Exception {
        APITest test = new APITest();
        List<Movie> movieList;
        movieList = test.getMovieNames("the lord of the rings");
        for(int i=0; i<movieList.size(); i++) {
            test.getFullDetails(movieList.get(i), movieList.get(i).getNetflixId());
        }
        System.out.println(movieList.get(0));
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(movieList);
        System.out.println(jsonString);
    }

    public List<Movie> getMovieNames(String keyword) throws Exception {
        List<Movie> movieList = new ArrayList<>();
        String encodedURL = URLEncoder.encode(keyword, "UTF-8");
        encodedURL = encodedURL.replace("+", "%20");
        HttpResponse<JsonNode> response = Unirest.get("https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?q="+encodedURL+"-!1900%2C2018-!0%2C5-!0%2C10-!0-!Any-!Any-!Any-!gt100-!%7Bdownloadable%7D&t=ns&cl=all&st=adv&ob=Relevance&p=1&sa=and")
                .header("x-rapidapi-host", "unogs-unogs-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "60c2cc28femsh78aa8418212493fp1ba00fjsn4bedfd91e07d")
                .asJson();
       JSONObject json =  response.getBody().getObject();
       JSONArray jsonArray = json.getJSONArray("ITEMS");
       for(int i=0; i<jsonArray.length(); i++) {
           Movie movie = new Movie();
            movie.setTitle(jsonArray.optJSONObject(i).getString("title"));
            movie.setImage(jsonArray.optJSONObject(i).getString("image"));
            movie.setRating(jsonArray.optJSONObject(i).getString("rating"));
            movie.setReleased(jsonArray.optJSONObject(i).getString("released"));
           movie.setNetflixId(jsonArray.optJSONObject(i).getString("netflixid"));
            movieList.add(movie);
        }
        return movieList;
    }


    //Evaluation is done on the JSON value "islive"
    public Movie getFullDetails(Movie movie, String netflixId) throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        HttpResponse<JsonNode> response = Unirest.get("https://unogs-unogs-v1.p.rapidapi.com/aaapi.cgi?t=loadvideo&q="+netflixId)
                .header("x-rapidapi-host", "unogs-unogs-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "60c2cc28femsh78aa8418212493fp1ba00fjsn4bedfd91e07d")
                .asJson();
         JSONObject json = response.getBody().getObject();
         JSONObject result = json.getJSONObject("RESULT");
         JSONArray array = result.getJSONArray("country");
        for (int i=0; i<array.length(); i++) {
           if(array.optJSONObject(i).getString("islive").equals("yes")) {
               arrayList.add(array.optJSONObject(i).getString("country"));
           }
        }
        movie.setCountries(arrayList);
        return movie;
    }


}
