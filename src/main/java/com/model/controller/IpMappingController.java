package com.model.controller;

import com.model.IpMappingService;
import com.model.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class IpMappingController {

    private final IpMappingService ipMappingService;

    @RequestMapping("/ipMapping")
    public String list(HttpServletRequest request,Model model){
        String ipAddr = IpUtils.getIpAddr(request);
        model.addAttribute("ipAddr", ipAddr);
        String ipMappingStationHtml = ipMappingService.getIpMappingStationHtml(ipAddr);
        if (ipMappingStationHtml.contains(".html")){
            ipMappingStationHtml = ipMappingStationHtml.replace(".html","");
        };
        return "result/" + ipMappingStationHtml;
    }

}
