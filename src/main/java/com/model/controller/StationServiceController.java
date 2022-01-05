package com.model.controller;

import com.model.dao.AmenityDao;
import com.model.dao.StationServiceDao;
import com.model.pojo.Amenity;
import com.model.pojo.StationHtml;
import com.model.pojo.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class StationServiceController {

    @Autowired
    StationServiceDao stationServiceDao;

    @RequestMapping("/stationServices")
    public String list(Model model){
        Collection<StationService> stationServices = stationServiceDao.getAllStationService();
        model.addAttribute("stationServices", stationServices);
        return "stationService/list";
    }

    //get请求走这个添加方法
    @GetMapping("/stationService")
    public String toAdd(Model model){
        //跳转到表单页面
        return "stationService/add";
    }

    //post请求走这个方法
    @PostMapping("/stationService")
    public String add(StationService stationService){
        stationServiceDao.save(stationService);
        return "redirect:/stationServices";
    }

    @GetMapping("/stationService/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        StationService stationService = stationServiceDao.getStationServiceById(id);
        model.addAttribute("stationService", stationService);
        //转向编辑页面
        return "stationService/edit";
    }

    @PostMapping("/stationService/{id}")
    public String edit(@PathVariable("id") Integer id, StationService stationService){
        stationService.setId(id);
        //进行dao层的修改操作
        stationServiceDao.updateStationService(stationService);
        return "redirect:/stationServices";
    }

    @RequestMapping("/delStationService/{id}")
    public String delete(@PathVariable("id") Integer id){
        stationServiceDao.deleteStationService(id);
        return "redirect:/stationServices";
    }
}
