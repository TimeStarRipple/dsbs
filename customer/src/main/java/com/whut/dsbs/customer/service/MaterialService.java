package com.whut.dsbs.customer.service;

import com.whut.dsbs.common.dto.Material;

import java.util.List;

/**
 * 消费者的材料业务逻辑接口
 *
 * Created by zyb on 2017-04-30.
 */
public interface MaterialService {

    List<Material> getMaterialByPage(int page, String filter);

    boolean deleteMaterialByIds(String ids);

    Material createMaterial(Material data);

    Material getMaterialById(int id);

    Material updateMaterial(Material data);
}
