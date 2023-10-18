package com.raja.ongkir.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestProvinceModel {
    private String id;
    private String key;
    private String androidKey;
    private String iosKey;
}
