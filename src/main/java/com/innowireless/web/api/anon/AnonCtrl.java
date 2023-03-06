package com.innowireless.web.api.anon;

import com.innowireless.web.util.ValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/anon")
@Tag(name = "미 가입 사용자 관련 API")
public class AnonCtrl {

    @Operation(summary = "사용자의 로그인 여부 확인")
    @GetMapping(value = "isUserLoggedin")
    public boolean isUserLoggedin() {
        // FIXME: Import AuthenticationUtil class
        // return AuthenticationUtil.isUserLoggedin();
        return true;
    }

    /**
     * 클라이언트에서 application.properties에 정의된 설정값이 필요할 때 사용
     * login.js, loginiso.js, main.js 각각 필요할 때마다 호출해 줘야 한다.
     *
     * @return SiteInfo
     */
    @Operation(summary = "사이트 정보 가져오기")
    @GetMapping(value = "siteInfo")
    public SiteInfo siteInfo() {
        SiteInfo siteInfo = new SiteInfo();
        siteInfo.enableRegister = enableRegister;

        return siteInfo;
    }

    private static class SiteInfo {
        public boolean enableRegister;
    }

    @Operation(summary = "사용자 가입 신청")
    @PostMapping(value = "/requestSignUp")
    public void requestSignUp(
        @RequestBody @Valid GuestInfoAddArgs args, BindingResult result) {
        ValidationUtil.checkAnnotationValidationResult(result);

        // TODO: 주석 해제
        // if (!passwordValidate.isValid(args.password, args.userId))
        //     throw new ApiException(ErrorCodes.PASSWORD_RULE_INVALID, "password invalid");
        // tt.execute(new TransactionCallbackWithoutResult() {
        //     @Override
        //     protected void doInTransactionWithoutResult(TransactionStatus status) {
        //         int cnt = guestMapper.requestSignUp(args.userId, args.userName, pe.encode(args.password),
        //             args.phone, args.email, args.purpose,
        //             args.dept, args.mac1, args.mac2, DBUtil.toLocalDateTime(args.requestDate));
        //         if (cnt != 1)
        //             throw new ApiException(ErrorCodes.DB_ERROR, "failed to insert user");
        //
        //         String roleId = guestMapper.getGuestRoleId();
        //
        //         cnt = guestMapper.insertUserToRole(args.userId, UUID.fromString(roleId));
        //         if (cnt != 1)
        //             throw new ApiException(ErrorCodes.DB_ERROR, "failed to insert user to role");
        //
        //         guestMapper.setUserUpdateHistory(args.userId);
        //         userService.addUserManageLog(UserLogTypes.REGISTER, args.userId,
        //             null, args.infoToString());
        //     }
        // });
    }

    @Operation(summary = "맥주소 및 패킷뷰어 로그 저장 권한여부 출력")
    @GetMapping(value = "/macpermit.txt")
    public void getMacAddressWithPacketAuthority(HttpServletResponse response) throws IOException {
        // TODO: 주석 해제
        // List<Map<String, Object>> rows = guestMapper.getMacAddressWithPacketAuthority();
        // response.setContentType("text/plain");
        // response.setCharacterEncoding("UTF-8");
        // PrintWriter writer = response.getWriter();
        // for (Map<String, Object> row : rows) {
        //     String mac1 = (String) row.get("mac_1");
        //     String mac2 = (String) row.get("mac_2");
        //     if (mac1 == null)
        //         mac1 = "";
        //     if (mac2 == null)
        //         mac2 = "";
        //     int hasAuthority = (Integer) row.get("has_authority");
        //     writer.write(mac1 + "," + mac2 + "," + hasAuthority + "\r\n");
        // }
        // writer.flush();
        // writer.close();
    }

    @Operation(summary = "사용자 패스워드 변경")
    @PostMapping(value = "/passwordChange")
    public void passwordChange(
        @RequestBody @Valid changePasswordArgs args, BindingResult result) {
        ValidationUtil.checkAnnotationValidationResult(result);

        // 이걸 과연 guest가 해도 되는건가? user에 있는거 그대로 갖고온건데
        // TODO: 주석 해제
        // String pw = guestMapper.getUserPw(args.userId);
        //
        // if (StringUtils.isBlank(pw))
        //     throw new ApiException(ErrorCodes.DB_ERROR, "failed to get user pw");
        // if (!pe.matches(args.password, pw))
        //     throw new ApiException(ErrorCodes.PASSWORD_MISMATCH, "old password not match");
        //
        // if (!passwordValidate.isValid(args.newPassword, args.userId))
        //     throw new ApiException(ErrorCodes.PASSWORD_RULE_INVALID, "new password invalid");
        //
        // // 이 녀석도
        // int cnt = guestMapper.changePw(args.userId, pe.encode(args.newPassword), args.pwUpdateDate);
        //
        // if (cnt != 1)
        //     throw new ApiException(ErrorCodes.NOT_FOUND, "failed to update user pw");
        //
        // userService.addUserManageLog(UserLogTypes.CHANGEPASSWORD, args.userId,
        //     null, null);
    }

    private static class GuestInfoAddArgs {
        @NotBlank
        public String userId;
        @NotBlank
        public String userName;
        @NotBlank
        public String password;
        @NotBlank
        public String phone;
        @NotBlank
        public String email;
        @NotBlank
        public String purpose;
        @NotBlank
        public String dept;
        public String mac1;
        public String mac2;
        @NotBlank
        public String requestDate;

        public String infoToString() {
            StringBuilder str = new StringBuilder();
            if (userName != null)
                str.append(", userName: ").append(userName);
            if (phone != null)
                str.append(", phone: ").append(phone);
            if (email != null)
                str.append(", email: ").append(email);
            if (purpose != null)
                str.append(", purpose: ").append(purpose);
            if (dept != null)
                str.append(", dept: ").append(dept);
            if (mac1 != null)
                str.append(", mac1: ").append(mac1);
            if (mac2 != null)
                str.append(", mac2: ").append(mac2);
            return str.toString().substring(2, str.length());
        }
    }

    private static class changePasswordArgs {
        @NotBlank
        public String userId;
        @NotBlank
        public String password;
        @NotBlank
        public String newPassword;
        public LocalDateTime pwUpdateDate;
    }

    @Autowired
    private TransactionTemplate tt;

    // @Autowired
    // private PasswordEncoder pe;
    //
    // @Autowired
    // private UserService userService;
    //
    // @Autowired
    // private GuestMapper guestMapper;
    //
    // @Autowired
    // private UserLogMapper userLogMapper;
    //
    // @Autowired
    // private PasswordValidate passwordValidate;

    @Value("${atas.site.enableRegister:true}")
    private boolean enableRegister;
}
