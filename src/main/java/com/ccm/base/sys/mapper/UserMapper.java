package com.ccm.base.sys.mapper;

import com.ccm.base.cache.Cache;
import com.ccm.base.cache.CacheKey;
import com.ccm.base.sys.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface UserMapper {

    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.TEST + "'+#name")
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

}