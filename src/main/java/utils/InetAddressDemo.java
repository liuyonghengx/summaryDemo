package utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: InetAddressDemo
 * @Description: 根据ip字符串或主机名来获取ip对象
 * @author: liuyongheng
 * @date: 2018/8/15 17:16
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        //通过名称(ip字符串or主机名)来获取一个ip对象。
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        //addr:61.135.169.125
        System.out.println("addr:" + ip.getHostAddress());
        //name:www.baidu.com
        System.out.println("name:" + ip.getHostName());

    }
}
