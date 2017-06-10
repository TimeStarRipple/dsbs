package com.whut.dsbs.customer.controller;

import com.whut.dsbs.common.dto.Bidding;
import com.whut.dsbs.common.dto.BiddingMaterial;
import com.whut.dsbs.common.dto.MaterialType;
import com.whut.dsbs.common.service.ActivitiService;
import com.whut.dsbs.common.service.BiddingMaterialService;
import com.whut.dsbs.common.service.BiddingService;
import com.whut.dsbs.common.service.MaterialTypeService;
import com.whut.dsbs.customer.constants.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流信息
 *
 * Created by zyb on 2017-05-22.
 */
@RestController
public class ActivitiController {

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private MaterialTypeService materialTypeService;

    /**
     * 我的工作流列表查询
     * @param roleId
     * @param page
     * @param filter
     * @return
     */
    @RequestMapping(value = "/activiti/list", method = RequestMethod.GET)
    public JsonResult getActivitiList(int roleId, int page, String filter){
        List<Bidding> biddings = biddingService.selectBiddingByRoleId(roleId, page, filter);
        return new JsonResult("200", "查询成功", biddings);
    }

    /**
     * 创建产品投标
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti", method = RequestMethod.POST)
    public JsonResult createActiviti(@RequestBody Bidding data){
        System.out.println(data);
        data = biddingService.insert(data);
        return new JsonResult("200", "添加成功", data);
    }

    /**
     * 技术部门-产品拆分
     * 产品详情信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/activiti/{id}", method = RequestMethod.GET)
    public JsonResult getBiddingAndMaterialById(@PathVariable("id")int id){
        //获取投标信息
        Bidding bidding = new Bidding();
        bidding.setId(id);
        bidding = biddingService.select(bidding);

        //获取材料类型数据用于产品分割时的材料选择
        List<MaterialType> materialTypes = materialTypeService.selectAll();

        //返回结果
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("bidding", bidding);
        result.put("materialTypes", materialTypes);
        return new JsonResult("200", "查询成功", result);
    }

    /**
     * 技术部门-产品拆分
     * 创建拆分信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti/productSplit", method = RequestMethod.POST)
    public JsonResult createBiddingMaterial(@RequestBody Bidding data){
        System.out.println(data);
        biddingService.completeProductSplit(data.getBiddingMaterials(), data.getAssigneeRoleId());
        return new JsonResult("200", "创建成功", data);
    }

    /**
     * 仓库部门-确认数量
     * 产品详情信息
     * @param biddingId
     * @return
     */
    @RequestMapping(value = "/activiti/confirmQuantity/{id}", method = RequestMethod.GET)
    public JsonResult getBiddingMaterial(@PathVariable("id")int biddingId){
        Bidding result = biddingService.selectBiddingAndMaterialById(biddingId);
        return new JsonResult("200", "查询成功", result);
    }

    /**
     * 仓库部门-确认数量
     * 确认是否足够
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti/confirmQuantity/{id}", method = RequestMethod.PUT)
    public JsonResult updateBiddingConfirmQuantity(@RequestBody Bidding data){
        System.out.println(data);
        biddingService.completeConfirmQuantity(data);
        return new JsonResult("200", "更新成功", data);
    }

    /**
     * 生产部门-确定费用
     * 产品详情信息
     * @param biddingId
     * @return
     */
    @RequestMapping(value = "/activiti/determineCost/{id}", method = RequestMethod.GET)
    public JsonResult getBiddingDetermineCost(@PathVariable("id")int biddingId){
        Bidding result = biddingService.selectBiddingAndMaterialById(biddingId);
        return new JsonResult("200", "查询成功", result);
    }

    /**
     * 生产部门-确定费用
     * 确认生产报价
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti/determineCost", method = RequestMethod.POST)
    public JsonResult createDetermineCost(@RequestBody Bidding data){
        System.out.println(data);
        biddingService.completeDetermineCost(data);
        return new JsonResult("200", "创建成功", data);
    }

    /**
     * 采购部门-确定收购价
     * 查看需要采购的材料信息
     * @param biddingId
     * @return
     */
    @RequestMapping(value = "/activiti/determinePurchasePrice/{id}", method = RequestMethod.GET)
    public JsonResult getDeterminePurchasePrice(@PathVariable("id")int biddingId){
        Bidding result = biddingService.selectDeterminePurchasePriceBidding(biddingId);
        return new JsonResult("200", "查询成功", result);
    }

    /**
     * 采购部门-确定收购价
     * 更新采购价格信息
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti/determinePurchasePrice/{id}", method = RequestMethod.PUT)
    public JsonResult updateDeterminePurchasePrice(@RequestBody Bidding data){
        System.out.println(data);
        biddingService.completeDeterminePurchasePrice(data);
        return new JsonResult("200", "更新成功", data);
    }

    /**
     * 查看报价结果
     * 查询所有报价信息
     * @param biddingId
     * @return
     */
    @RequestMapping(value = "/activiti/quoteResults/{id}", method = RequestMethod.GET)
    public JsonResult getQuoteResults(@PathVariable("id")int biddingId){
        Bidding result = biddingService.selectResultBidding(biddingId);
        return new JsonResult("200", "查询成功", result);
    }

    /**
     * 查看报价结果
     * 工作流完成，这里不需要完成，这个方法先放在这
     * @param data
     * @return
     */
    @RequestMapping(value = "/activiti/quoteResults/{id}", method = RequestMethod.PUT)
    public JsonResult updateQuoteResults(@RequestBody Bidding data){
        System.out.println(data);
        //待添加
        return new JsonResult("200", "更新成功", data);
    }
}
