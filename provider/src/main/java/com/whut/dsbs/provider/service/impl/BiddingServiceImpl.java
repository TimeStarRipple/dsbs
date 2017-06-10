package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.Bidding;
import com.whut.dsbs.common.dto.BiddingMaterial;
import com.whut.dsbs.common.dto.BiddingProduceCost;
import com.whut.dsbs.common.service.ActivitiService;
import com.whut.dsbs.common.service.BiddingMaterialService;
import com.whut.dsbs.common.service.BiddingProduceCostService;
import com.whut.dsbs.common.service.BiddingService;
import com.whut.dsbs.provider.dao.BiddingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 报价投标服务层的实现
 *
 * Created by zyb on 2017-05-10.
 */
@Service("biddingService")
@Transactional
public class BiddingServiceImpl implements BiddingService{

    @Resource
    private ActivitiService activitiService;

    @Autowired
    private BiddingMaterialService biddingMaterialService;

    @Autowired
    private BiddingDao biddingDao;

    @Autowired
    private BiddingProduceCostService biddingProduceCostService;

    public List<Bidding> selectAll() {
        return null;
    }

    public List<Bidding> selectByPage(int i, String s) {
        return null;
    }

    public Bidding select(Bidding bidding) {
        return biddingDao.selectById(bidding.getId());
    }

    public Bidding insert(Bidding bidding) {
        String processInstanceId = activitiService.startProcessInstance(bidding.getUserId());
        bidding.setProcessInstanceId(processInstanceId);

        //查到新建的任务
        List<Bidding> biddings = activitiService.findRoleCurrentTask(2, 1, null);//决策者角色id

        Map<String, Bidding> map = new HashMap<String, Bidding>();
        for(Bidding item : biddings){
            map.put(item.getProcessInstanceId(), item);
        }

        Bidding result = map.get(processInstanceId);

        //创建投标报价单
        bidding.setAssigneeDepartment("技术部门");
        bidding.setAssigneeRoleId(6);
        bidding.setProcessInstanceId(result.getProcessInstanceId());
        bidding.setTaskId(result.getTaskId());
        bidding.setTaskName(result.getTaskName());
        bidding.setCreateTime(new Date());
        biddingDao.insert(bidding);

        //完成任务，后期如果添加了保存功能的话，要分割成两个方法
        activitiService.completeTask(bidding);
        //这一个模块要不要，看后期具体需求，因为下一个是什么角色不确定，所以在读取的时候使用了角色，所以不一定需要
//        //查到更新后的任务信息并重新设置
//        List<Bidding> bidding2 = activitiService.findRoleCurrentTask(6, 1, null);//决策者角色id
//        Bidding result2 = bidding2.get(0);
//        bidding.setTaskId(result2.getTaskId());
//        bidding.setTaskName(result2.getTaskName());

        return bidding;
    }

    public Bidding delete(Bidding bidding) {
        return null;
    }

    public Bidding update(Bidding bidding) {
        activitiService.completeTask(bidding);
        biddingDao.update(bidding);
        return bidding;
    }

    public boolean deleteBatch(String s) {
        return false;
    }

    public List<Bidding> selectBiddingByRoleId(int roleId, int page, String filter) {

        List<Bidding> activitiBidding = activitiService.findRoleCurrentTask(roleId, page, filter);

        List<Bidding> biddings = biddingDao.selectByRoleId(roleId);
        Map<String, Bidding> map = new HashMap<String, Bidding>();
        for(Bidding bidding : biddings){
            map.put(bidding.getProcessInstanceId(), bidding);
        }

        List<Bidding> result = new ArrayList<Bidding>();
        for(Bidding bidding : activitiBidding){
            Bidding item = map.get(bidding.getProcessInstanceId());

            if(item != null){
                item.setTaskId(bidding.getTaskId());
                item.setTaskName(bidding.getTaskName());

                result.add(item);
            }
        }

        return result;
    }

    public Bidding selectBiddingById(int i) {
        return biddingDao.selectById(i);
    }

    public boolean completeProductSplit(List<BiddingMaterial> list, int roleId) {
        Bidding bidding = biddingDao.selectById(list.get(0).getBiddingId());
        bidding.setAssigneeRoleId(roleId);
        bidding = activitiService.selectTaskByBidding(bidding);

        //更新工作流以及报价产品信息
        bidding.setAssigneeRoleId(4);
        bidding.setAssigneeDepartment("仓库管理部门");
        this.update(bidding);

        //创建产品所需材料
        biddingMaterialService.createBatch(list);
        return true;
    }

    public Bidding selectBiddingAndMaterialById(int i) {
        Bidding result = biddingDao.selectById(i);
        List<BiddingMaterial> biddingMaterials = biddingMaterialService.selectAllByBiddingId(i);
        result.setBiddingMaterials(biddingMaterials);
        return result;
    }

    public boolean completeConfirmQuantity(Bidding bidding) {
        bidding.setAssigneeRoleId(4);
        bidding = activitiService.selectTaskByBidding(bidding);

        //更新工作流以及报价产品信息
        if(bidding.getCondition().equals("足够")){
            bidding.setAssigneeRoleId(5);
            bidding.setAssigneeDepartment("生产部门");
        }
        else{
            bidding.setAssigneeRoleId(3);
            bidding.setAssigneeDepartment("采购部门");
        }
        this.update(bidding);

        //更新产品所需材料
        biddingMaterialService.updateBatch(bidding.getBiddingMaterials());
        return true;
    }

    public Bidding selectDeterminePurchasePriceBidding(int i) {
        Bidding result = biddingDao.selectById(i);
        List<BiddingMaterial> biddingMaterials = biddingMaterialService.selectPurchaseMaterialByBiddingId(i);
        result.setBiddingMaterials(biddingMaterials);
        return result;
    }

    public boolean completeDeterminePurchasePrice(Bidding bidding) {
        bidding.setAssigneeRoleId(3);
        bidding = activitiService.selectTaskByBidding(bidding);

        //更新工作流以及报价产品信息
        bidding.setAssigneeRoleId(5);
        bidding.setAssigneeDepartment("生产部门");
        this.update(bidding);

        //更新产品所需材料
        biddingMaterialService.updateBatch(bidding.getBiddingMaterials());
        return true;
    }

    public boolean completeDetermineCost(Bidding bidding) {
        bidding.setAssigneeRoleId(5);
        bidding = activitiService.selectTaskByBidding(bidding);

        //更新工作流以及报价产品信息
        bidding.setAssigneeRoleId(2);
        bidding.setAssigneeDepartment("boss");
        this.update(bidding);

        //更新生产信息
        biddingProduceCostService.insert(bidding.getBiddingProduceCost());

        return true;
    }

    public Bidding selectResultBidding(int i) {
        //获取bidding
        Bidding result = biddingDao.selectById(i);

        //获取分割产品信息
        List<BiddingMaterial> biddingMaterials = biddingMaterialService.selectAllByBiddingId(i);

        //获取生产费用
        BiddingProduceCost biddingProduceCost = new BiddingProduceCost();
        biddingProduceCost.setBiddingId(i);
        biddingProduceCost = biddingProduceCostService.select(biddingProduceCost);

        //设置值
        result.setBiddingMaterials(biddingMaterials);
        result.setBiddingProduceCost(biddingProduceCost);
        return result;
    }
}
