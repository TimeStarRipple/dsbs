package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.BiddingProduceCost;
import com.whut.dsbs.common.service.BiddingProduceCostService;
import com.whut.dsbs.provider.dao.BiddingProduceCostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 投标产品生产成本服务实现函数
 *
 * Created by zyb on 2017-06-03.
 */
@Service("biddingProduceCostService")
@Transactional
public class BiddingProduceCostServiceImpl extends BaseServiceImpl<BiddingProduceCost> implements BiddingProduceCostService{


    @Autowired
    private BiddingProduceCostDao biddingProduceCostDao;

    @Override
    public BiddingProduceCost insert(BiddingProduceCost biddingProduceCost) {
        biddingProduceCostDao.insert(biddingProduceCost);
        return biddingProduceCost;
    }

    @Override
    public BiddingProduceCost select(BiddingProduceCost biddingProduceCost) {
        return biddingProduceCostDao.selectByBiddingId(biddingProduceCost.getBiddingId());
    }
}
