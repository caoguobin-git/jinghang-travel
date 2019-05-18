package com.travel.service;

import com.travel.common.entity.StrategyEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StrategyService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize, String sceneryName);

    StrategyEntity doFindObjectById(String id);

    String doDeleteObject(String strategyId);

    PageObject getStrategysByCityName(Integer pageCurrent, Integer pageSize, String cityName);

    String doSaveObject(String sceneryName, String strategyTitle, String strategyContent, MultipartFile[] strategyPicFile) throws IOException;

    List<String> getSceneryOptions(String cityName);

    String doUpdateObject(String strategyId, String sceneryName, String strategyTitle, String strategyContent, MultipartFile[] strategyPicFile) throws IOException;
}
