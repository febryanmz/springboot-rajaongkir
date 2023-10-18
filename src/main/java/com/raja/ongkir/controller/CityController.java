package com.raja.ongkir.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/city")
public class CityController {

    private RestTemplate restTemplate;
    //
    public CityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/getallcity")
    @ResponseBody
    public ResponseEntity<String> getAllCity(
        @RequestParam("key") String key,
        @RequestParam(value = "android-key", required = false) String androidKey,
        @RequestParam(value = "ios-key", required = false) String iosKey,
        @RequestParam(value = "id", required = false) String id,
        @RequestParam(value = "province", required = false) String province
    ) {
        String baseCityUrl = "https://api.rajaongkir.com/starter/city";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(baseCityUrl);

        if (id != null) {
            uriBuilder.queryParam("id", id);
        } if (province != null) {
            uriBuilder.queryParam("province", province);
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
