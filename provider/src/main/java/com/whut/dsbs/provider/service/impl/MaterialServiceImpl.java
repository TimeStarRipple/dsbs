package com.whut.dsbs.provider.service.impl;

import com.github.pagehelper.PageHelper;
import com.whut.dsbs.common.dto.Material;
import com.whut.dsbs.common.service.MaterialService;
import com.whut.dsbs.provider.dao.MaterialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 材料服务的实现
 *
 * Created by zyb on 2017-04-30.
 */
@Service("materialService")
@Transactional
public class MaterialServiceImpl implements MaterialService{

    @Autowired
    private MaterialDao materialDao;

    public List<Material> selectAll() {
        return materialDao.selectAll(new Material());
    }

    public List<Material> selectByPage(int page, String filter) {
        PageHelper.startPage(page, 10);

        return materialDao.selectMaterialByPage();
    }

    public Material select(Material material) {
        return materialDao.selectById(material.getId());
    }

    public Material insert(Material material) {
        materialDao.insertMaterial(material);
        return material;
    }

    public Material delete(Material material) {

        return null;
    }

    public Material update(Material material) {
        materialDao.updateMaterialById(material);
        return material;
    }

    public boolean deleteBatch(String ids) {
        String[] idArray = ids.split(",");
        List<String> strIdList = Arrays.asList(idArray);
        List<Integer> idList = new ArrayList<Integer>();
        for(String id : strIdList){
            idList.add(Integer.parseInt(id));
        }

        materialDao.deleteByIdList(idList);

        return true;
    }
}
