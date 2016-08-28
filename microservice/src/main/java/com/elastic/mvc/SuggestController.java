package com.elastic.mvc;

import com.elastic.mvc.response.Hotel;
import com.elastic.mvc.response.HotelResponse;
import io.searchbox.client.JestClient;
import io.searchbox.core.Suggest;
import io.searchbox.core.SuggestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class SuggestController {

    private final JestClient jestClient;

    public SuggestController(JestClient jestClient) {
        this.jestClient = jestClient;
    }

    @GetMapping(path = "/suggest")
    public HotelResponse suggest(@RequestParam(value = "q", required = true) String q,
                                 @RequestParam(value = "maxResults", required = false, defaultValue = "0")
                                         int maxResults)
            throws IOException {

        String suggestionName = "name_suggest";


        List<Hotel> items = new ArrayList<>();

        Suggest suggest = new Suggest.Builder("{\n" +
                "  \"hotels\" : {\n" +
                "    \"text\" : \"" + q + "\",\n" +
                "    \"completion\" : {\n" +
                "      \"field\" : \"name_suggest\",\n" +
                "      \"fuzzy\" : {\n" +
                "        \"edit_distance\" : 2\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}").addIndex("hotels").build();


        SuggestResult result = jestClient.execute(suggest);

        List<SuggestResult.Suggestion> suggestions = result.getSuggestions("hotels");
        SuggestResult.Suggestion suggestion = suggestions.get(0);
        suggestion.options.stream().forEach(s -> items.add(Hotel.builder().name(s.get("text").toString()).build()));

        return HotelResponse.builder().pointsData(items).build();
    }
}
