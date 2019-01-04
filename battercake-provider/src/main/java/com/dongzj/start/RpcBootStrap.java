package com.dongzj.start;

import com.dongzj.rpc.RpcProvider;
import com.dongzj.service.BatterCakeService;
import com.dongzj.service.impl.BatterCakeServiceImpl;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 17:53
 */
public class RpcBootStrap {

    public static void main(String[] args) throws Exception {
        BatterCakeService batterCakeService = new BatterCakeServiceImpl();
        //发布卖煎饼的服务，注册在20006端口
        RpcProvider.export(20006, batterCakeService);
    }
}
