package com.dongzj.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 17:43
 */
public class ServerThread implements Runnable {

    private Socket client = null;

    private List<Object> serviceList = null;

    public ServerThread(Socket client, List<Object> serviceList) {
        this.client = client;
        this.serviceList = serviceList;
    }

    public void run() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;

        try {
            input = new ObjectInputStream(client.getInputStream());
            output = new ObjectOutputStream(client.getOutputStream());

            //读取客户端需要访问的service
            Class serviceClass = (Class) input.readObject();
            //找到服务类
            Object obj = findService(serviceClass);
            if (obj == null) {
                output.writeObject(serviceClass.getName() + "服务未发现");
            } else {
                //利用反射调用该方法，返回结果
                try {
                    String methodName = input.readUTF();
                    Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                    Object[] arguments = (Object[]) input.readObject();
                    Method method = obj.getClass().getMethod(methodName, parameterTypes);
                    Object result = method.invoke(obj, arguments);
                    output.writeObject(result);
                } catch (Throwable e) {
                    output.writeObject(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
                input.close();
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Object findService(Class serviceClass) {
        for (Object obj : serviceList) {
            boolean isFather = serviceClass.isAssignableFrom(obj.getClass());
            if (isFather) {
                return obj;
            }
        }
        return null;
    }

}
