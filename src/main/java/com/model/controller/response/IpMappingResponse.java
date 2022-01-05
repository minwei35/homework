package com.model.controller.response;

import lombok.Data;

@Data
public class IpMappingResponse {
    private String currentIp;
    private String stationName;
    private String stationServiceName;
    private String amenity;
}
