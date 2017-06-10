package com.whut.dsbs.customer.controller;

import com.whut.dsbs.common.dto.Material;
import com.whut.dsbs.customer.constants.JsonResult;
import com.whut.dsbs.common.service.MaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 材料管理控制器
 *
 * Created by zyb on 2017-04-30.
 */
@RestController
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @RequestMapping(value = "/material/{id}", method = RequestMethod.GET)
    public JsonResult getMaterialById(@PathVariable("id")int id){
        Material result = new Material();
        result.setId(id);
        result = materialService.select(result);
        return new JsonResult("200", "查询成功", result);
    }

    @RequestMapping(value = "/material", method = RequestMethod.GET)
    public JsonResult getMaterialByPage(int page, String search){
        System.out.println(page + ":" + search);

        List<Material> result = materialService.selectByPage(page, search);

        return new JsonResult("200", "查询成功", result);
    }

    @RequestMapping(value = "/material/deletes", method = RequestMethod.DELETE)
    public JsonResult deleteMaterial(String ids){
        System.out.println(ids);

        materialService.deleteBatch(ids);

        return new JsonResult("200", "批量删除成功", null);
    }

    @RequestMapping(value = "/material/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteMaterialById(@PathVariable("id")int id){

        Material material = new Material();
        material.setId(id);
        materialService.delete(material);

        return new JsonResult("200", "删除成功", null);
    }

    @RequestMapping(value = "/material/{id}", method = RequestMethod.PUT)
    public JsonResult updateMaterial(@RequestBody Material data){
        System.out.println(data);
        materialService.update(data);
        return new JsonResult("200", "更新成功", data);
    }

    @RequestMapping(value = "/material", method = RequestMethod.POST)
    public JsonResult createMaterial(@RequestBody Material data){
        System.out.println(data);

        Material result = materialService.insert(data);

        return new JsonResult("200", "创建成功", result);
    }
}
