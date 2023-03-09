package com.innowireless.web.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<Map<String, Object>> getUserForAuthentication(@Param("userId") String userId);

    List<Map<String, Object>> getUserRoles(@Param("userId") String userId);
}
