package com.dongzj.rpc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 17:39
 */
public class RpcProvider {

    /**
     * 存储注册的服务列表
     */
    private static List<Object> serviceList;

    public static void export(int port, Object... services) throws Exception{
        serviceList = Arrays.asList(services);
        ServerSocket server = new ServerSocket(port);
        Socket client = null;
        while (true) {
            //阻塞等待输入
            client = server.accept();
            new Thread(new ServerThread(client, serviceList)).start();
        }
    }
}
