package com.shoulaxiao.CustomMsg;

/**
 * @program: MessageQueueTest
 * @description: 客户端
 * @author: 手拉小
 * @create: 2019-12-19 17:16
 **/
public class ConsumeClient {

    public static void main(String[] args) throws Exception{
        MqClient client=new MqClient();
        String message=client.consume();
        System.out.println("获取的消息为:"+message);
    }
}
