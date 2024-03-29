package com.shoulaxiao.CustomMsg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: MessageQueueTest
 * @description: 对外服务类
 * @author: 手拉小
 * @create: 2019-12-19 16:54
 **/
public class BrokerServer implements Runnable{

    public static int  SERVICE_PORT=9999;

    private final Socket socket;


    public BrokerServer(Socket socket){
        this.socket=socket;
    }



    public void run() {
        try (BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out=new PrintWriter(socket.getOutputStream())){
            while (true){
                String str=in.readLine();
                if (str==null){
                    continue;
                }
                System.out.println("接受到原始数据:"+str);
                if (str.equals("CONSUME")){
                    String message=Broker.consume();
                    out.println(message);
                    out.flush();
                }else {
                    Broker.produce(str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket(SERVICE_PORT);
        while (true){
            BrokerServer brokerServer=new BrokerServer(server.accept());
            new Thread(brokerServer).start();
        }

    }
}
