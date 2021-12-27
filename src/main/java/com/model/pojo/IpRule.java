package com.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//IP对应站点映射信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpRule {
    private Integer id;
    private String ipRule;
    private StationHtml stationHtml;
}
