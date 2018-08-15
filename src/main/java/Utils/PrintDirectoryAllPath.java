package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @ClassName: PrintDirectoryAllPath
 * @Description: 在控制台打印出一个文件夹下的文件的所有路径
 * @author: liuyongheng
 * @date: 2018/8/15 16:45
 */
public class PrintDirectoryAllPath {
    public static void main(String[] args) {
        try {
            boolean flag = readfile("D:\\ideaWorkSpace\\summary\\summaryDemo");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                //System.out.println("文件");
                System.out.println("path=" + file.getPath());
                //System.out.println("absolutepath=" + file.getAbsolutePath());
                //System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                //System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        //System.out.println("absolutepath="
                        //          + readfile.getAbsolutePath());
                        //System.out.println("name=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile() Exception:" + e.getMessage());
        }
        return true;
    }
}
