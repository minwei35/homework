package com.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//站点服务信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationService {
    private Integer id;
    private String location;
    private String name;
//    private Amenity amenity;
}
