package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:日志脱敏工具类
 * @author: liuyongheng
 * @date: 2020-04-26 16:58:38
 */
public class LogFilterUtil {

    private static  String ACCT_NO_RULE = "[0-9]{16,32}|[A-Z{3}[0-9]{16,22}";
    private static  String MOBILE_NO_RULE = "[0-9]{11}";
    private static  String ID_NO_RULE = "[0-9A-Za-z]{5,18}";
    private static  String NAME_RULE = "[\u4E00-\u9FA5]{2,20}";
    private static  String RULE_FOR_XML_MSG = "[>]{1}"+"("+ACCT_NO_RULE+"|"+MOBILE_NO_RULE+"|"+ID_NO_RULE+"|"+NAME_RULE+")"+"[<]{1}";
    private static  String RULE_FOR_DB = "[\\s\\[]{1}"+"("+ACCT_NO_RULE+"|"+MOBILE_NO_RULE+"|"+ID_NO_RULE+"|"+NAME_RULE+")"+"[\\,\\]]{1}";
    private static  String RULE_FOR_SIGN_MSG = "v=\""+"("+ACCT_NO_RULE+"|"+MOBILE_NO_RULE+"|"+ID_NO_RULE+"|"+NAME_RULE+")"+"\"";
    private static  String RULE_FOR_DB_SPEC = "[=]{1}"+"("+ACCT_NO_RULE+"|"+MOBILE_NO_RULE+"|"+ID_NO_RULE+"|"+NAME_RULE+")"+"[,]{1}";



    public static void main(String[] args) {
        String oldMessage ="rset-: 13752682750";//
        String newMessage = LogFilterUtil.filterLogs(oldMessage);
        System.out.println("oldMessage="+newMessage);
    }

    /**
     *
     * @param message 原始信息
     * @return 脱敏后的信息
     */
    public static String filterLogs(String message){
        String resultMsg = message;
        try{
            String oldLogs= message;
            //处理数据库脚本
            if ((oldLogs.indexOf("rset-") != -1 && oldLogs.indexOf("Result: ") !=-1)
                ||(oldLogs. indexOf("[pstm-") !=-1 && oldLogs.indexOf("Parameters: ") !=-1)){
                oldLogs = processLogs(oldLogs, RULE_FOR_DB);
                if(oldLogs.indexOf("<?xml") !=-1){//处理xm1报文数据
                    oldLogs = processLogs (oldLogs, RULE_FOR_XML_MSG);
                }
            } else if(oldLogs.indexOf("<?xml") !=-1){
                if(oldLogs.indexOf("<T><H>") !=-1){//处理xm1报文数据
                    oldLogs = processLogs (oldLogs, RULE_FOR_SIGN_MSG);
                }else {
                    oldLogs = processLogs (oldLogs, RULE_FOR_XML_MSG);
                }
            } else if(oldLogs.indexOf("result :") !=-1){
                oldLogs = processLogs (oldLogs, RULE_FOR_DB_SPEC);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMsg;
    }
    public static String processLogs(String oldLogs, String exp){
        Pattern pattern = Pattern. compile(exp);
        Matcher matcher = pattern. matcher(oldLogs);
        StringBuffer result= new StringBuffer();
        int start = 0;
        int index = 0;
        int childIndex = 0;
        String value1="";
        String value0="";
        while (matcher. find()) {
                value0 = matcher.group(0);
                value1 = matcher.group(1);
                index= oldLogs.indexOf(value0, start);
                childIndex = value0.indexOf(value1);
                result. append(oldLogs. substring(start, index)).append(value0. substring(0, childIndex))
                                .append(getProcessedInfo(value1)). append(value0. substring(childIndex+value1. length()));
                start = index + value0.length();
        }
         result.append(oldLogs. substring(start, oldLogs. length()));
         return result.toString();
    }

    /**
     *
     * @param info
     * @return 进行脱敏后的处理
     */
    private static String getProcessedInfo(String info){
        StringBuffer resultBuffer = new StringBuffer();
        if (Pattern.matches(NAME_RULE, info)){
            for (int i=0;i< info.length(); i++){
                if(i==0) {
                    resultBuffer. append(info.charAt(i));
                } else{
                resultBuffer. append("*");
                }
            }
        } else if (info.length() ==11){
            for (int i = 0;i< info.length(); i++){
                if (i<3 || i>=info.length()-4) {
                    resultBuffer. append(info.charAt(i));
                } else{
                    resultBuffer. append("*");
                }
            }
        } else {
            for (int i = 0;i< info.length(); i++){
                if (i<6 || i>=info.length()-4) {
                    resultBuffer. append(info.charAt(i));
                } else{
                    resultBuffer. append("*");
                }
            }
        }
        return resultBuffer.toString();
    }


}
