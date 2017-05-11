package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.Bidding;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报价投标Dao接口
 *
 * Created by zyb on 2017-05-10.
 */
@Mapper
public interface BiddingDao {
    Bidding insert(Bidding bidding);

    List<Bidding> selectByRoleId(@Param("roleId")int roleId);
}
