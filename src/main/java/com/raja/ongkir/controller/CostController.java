package com.raja.ongkir.controller;


import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cost")
public class CostController {
    private RestTemplate restTemplate;
    //
    public CostController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @PostMapping(value = "/postcost", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> getCost(
        @RequestHeader("key") String key,
        @RequestParam("origin") String origin,
        @RequestParam("destination") String destination,
        @RequestParam("weight") long weight,
        @RequestParam("courier") String courier,
        @RequestParam(value = "android-key", required = false) String androidKey,
        @RequestParam(value = "ios-key", required = false) String iosKey
    ) {

        String baseUrl = "https://api.rajaongkir.com/starter/cost";

        HttpHeaders headers = new HttpHeaders();
        headers.add("key", key);
        headers.add("android-key", androidKey);
        headers.add("ios-key", iosKey);


        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("origin", origin);
        map.add("destination", destination);
        map.add("weight", Long.toString(weight));
        map.add("courier", courier);


        ResponseEntity<String> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(map, headers), String.class);

        return responseEntity;

    }

}
