package com.whut.dsbs.common.service;

import com.whut.dsbs.common.dto.Bidding;
import com.whut.dsbs.common.dto.BiddingMaterial;

import java.util.List;

/**
 * 投标报价服务层
 *
 * Created by zyb on 2017-05-10.
 */
public interface BiddingService extends BaseService<Bidding>{

    List<Bidding> selectBiddingByRoleId(int roleId, int page, String filter);

    Bidding selectBiddingById(int id);

    boolean completeProductSplit(List<BiddingMaterial> data, int roleId);

    Bidding selectBiddingAndMaterialById(int id);

    boolean completeConfirmQuantity(Bidding bidding);

    Bidding selectDeterminePurchasePriceBidding(int id);

    boolean completeDeterminePurchasePrice(Bidding bidding);

    boolean completeDetermineCost(Bidding bidding);

    Bidding selectResultBidding(int id);
}
