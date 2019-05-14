/***********************************************
 * File Name: TicketService
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 14 05 2019 11:10
 ***********************************************/
package com.travel.service;

import com.travel.common.entity.TicketEntity;
import com.travel.common.vo.PageObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TicketService {
    PageObject doFindPageObjects(Integer pageCurrent, Integer pageSize);

    TicketEntity doFindObjectById(String id);

    String doUpdateObject(String ticketId, String sceneryName, String ticketName, String ticketDesc, double ticketPrice, String ticketType, String ticketTel, MultipartFile ticketPicFile) throws IOException;

    String doDeleteObject(String ticketId);

    List<String> getSceneryOptions(String cityName);

    String doSaveObject(String sceneryName, String ticketName, String ticketDesc, double ticketPrice, String ticketType, String ticketTel, MultipartFile ticketPicFile) throws IOException;
}
