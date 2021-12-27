package com.model.controller;

import com.model.dao.IpRuleDao;
import com.model.dao.StationHtmlDao;
import com.model.pojo.IpRule;
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
public class IpToStationController {

    @Autowired
    StationHtmlDao stationHtmlDao;
    @Autowired
    IpRuleDao ipRuleDao;

    @RequestMapping("/ips")
    public String list(Model model){
        Collection<IpRule> ipRules = ipRuleDao.getAllIpRule();
        model.addAttribute("ipRules", ipRules);
        return "ipRule/list";
    }

    //get请求走这个添加方法
    @GetMapping("/ip")
    public String toAdd(Model model){
        //查询站点列表
        Collection<StationHtml> stationHtmls = stationHtmlDao.getAllStationHtml();
        model.addAttribute("stations",stationHtmls);
        //跳转到表单页面
        return "ipRule/add";
    }

    //post请求走这个方法
    @PostMapping("/ip")
    public String add(IpRule ipRule){
        if (ipRule.getStationHtml() != null){
            Integer stationId = ipRule.getStationHtml().getId();
            StationHtml stationHtmlById = stationHtmlDao.getStationHtmlById(stationId);
            ipRule.setStationHtml(stationHtmlById);
        }
        ipRuleDao.save(ipRule);
        return "redirect:/ips";
    }

    @GetMapping("/ip/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        IpRule ipRule = ipRuleDao.getSIpRuleById(id);
        //查询站点列表
        Collection<StationHtml> stationHtmls = stationHtmlDao.getAllStationHtml();
        model.addAttribute("stations",stationHtmls);
        model.addAttribute("ipRule", ipRule);
        //转向编辑页面
        return "ipRule/edit";
    }

    @PostMapping("/ip/{id}")
    public String edit(@PathVariable("id") Integer id, IpRule ipRule){
        ipRule.setId(id);
        if (ipRule.getStationHtml() != null){
            Integer stationId = ipRule.getStationHtml().getId();
            StationHtml stationHtmlById = stationHtmlDao.getStationHtmlById(stationId);
            ipRule.setStationHtml(stationHtmlById);
        }
        //进行dao层的修改操作
        ipRuleDao.updateIpRule(ipRule);
        return "redirect:/ips";
    }

    @RequestMapping("/delIp/{id}")
    public String delete(@PathVariable("id") Integer id){
        ipRuleDao.deleteIpRule(id);
        return "redirect:/ips";
    }
}
