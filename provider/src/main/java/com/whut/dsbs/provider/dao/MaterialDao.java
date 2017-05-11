package com.whut.dsbs.provider.dao;

import com.whut.dsbs.common.dto.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 材料dao的接口
 *
 * Created by zyb on 2017-04-30.
 */
@Mapper
public interface MaterialDao{

    List<Material> selectMaterialByPage();

    boolean deleteByIdList(List<Integer> list);

    boolean insertMaterial(Material material);

    Material selectById(int id);

    boolean updateMaterialById(Material material);

    List<Material> selectAll(Material material);

    void deleteById(Material material, Integer id);
}
