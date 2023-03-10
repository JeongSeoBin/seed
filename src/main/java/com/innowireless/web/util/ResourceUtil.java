package com.innowireless.web.util;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class ResourceUtil {

    /**
     * class.getResourceAsStream()은 caching을 하므로, caching을 하지 않으려면 이 함수를 사용한다.
     *
     * @param name classpath 중에서 찾으려면, 맨 앞에 '/'가 없어야 한다.
     * @return resource string
     * @throws IOException
     */
    public static String readResourceAsStringWithoutCaching(String name) throws IOException {
        URL res = ResourceUtil.class.getClassLoader().getResource(name);

        if (res == null) {
            throw new IOException("resource \"" + name + "\" not found");
        }

        URLConnection resConn = res.openConnection();
        resConn.setUseCaches(false);

        try (InputStream inputStream = resConn.getInputStream()) {
            StringBuilder sb = new StringBuilder();

            try (BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }

            return sb.toString();
        }
    }
}
