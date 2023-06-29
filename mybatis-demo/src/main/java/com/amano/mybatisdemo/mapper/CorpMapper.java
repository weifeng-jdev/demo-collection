package com.amano.mybatisdemo.mapper;

import com.amano.mybatisdemo.entity.Corp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: CorpMapper
 * @package com.amano.mybatisdemo.mapper
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Mapper
public interface CorpMapper extends BaseMapper<Corp> {
}
