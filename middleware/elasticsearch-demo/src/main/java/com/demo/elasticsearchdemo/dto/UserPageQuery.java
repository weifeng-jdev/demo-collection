package com.demo.elasticsearchdemo.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @className: UserPageQuery
 * @package com.demo.elasticsearchdemo.dto
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
public class UserPageQuery implements Pageable {
    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 0;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
