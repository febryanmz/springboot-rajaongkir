package com.raja.ongkir.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/province")
public class ProvinceController {

    private RestTemplate restTemplate;
//
    public ProvinceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getalldata")
    @ResponseBody
    public ResponseEntity<String> getAllProvince(
            @RequestParam("key") String key,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "android-key", required = false) String androidKey,
            @RequestParam(value = "ios-key", required = false) String iosKey

    ) {
        String baseProvinceUrl = "https://api.rajaongkir.com/starter/province";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseProvinceUrl);

        if (id != null) {
            uriBuilder.queryParam("id", id);
        }

        String rajaUrl = uriBuilder.toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.add("key", key);
        headers.add("android-key", androidKey);
        headers.add("ios-key", iosKey);


        ResponseEntity<String> responseEntity = restTemplate.exchange(rajaUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        return responseEntity;
    }
}