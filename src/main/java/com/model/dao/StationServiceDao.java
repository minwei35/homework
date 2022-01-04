package com.model.dao;

//设备dao

import com.model.pojo.Amenity;
import com.model.pojo.StationService;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class StationServiceDao {

    //模拟数据库中的数据
    private static final Map<Integer, StationService> stationService;

    static {
        stationService = new HashMap<>();
        stationService.put(1001,new StationService(1001,"location 1","StationService 1", new Amenity(1001,"location 1","Amenity 1")));
        stationService.put(1002,new StationService(1002,"location 2","StationService 2", new Amenity(1002,"location 2","Amenity 2")));
        stationService.put(1003,new StationService(1003,"location 3","StationService 3", new Amenity(1003,"location 3","Amenity 3")));
        stationService.put(1004,new StationService(1004,"location 4","StationService 4", new Amenity(1004,"location 4","Amenity 4")));
        stationService.put(1005,new StationService(1005,"location 5","StationService 5", new Amenity(1005,"location 5","Amenity 5")));
    }

    //设置主键自增
    private static Integer initId = 1006;

    //增加一个站点服务信息
    public void save(StationService stationService){
        if(stationService.getId() == null){
            stationService.setId(initId++);
        }
        StationServiceDao.stationService.put(stationService.getId(), stationService);
    }

    //查询全部站点服务信息
    public Collection<StationService> getAllStationService(){
        return stationService.values();
    }

    //通过id查询站点服务信息
    public StationService getStationServiceById(Integer id){
        return stationService.get(id);
    }

    //修改站点服务信息
    public void updateStationService(StationService stationService){
        StationServiceDao.stationService.put(stationService.getId(), stationService);
    }

    //删除站点服务信息
    public void deleteStationService(Integer id){
        stationService.remove(id);
    }
}
