package com.innowireless.web.mapper;

import com.innowireless.web.api.KeyOrOffsetPagingItem;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserMapper {

    List<Map<String, Object>> getUserPage(
        @Param("pagingItem") KeyOrOffsetPagingItem pagingItem,
        @Param("isRegistered") String isRegistered,
        @Param("userId") String userId,
        @Param("userNm") String userNm,
        @Param("roleId") UUID roleId
    );

    List<Map<String, Object>> getUserPhoneRestrict(
        @Param("pagingItem") KeyOrOffsetPagingItem pagingItem
    );

    int updateRestrictChecked(
        @Param("userId") String userId,
        @Param("isRestrictChecked") boolean isRestrictChecked
    );

    List<Map<String, Object>> getUsersInfoByReg(
        @Param("isRegistered") String isRegistered,
        @Param("userId") String userId,
        @Param("userNm") String userNm,
        @Param("roleId") UUID roleId
    );

    List<Map<String, Object>> getAllUserRoles();

    List<Map<String, Object>> getAllUserId();

    List<Map<String, Object>> getUserForAuthentication(@Param("userId") String userId);

    List<Map<String, Object>> getUserState(@Param("userId") String userId);

    List<Map<String, Object>> getUserRoles(@Param("userId") String userId);

    List<Map<String, Object>> getUserPrivileges(@Param("userId") String userId);

    Map<String, Object> getUserInfo(@Param("userId") String userId);

    int addUser(
        @Param("userId") String userId,
        @Param("userName") String userName,
        @Param("password") String password,
        @Param("phone") String phone,
        @Param("email") String email,
        @Param("purpose") String purpose,
        @Param("allowedIp") String allowedIp,
        @Param("allowedId") String allowedId,
        @Param("dept") String dept,
        @Param("mac1") String mac1,
        @Param("mac2") String mac2,
        @Param("expireDate") LocalDateTime expireDate,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate
    );

    // 관리자 역할 - 사용자 정보 수정
    int updateUserInfo(
        @Param("userId") String userId,
        @Param("userName") String userName,
        @Param("phone") String phone,
        @Param("email") String email,
        @Param("purpose") String purpose,
        @Param("allowedIp") String allowedIp,
        @Param("allowedId") String allowedId,
        @Param("dept") String dept,
        @Param("mac1") String mac1,
        @Param("mac2") String mac2,
        @Param("isRegistered") String isRegistered,
        @Param("expireDate") LocalDateTime expireDate,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate
    );

    // 사용자 역할 - 사용자 정보 수정
    int editUserInfo(
        @Param("userId") String userId,
        @Param("userName") String userName,
        @Param("password") String password,
        @Param("phone") String phone,
        @Param("email") String email,
        @Param("purpose") String purpose,
        @Param("dept") String dept,
        @Param("mac1") String mac1,
        @Param("mac2") String mac2,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate
    );

    int bulkUpdateUsersInfo(
        @Param("userIds") List<String> userId,
        @Param("dept") String dept,
        @Param("isRegistered") String isRegistered,
        @Param("allowedId") String allowedId,
        @Param("purpose") String purpose,
        @Param("expireDate") LocalDateTime expireDate,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate
    );

    String getUserPw(@Param("userId") String userId);

    int changePw(
        @Param("userId") String userId,
        @Param("password") String password);

    int resetPw(
        @Param("userId") String userId,
        @Param("password") String password,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate,
        @Param("expireDate") LocalDateTime expireDate,
        @Param("pwUpdateId") String pwUpdateId
    );

    int deleteCompleteUsers(@Param("userId") String userId);

    int insertUserToRole(
        @Param("userId") String userId,
        @Param("roleId") UUID roleId);

    int updateIsoUserInfo(
        @Param("userId") String userId,
        @Param("userName") String userName,
        @Param("teamName") String teamName);

    int deleteUserFromRole(@Param("userId") String userId);

    int getNewUserReqCnt();

    Map<String, Object> getPwUpdateDate(@Param("userId") String userId);

    Map<String, Object> getExpireDate(@Param("userId") String userId);

    int getRegUserCnt();

    int unlockAccount(
        @Param("userId") String userId,
        @Param("password") String password,
        @Param("pwUpdateDate") LocalDateTime pwUpdateDate,
        @Param("pwUpdateId") String pwUpdateId
    );

    int getLoginFailCnt(
        @Param("userId") String userId);

    int increaseLoginFailCnt(
        @Param("userId") String userId,
        @Param("cnt") int cnt);

    int setUserLock(
        @Param("userId") String userId,
        @Param("cnt") int cnt);

    int isUserLock(
        @Param("userId") String userId);

    int initUserLoginFailCnt(
        @Param("userId") String userId,
        @Param("loginIp") String loginIp);

    String getAllowedIp(
        @Param("userId") String userId
    );

    int setAllowedIp(
        @Param("userId") String userId,
        @Param("allowedIp") String allowedIp
    );

    int extendExpireDate(
        @Param("userId") String userId,
        @Param("expireDate") LocalDateTime expireDate
    );

    List<Map<String, String>> getExpiredOverUsers(
        @Param("deleteTermDays") int deleteTermDays
    );

    String getRegistered(
        @Param("userId") String userId
    );

    void setUserUpdateHistory(
        @Param("userId") String userId
    );
}
