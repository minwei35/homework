package com.model.controller;

import com.model.dao.StationHtmlDao;
import com.model.pojo.StationHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class StationController {

    @Autowired
    StationHtmlDao stationHtmlDao;

    @RequestMapping("/stations")
    public String list(Model model){
        Collection<StationHtml> stationHtmls = stationHtmlDao.getAllStationHtml();
        model.addAttribute("stations", stationHtmls);
        return "station/list";
    }

    //get请求走这个添加方法
    @GetMapping("/station")
    public String toAdd(Model model){
        //跳转到表单页面
        return "station/add";
    }

    //post请求走这个方法
    @PostMapping("/station")
    public String add(StationHtml stationHtml){
        stationHtmlDao.save(stationHtml);
        return "redirect:/stations";
    }

    @GetMapping("/station/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        StationHtml stationHtml = stationHtmlDao.getStationHtmlById(id);
        model.addAttribute("station", stationHtml);
        //转向编辑页面
        return "station/edit";
    }

    @PostMapping("/station/{id}")
    public String edit(@PathVariable("id") Integer id, StationHtml stationHtml){
        stationHtml.setId(id);
        //进行dao层的修改操作
        stationHtmlDao.updateStationHtml(stationHtml);
        return "redirect:/stations";
    }

    @RequestMapping("/delStation/{id}")
    public String delete(@PathVariable("id") Integer id){
        stationHtmlDao.deleteStationHtml(id);
        return "redirect:/stations";
    }
}
