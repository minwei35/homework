package com.model.controller;

import com.model.controller.request.StationHtmlRequest;
import com.model.controller.response.StationHtmlResponse;
import com.model.dao.StationHtmlDao;
import com.model.dao.StationServiceDao;
import com.model.pojo.Amenity;
import com.model.pojo.StationHtml;
import com.model.pojo.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StationController {

    @Autowired
    StationHtmlDao stationHtmlDao;
    @Autowired
    StationServiceDao stationServiceDao;

    @RequestMapping("/stations")
    public String list(Model model) {
        Collection<StationHtml> stationHtmls = stationHtmlDao.getAllStationHtml();
        List<StationHtmlResponse> response = stationHtmls.stream().map(StationHtmlResponse::new).peek(stationHtmlResponse -> {
                    if (!CollectionUtils.isEmpty(stationHtmlResponse.getStationServiceList())) {
                        stationHtmlResponse.setServiceName(stationHtmlResponse.getStationServiceList().stream()
                                .map(StationService::getName)
                                .collect(Collectors.joining(",")));
                    }
                })
                .collect(Collectors.toList());
        model.addAttribute("stations", response);
        return "station/list";
    }

    //get请求走这个添加方法
    @GetMapping("/station")
    public String toAdd(Model model) {
        Collection<StationService> stationServices = stationServiceDao.getAllStationService();
        model.addAttribute("stationServices", stationServices);
        //跳转到表单页面
        return "station/add";
    }

    //post请求走这个方法
    @PostMapping("/station")
    public String add(StationHtmlRequest request) {
        StationHtml stationHtml = new StationHtml();
        stationHtml.setHtmlPath(request.getHtmlPath());
        stationHtml.setStationName(request.getStationName());
        if (request.getStationServices() != null && request.getStationServices().length > 0) {
            List<StationService> stationServiceList = Arrays.stream(request.getStationServices()).map(Integer::valueOf).map(stationServiceDao::getStationServiceById).collect(Collectors.toList());
            stationHtml.setStationServiceList(stationServiceList);
        }
        stationHtmlDao.save(stationHtml);
        return "redirect:/stations";
    }

    @GetMapping("/station/{id}")
    public String toEdit(@PathVariable("id") Integer id, Model model) {
        StationHtml stationHtml = stationHtmlDao.getStationHtmlById(id);
        Collection<StationService> stationServices = stationServiceDao.getAllStationService();
        model.addAttribute("stationServices", stationServices);
        model.addAttribute("station", stationHtml);
        //转向编辑页面
        return "station/edit";
    }

    @PostMapping("/station/{id}")
    public String edit(@PathVariable("id") Integer id, StationHtmlRequest request) {
        StationHtml stationHtml = new StationHtml();
        stationHtml.setId(id);
        stationHtml.setStationName(request.getStationName());
        stationHtml.setHtmlPath(request.getHtmlPath());
        if (request.getStationServices() != null && request.getStationServices().length > 0) {
            List<StationService> stationServiceList = Arrays.stream(request.getStationServices()).map(Integer::valueOf).map(stationServiceDao::getStationServiceById).collect(Collectors.toList());
            stationHtml.setStationServiceList(stationServiceList);
        }
        //进行dao层的修改操作
        stationHtmlDao.updateStationHtml(stationHtml);
        return "redirect:/stations";
    }

    @RequestMapping("/delStation/{id}")
    public String delete(@PathVariable("id") Integer id) {
        stationHtmlDao.deleteStationHtml(id);
        return "redirect:/stations";
    }
}
