package com.whut.dsbs.provider.service.impl;

import com.whut.dsbs.common.dto.MaterialType;
import com.whut.dsbs.common.service.MaterialTypeService;
import com.whut.dsbs.provider.dao.MaterialTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 材料类型业务逻辑实现类
 *
 * Created by zyb on 2017-05-30.
 */
@Service("materialTypeService")
@Transactional
public class MaterialTypeServiceImpl extends BaseServiceImpl<MaterialType> implements MaterialTypeService{

    @Autowired
    private MaterialTypeDao materialTypeDao;

    public List<MaterialType> selectAll(){
        return materialTypeDao.selectAll();
    }
}
