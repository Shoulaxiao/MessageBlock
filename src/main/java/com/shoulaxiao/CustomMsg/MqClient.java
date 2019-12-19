package com.shoulaxiao.CustomMsg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @program: MessageQueueTest
 * @description: 消息客户端
 * @author: 手拉小
 * @create: 2019-12-19 17:06
 **/
public class MqClient {

    /**
     * 生产消息
     * @param message
     * @throws Exception
     */
    public static void produce(String message)throws Exception{
        Socket socket=new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);
        try( PrintWriter out=new PrintWriter(socket.getOutputStream())){
           out.println(message);
           out.flush();
        }
    }

    /**
     * 消费消息
     * @return
     * @throws Exception
     */
    public static String consume() throws Exception{
        Socket socket=new Socket(InetAddress.getLocalHost(),BrokerServer.SERVICE_PORT);

        try(BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out=new PrintWriter(socket.getOutputStream())){
            out.println("CONSUME");
            out.flush();
            String messge=in.readLine();
            return messge;
        }
    }
}
