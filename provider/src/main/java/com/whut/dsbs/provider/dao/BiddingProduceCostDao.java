package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.BiddingProduceCost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 投标产品生产成本Dao层
 *
 * Created by zyb on 2017-06-03.
 */
@Mapper
public interface BiddingProduceCostDao {
    int insert(BiddingProduceCost biddingProduceCost);

    BiddingProduceCost selectByBiddingId(@Param("biddingId")int biddingId);
}
