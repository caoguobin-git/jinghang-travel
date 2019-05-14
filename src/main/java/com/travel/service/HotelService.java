/***********************************************
 * File Name: HotelService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.HotelEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface HotelService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    HotelEntity doFindObjectById(String id);

    String doUpdateObject(String hotelId,String sceneryName, String hotelName,String hotelDesc,double hotelPrice, MultipartFile hotelPicFile) throws IOException;

    String doDeleteObject(String hotelId);

    List<String> getSceneryOptions(String cityName);

    String doSaveObject(String sceneryName, String hotelName, String hotelDesc, double hotelPrice, MultipartFile hotelPicFile) throws IOException;
}
