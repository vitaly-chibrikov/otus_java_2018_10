package ru.otus.mybatis;

import org.apache.ibatis.annotations.Select;

import java.util.Optional;

public interface TestMapperInterface {

    @Select("select * from Test where id = #{id}")
    Optional<Test> findOne(Integer id);
}
