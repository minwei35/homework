package com.model.dao;

//设备dao

import com.model.pojo.Amenity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class AmenityDao {

    //模拟数据库中的数据
    private static final Map<Integer, Amenity> amenity;

    static {
        amenity = new HashMap<Integer, Amenity>();
        amenity.put(1001,new Amenity(1001,"location 1","Amenity 1"));
        amenity.put(1002,new Amenity(1002,"location 2","Amenity 2"));
        amenity.put(1003,new Amenity(1003,"location 3","Amenity 3"));
        amenity.put(1004,new Amenity(1004,"location 4","Amenity 4"));
        amenity.put(1005,new Amenity(1005,"location 5","Amenity 5"));
    }

    //设置主键自增
    private static Integer initId = 1006;

    //增加一个设备信息
    public void save(Amenity amenity){
        if(amenity.getId() == null){
            amenity.setId(initId++);
        }
        AmenityDao.amenity.put(amenity.getId(), amenity);
    }

    //查询全部设备信息
    public Collection<Amenity> getAllAmenity(){
        return amenity.values();
    }

    //通过id查询设备信息
    public Amenity getAmenityById(Integer id){
        return amenity.get(id);
    }

    //修改设备信息
    public void updateAmenity(Amenity amenity){
        AmenityDao.amenity.put(amenity.getId(), amenity);
    }

    //删除设备信息
    public void deleteAmenity(Integer id){
        amenity.remove(id);
    }
}
