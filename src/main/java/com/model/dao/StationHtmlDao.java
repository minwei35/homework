package com.model.dao;

//员工dao

import com.model.pojo.Amenity;
import com.model.pojo.StationHtml;
import com.model.pojo.StationService;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class StationHtmlDao {

    //模拟数据库中的数据
    private static final Map<Integer, StationHtml> stationHtml;

    static {
        stationHtml = new HashMap<Integer, StationHtml>();
        stationHtml.put(1001,new StationHtml(1001,"station 1","1.html", Collections.singletonList(new StationService(1001,"location 1","StationService 1")),new Amenity(1001,"location 1","amenity 1")));
        stationHtml.put(1002,new StationHtml(1002,"station 2","2.html", Collections.singletonList(new StationService(1002,"location 2","StationService 2")),new Amenity(1002,"location 2","amenity 2")));
        stationHtml.put(1003,new StationHtml(1003,"station 3","3.html", Collections.singletonList(new StationService(1003,"location 3","StationService 3")),new Amenity(1003,"location 3","amenity 3")));
        stationHtml.put(1004,new StationHtml(1004,"station 4","4.html", Collections.singletonList(new StationService(1004,"location 4","StationService 4")),new Amenity(1004,"location 4","amenity 4")));
        stationHtml.put(1005,new StationHtml(1005,"station 5","5.html", Collections.singletonList(new StationService(1005,"location 5","StationService 5")),new Amenity(1005,"location 5","amenity 5")));
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
