package com.innowireless.web.util.xdb;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import com.innowireless.web.util.MybatisMapperUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * XDB Connection 관리
 */
@Slf4j
public class XDBConnectionManagementService {

    private final MybatisMapperUtil mapperUtil;

    private List<XDBConnection> connections = new ArrayList<>();
    private HashMap<Integer, XDBConnection> connectionsMap = new HashMap<>();

    public XDBConnectionManagementService(final List<XDBConnection.XdbConnectionInfo> list,
                                          final MybatisMapperUtil mapperUtil) {
        this.mapperUtil = mapperUtil;

        log.info("Initializing XDB Connection Service");

        for (final XDBConnection.XdbConnectionInfo info : list) {
            add(info.id, info.host, info.port);
            log.info("add XdbConnection: id = {}, host = {}, port = {}",
                info.id, info.host, info.port);
        }

        log.info("Initialized XDB Connection Service");
    }

    public void destroy() {
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

        XDBConnection newConnection = new XDBConnection(connectionInfo, this.mapperUtil);
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
