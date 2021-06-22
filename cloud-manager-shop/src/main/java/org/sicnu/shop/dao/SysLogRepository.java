package org.sicnu.shop.dao;

import org.sicnu.shop.model.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public interface SysLogRepository extends JpaRepository<SysLog, Integer> {
    @Query(value = "select data_explain type, count(*) count from sys_log  group by data_explain",nativeQuery=true)
    ArrayList<Map<String, Integer>> getLogTypeAndCount();

    @Query(value = "SELECT DATE_FORMAT( create_time, \'%Y-%m-%d\' ) AS create_time, COUNT(*) AS total FROM sys_log  GROUP BY DATE_FORMAT( create_time, \'%Y-%m-%d\' )",nativeQuery=true)
    ArrayList<Map<String, Integer>> getLogInfo();
}
