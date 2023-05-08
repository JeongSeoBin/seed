package com.innowireless.web.util.xdb;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * XDB Connection 관리
 */
@Service
@ConditionalOnBean(XDBProperties.class)
@Slf4j
@RequiredArgsConstructor
public class XDBConnectionManagementService {

    private final XDBProperties properties;

    private List<XDBConnection> connections = new ArrayList<>();
    private HashMap<Integer, XDBConnection> connectionsMap = new HashMap<>();

    @PostConstruct
    public void postConstruct() {
        log.info("Initializing XDB Connection Service");

        /*
        List<Map<String, Object>> rows = xdbMapper.getEnabledXdbs();

        for (Map<String, Object> row : rows) {
            add(((BigDecimal) row.get("XDB_ID")).intValue(),
                (String) row.get("HOST"),
                ((BigDecimal) row.get("PORT")).intValue());
            log.info("add XdbConnection: id = {}, host = {}, port = {}",
                ((BigDecimal) row.get("XDB_ID")).intValue(),
                row.get("HOST"),
                ((BigDecimal) row.get("PORT")).intValue());
        }
        */

        final List<XDBProperties.Config> list;

        if (Objects.nonNull(properties.getConfig())) {
            list = new ArrayList<>();
            list.add(properties.getConfig());
        } else {
            list = properties.getConfigs();
        }

        for (final XDBProperties.Config config : list) {
            add(config.getId(), config.getHost(), config.getPort());
            log.info("add XdbConnection: {}", config);
        }

        log.info("Initialized XDB Connection Service");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("Destroying XDB Connection Service");
    }

    public synchronized void add(Integer id, String host, Integer port) {
        XDBConnection.XdbConnectionInfo connectionInfo = new XDBConnection.XdbConnectionInfo();
        connectionInfo.id = id;
        connectionInfo.host = host;
        connectionInfo.port = port;
        XDBConnection connection = connectionsMap.get(id);

        if (connection != null) {
            log.warn("Xdb(ID:{}, {}:{}) is already exist. the existing xdb will be replaced with new one",
                id, connection.getHost(), connection.getPort());
            remove(id);
        }

        XDBConnection newConnection = new XDBConnection(connectionInfo);
        connections.add(newConnection);
        connectionsMap.put(id, newConnection);
    }

    public synchronized void remove(Integer id) {
        XDBConnection connection = connectionsMap.get(id);
        if (connection != null) {
            log.info("Removing Xdb({}:{}) connection", connection.getHost(), connection.getPort());
            connectionsMap.remove(id);
            connections.remove(connection);
            connection.destroy();
        }
    }

    public synchronized void modify(Integer id, String host, Integer port) {
        remove(id);
        add(id, host, port);
    }

    public synchronized XDBConnection get(Integer xdbId) {
        if (xdbId == null)
            throw new ApiException(ErrorCodes.INVALID_XDB, ("Xdb ID is null"));

        XDBConnection xdbConnection = connectionsMap.get(xdbId);
        if (xdbConnection == null)
            throw new ApiException(ErrorCodes.INVALID_XDB, ("Xdb ID:" + xdbId));
        log.debug("Xdb({}:{})", xdbConnection.getHost(), xdbConnection.getPort());
        return xdbConnection;
    }
}
