package com.shoulaxiao.CustomMsg;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @program: MessageQueueTest
 * @description: 消息处理中心类
 * @author: 手拉小
 * @create: 2019-12-19 16:46
 **/
public class Broker {

    //队列存储消息的最大数量
    private final  static int MAX_SIZE=3;

    //保存消息的容器
    private static ArrayBlockingQueue<String> message=new ArrayBlockingQueue<String>(MAX_SIZE);

    /**
     * 生成消息
     * @param msg
     */
    public static void produce(String msg){
        if (message.offer(msg)){
            System.out.println("成功向消息处理中心投递消息:"+msg+",当前暂存的消息数量是"+message.size());
        }else {
            System.out.println("消息处理中心内暂存的消息已经达到最大负荷，不能投放消息");
        }
        System.out.println("====================================");
    }

    /**
     * 消费消息
     */
    public static String consume(){
        String msg=message.poll();
        if (msg!=null){
            System.out.println("已经消费消息"+msg+",当前暂存的消息数是"+message.size());
        }else {
            System.out.println("消息处理中心内没有消息可供消费");
        }
        System.out.println("===================================");
        return msg;
    }

}
