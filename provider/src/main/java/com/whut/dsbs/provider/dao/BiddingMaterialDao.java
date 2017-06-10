package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.BiddingMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 投标报价的产品所需材料Dao层
 *
 * Created by zyb on 2017-05-30.
 */
@Mapper
public interface BiddingMaterialDao {
    List<BiddingMaterial> selectAllByBiddingId(@Param("biddingId")int biddingId);

    boolean insert(BiddingMaterial item);

    boolean update(BiddingMaterial item);

    List<BiddingMaterial> selectIsNotEnoughMaterial(@Param("biddingId")int biddingId);
}
