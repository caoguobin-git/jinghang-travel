package com.travel.service;

import com.travel.common.entity.SceneryEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SceneryService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    SceneryEntity doFindObjectById(String id);

    String doSaveObject(String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException;

    String doUpdateObject(String sceneryId, String cityName, String sceneryName, String sceneryDesc, MultipartFile sceneryPicFile) throws IOException;

    String doDeleteObject(String sceneryId);

    PageObject getScenerysByCityName(Integer pageCurrent, Integer pageSize,String cityName);

    List<SceneryEntity> doGetSceneryByCondition(String condition);
}
