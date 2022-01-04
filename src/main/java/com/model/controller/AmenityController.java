package com.model.controller;

import com.model.dao.AmenityDao;
import com.model.dao.StationHtmlDao;
import com.model.pojo.Amenity;
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
public class AmenityController {

    @Autowired
    AmenityDao amenityDao;

    @RequestMapping("/amenitys")
    public String list(Model model){
        Collection<Amenity> amenities = amenityDao.getAllAmenity();
        model.addAttribute("amenitys", amenities);
        return "amenity/list";
    }

    //get请求走这个添加方法
    @GetMapping("/amenity")
    public String toAdd(Model model){
        //跳转到表单页面
        return "amenity/add";
    }

    //post请求走这个方法
    @PostMapping("/amenity")
    public String add(Amenity amenity){
        amenityDao.save(amenity);
        return "redirect:/amenitys";
    }

    @GetMapping("/amenity/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        Amenity amenity = amenityDao.getAmenityById(id);
        model.addAttribute("amenity", amenity);
        //转向编辑页面
        return "amenity/edit";
    }

    @PostMapping("/amenity/{id}")
    public String edit(@PathVariable("id") Integer id, Amenity amenity){
        amenity.setId(id);
        //进行dao层的修改操作
        amenityDao.updateAmenity(amenity);
        return "redirect:/amenitys";
    }

    @RequestMapping("/delAmenity/{id}")
    public String delete(@PathVariable("id") Integer id){
        amenityDao.deleteAmenity(id);
        return "redirect:/amenitys";
    }
}
