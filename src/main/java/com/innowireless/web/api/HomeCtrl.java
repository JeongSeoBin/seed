package com.innowireless.web.api;

import com.innowireless.web.util.PageUrls;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class HomeCtrl {

    /**
     * 로그인 된 상태에서 "/" 요청이 들어오면 "/index.htm"으로 redirect 시킨다.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/")
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + PageUrls.INDEX_PAGE_URL);
    }
}
