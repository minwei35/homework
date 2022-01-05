package com.model.dao;

//员工dao

import com.model.pojo.Amenity;
import com.model.pojo.IpRule;
import com.model.pojo.StationHtml;
import com.model.pojo.StationService;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Repository
public class IpRuleDao {

    //模拟数据库中的数据
    private static final Map<Integer, IpRule> ipToStation;

    static {
        ipToStation = new HashMap<Integer, IpRule>();
        ipToStation.put(1001,new IpRule(1001,"192.168.10.120",new StationHtml(1001,"station 1","1.html", Collections.singletonList(new StationService(1001,"location 1","StationService 1")),new Amenity(1001,"location 1","amenity 1"))));
        ipToStation.put(1002,new IpRule(1002,"192.168.10.*",new StationHtml(1002,"station 2","2.html", Collections.singletonList(new StationService(1002,"location 2","StationService 2")),new Amenity(1002,"location 2","amenity 2"))));
        ipToStation.put(1003,new IpRule(1003,"192.168.*.*",new StationHtml(1003,"station 3","3.html", Collections.singletonList(new StationService(1003,"location 3","StationService 3")),new Amenity(1003,"location 3","amenity 3"))));
        ipToStation.put(1004,new IpRule(1004,"192.*.*.*",new StationHtml(1004,"station 4","4.html", Collections.singletonList(new StationService(1004,"location 4","StationService 4")),new Amenity(1004,"location 4","amenity 4"))));
    }

    //设置主键自增
    private static Integer initId = 1005;

    //增加一个IP对应站点信息
    public void save(IpRule ipRule){
        if(ipRule.getId() == null){
            ipRule.setId(initId++);
        }
        IpRuleDao.ipToStation.put(ipRule.getId(), ipRule);
    }

    //查询全部IP对应站点信息
    public Collection<IpRule> getAllIpRule(){
        return ipToStation.values();
    }

    //通过id查询IP对应站点信息
    public IpRule getSIpRuleById(Integer id){
        return ipToStation.get(id);
    }

    //修改IP对应站点信息
    public void updateIpRule(IpRule ipRule){
        IpRuleDao.ipToStation.put(ipRule.getId(), ipRule);
    }

    //删除IP对应站点信息
    public void deleteIpRule(Integer id){
        ipToStation.remove(id);
    }
}
