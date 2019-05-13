package com.travel.serviceImpl;

import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.PageObject;
import com.travel.mapper.SceneryMapper;
import com.travel.service.SceneryService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneryServiceImpl implements SceneryService {

    @Autowired
    private SceneryMapper sceneryMapper;

    @Override
    public PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize) {
        if (pageSize == null) {
            pageSize = 20;
        }
        int pageCount = getPageCount(pageSize);
        if (pageCurrent == null || pageCurrent < 1) {
            pageCurrent = 1;
        }
        if (pageCurrent > pageCount) {
            pageCurrent = pageCount;
        }
        PageObject pageObject = new PageObject();
        pageObject.setTotal(sceneryMapper.getPageCount());
        pageObject.setPageCount(pageCount);
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        List<Object> list = sceneryMapper.doFindPageObjects(pageCurrent,new RowBounds(pageCurrent-1, pageSize));
        System.out.println(list.size());
        for (Object o : list) {
            System.out.println(o.toString());
        }
        pageObject.setRecords(list);
        return pageObject;
    }

    @Override
    public SceneryEntity doFindObjectById(String id) {
        SceneryEntity sceneryEntity=sceneryMapper.doFindObjectById(id);
        return sceneryEntity;
    }


    public int getPageCount(int pageSize) {
        return sceneryMapper.getPageCount() / pageSize + 1;
    }
}
