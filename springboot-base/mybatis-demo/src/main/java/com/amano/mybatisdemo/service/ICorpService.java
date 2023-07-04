package com.amano.mybatisdemo.service;

import com.amano.mybatisdemo.entity.Corp;
import com.amano.mybatisdemo.entity.dto.CorpPageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @className: ICorpService
 * @package com.amano.mybatisdemo.service
 * @description: 租户service，继承mybatis-plus的iservice接口，实现基本的增删改查操作
 * @author: amano
 * @date: 2023/6/28
 **/
public interface ICorpService extends IService<Corp> {
    Page<Corp> listCorpPage(CorpPageQuery query);

}
