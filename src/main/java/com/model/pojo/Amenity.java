package com.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//设备信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {
    private Integer id;
    private String location;
    private String name;

}
