package org.sicnu.shop.service;

import org.sicnu.shop.model.AjaxResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

public interface SysLogService {
    void addLog(HttpServletRequest request, AjaxResponse aj);
    ArrayList<Map<String,Integer>> getLogTypeAndCount();
    ArrayList<Map<String,Integer>> getLogInfo();

}
