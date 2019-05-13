package com.travel.service;

import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.PageObject;

public interface SceneryService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    SceneryEntity doFindObjectById(String id);
}
