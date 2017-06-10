package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.BiddingMaterial;
import com.whut.dsbs.common.service.BiddingMaterialService;
import com.whut.dsbs.provider.dao.BiddingMaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 投标产品所需材料服务实现
 *
 * Created by zyb on 2017-05-30.
 */
@Service("biddingMaterialService")
@Transactional
public class BiddingMaterialServiceImpl extends BaseServiceImpl<BiddingMaterial> implements BiddingMaterialService{
    @Autowired
    private BiddingMaterialDao biddingMaterialDao;

    public List<BiddingMaterial> selectAllByBiddingId(int i) {
        return biddingMaterialDao.selectAllByBiddingId(i);
    }

    public boolean createBatch(List<BiddingMaterial> list) {
        for(BiddingMaterial item : list){
            biddingMaterialDao.insert(item);
        }
        return true;
    }

    public boolean updateBatch(List<BiddingMaterial> list) {
        for(BiddingMaterial item : list){
            biddingMaterialDao.update(item);
        }
        return true;
    }

    public List<BiddingMaterial> selectPurchaseMaterialByBiddingId(int i) {
        return biddingMaterialDao.selectIsNotEnoughMaterial(i);
    }
}
