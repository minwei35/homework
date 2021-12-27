package com.model.dao;

//员工dao

import com.model.pojo.StationHtml;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class StationHtmlDao {

    //模拟数据库中的数据
    private static final Map<Integer, StationHtml> stationHtml;

    static {
        stationHtml = new HashMap<Integer, StationHtml>();
        stationHtml.put(1001,new StationHtml(1001,"station 1","1.html"));
        stationHtml.put(1002,new StationHtml(1002,"station 2","2.html"));
        stationHtml.put(1003,new StationHtml(1003,"station 3","3.html"));
        stationHtml.put(1004,new StationHtml(1004,"station 4","4.html"));
        stationHtml.put(1005,new StationHtml(1005,"station 5","5.html"));
    }

    //设置主键自增
    private static Integer initId = 1006;

    //增加一个站点信息
    public void save(StationHtml stationHtml){
        if(stationHtml.getId() == null){
            stationHtml.setId(initId++);
        }
        StationHtmlDao.stationHtml.put(stationHtml.getId(), stationHtml);
    }

    //查询全部站点信息
    public Collection<StationHtml> getAllStationHtml(){
        return stationHtml.values();
    }

    //通过id查询站点信息
    public StationHtml getStationHtmlById(Integer id){
        return stationHtml.get(id);
    }

    //修改站点信息
    public void updateStationHtml(StationHtml stationHtml){
        StationHtmlDao.stationHtml.put(stationHtml.getId(), stationHtml);
    }

    //删除站点信息
    public void deleteStationHtml(Integer id){
        stationHtml.remove(id);
    }
}
