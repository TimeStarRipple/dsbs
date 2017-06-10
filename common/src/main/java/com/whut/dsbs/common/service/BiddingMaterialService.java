package com.whut.dsbs.common.service;

import com.whut.dsbs.common.dto.BiddingMaterial;

import java.util.List;

/**
 * 投标报价材料实体类处理
 *
 * Created by zyb on 2017-05-30.
 */
public interface BiddingMaterialService extends BaseService<BiddingMaterial>{

    List<BiddingMaterial> selectAllByBiddingId(int biddingId);

    boolean createBatch(List<BiddingMaterial> list);

    boolean updateBatch(List<BiddingMaterial> list);

    List<BiddingMaterial> selectPurchaseMaterialByBiddingId(int biddingId);
}
