package com.whut.dsbs.customer.service.impl;

import com.whut.dsbs.common.dto.Material;
import com.whut.dsbs.customer.service.MaterialService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zyb on 2017-04-30.
 */
@Service("materialServiceImpl")
@Transactional
public class MaterialServiceImpl implements MaterialService{

    @Resource
    private com.whut.dsbs.common.service.MaterialService materialService;

    @Override
    public List<Material> getMaterialByPage(int page, String filter) {
        return materialService.selectByPage(page, filter);
    }

    @Override
    public boolean deleteMaterialByIds(String ids) {
        return materialService.deleteBatch(ids);
    }

    @Override
    public Material createMaterial(Material data) {
        return materialService.insert(data);
    }

    @Override
    public Material getMaterialById(int id) {
        Material material = new Material();
        material.setId(id);
        return materialService.select(material);
    }

    @Override
    public Material updateMaterial(Material data) {
        return materialService.update(data);
    }
}
