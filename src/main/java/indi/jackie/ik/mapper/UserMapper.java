package indi.jackie.ik.mapper;

import indi.jackie.ik.annotation.Dao;
import indi.jackie.ik.domain.User;

@Dao
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}