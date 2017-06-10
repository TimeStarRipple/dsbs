package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.MaterialType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 材料类型dao层
 *
 * Created by zyb on 2017-05-30.
 */
@Mapper
public interface MaterialTypeDao {

    List<MaterialType> selectAll();
}
