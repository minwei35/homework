package com.model.controller;

import com.model.IpMappingService;
import com.model.controller.response.IpMappingResponse;
import com.model.pojo.IpRule;
import com.model.pojo.StationHtml;
import com.model.pojo.StationService;
import com.model.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class IpMappingController {

    private final IpMappingService ipMappingService;

    @RequestMapping("/ipMapping")
    public String list(HttpServletRequest request,Model model){
        String ipAddr = IpUtils.getIpAddr(request);
        IpMappingResponse response = new IpMappingResponse();
        response.setCurrentIp(ipAddr);
        IpRule ipRule = ipMappingService.getIpMappingStationHtml(ipAddr);
        if (ipRule == null){
            return "result/default";
        }
        StationHtml stationHtml = ipRule.getStationHtml();
        String ipMappingStationHtml = stationHtml.getHtmlPath();
        if (ipMappingStationHtml.contains(".html")){
            ipMappingStationHtml = ipMappingStationHtml.replace(".html","");
        };
        response.setStationName(stationHtml.getStationName());
        response.setStationServiceName(stationHtml.getStationServiceList().stream()
                .map(StationService::getName)
                .collect(Collectors.joining(",")));
        response.setAmenity(stationHtml.getAmenity().getName());
        model.addAttribute("ipMapping", response);
        return "result/" + ipMappingStationHtml;
    }

}
