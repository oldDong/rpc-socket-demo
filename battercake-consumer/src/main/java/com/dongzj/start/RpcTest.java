package com.dongzj.start;

import com.dongzj.rpc.RpcConsumer;
import com.dongzj.service.BatterCakeService;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2018/11/12
 * Time: 18:07
 */
public class RpcTest {

    public static void main(String[] args) {
        BatterCakeService batterCakeService = RpcConsumer.getService(BatterCakeService.class, "127.0.0.1", 20006);
        String result = batterCakeService.sellBatterCake("鸭蛋");
        System.out.println(result);
    }
}
