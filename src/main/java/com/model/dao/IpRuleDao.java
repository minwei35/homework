package com.model.dao;

//员工dao

import com.model.pojo.IpRule;
import com.model.pojo.StationHtml;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class IpRuleDao {

    //模拟数据库中的数据
    private static final Map<Integer, IpRule> ipToStation;

    static {
        ipToStation = new HashMap<Integer, IpRule>();
        ipToStation.put(1001,new IpRule(1001,"192.168.10.120",new StationHtml(1001,"station 1","1.html")));
        ipToStation.put(1002,new IpRule(1002,"192.168.10.*",new StationHtml(1002,"station 2","2.html")));
        ipToStation.put(1003,new IpRule(1003,"192.168.*.*",new StationHtml(1003,"station 3","3.html")));
        ipToStation.put(1004,new IpRule(1004,"192.*.*.*",new StationHtml(1004,"station 4","4.html")));
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
