package com.amano.mybatisdemo.service.impl;

import com.amano.mybatisdemo.entity.Corp;
import com.amano.mybatisdemo.entity.dto.CorpPageQuery;
import com.amano.mybatisdemo.mapper.CorpMapper;
import com.amano.mybatisdemo.service.ICorpService;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @className: CorpServiceImpl
 * @package com.amano.mybatisdemo.service.impl
 * @description: 企业service，继承ServiceImpl，实现IService接口实现基本的增删改查
 * @author: amano
 * @date: 2023/6/28
 **/
@Service
public class CorpServiceImpl extends ServiceImpl<CorpMapper, Corp> implements ICorpService {
    /**
     * 使用selectPage进行带条件的分页查询
     */
    @Override
    public Page<Corp> listCorpPage(CorpPageQuery query) {
        LambdaQueryChainWrapper<Corp> condition = new LambdaQueryChainWrapper<>(this.baseMapper).like(Corp::getName, query.getName());
        return this.baseMapper.selectPage(new Page<>(query.getPageNum(), query.getPageSize()), condition);
    }
}
