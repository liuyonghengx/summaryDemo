package program;

import java.io.*;
import java.util.List;
import java.util.Vector;

/**
 * @Description: 请对D盘下A.txt文件中的每行数据做升序排列后写入B.txt文件中；A.txt文件描述：通过“|”拼接的多行数字，
 * 例如：“10|14|5|10|6|3”有多行。
 * @author: liuyongheng
 * @date: 2020/3/15 17:09
 */
public class ReadFileSort {

    public static void main(String[] args) {
        File file = new File("D:\\A.txt");
        if (file.exists()) {

            try {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                File newfile = new File("D:\\B.txt"); // 如果文件不存在下一步会创建出来
                FileOutputStream fos = new FileOutputStream(newfile);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                BufferedWriter bw = new BufferedWriter(osw);

                String str;
                String[] line;
                str = br.readLine();
                int temp;

                while (str != null) {
                    line = str.split("\\|");
                    int[] intArray = new int[line.length];
                    for (int i = 0; i < line.length; i++) {
                        intArray[i] = Integer.parseInt(line[i]);
                    }

                    for (int m = 0; m < intArray.length - 1; m++) {
                        for (int n = 0; n < intArray.length - m - 1; n++) {
                            if (intArray[n] > intArray[n + 1]) {
                                temp = intArray[n];
                                intArray[n] = intArray[n + 1];
                                intArray[n + 1] = temp;
                            }
                        }
                    }

                     List<String> lists = new Vector<String>();
                    for (int j = 0; j < intArray.length; j++) {
                        lists.add(String.valueOf(intArray[j]));
                    }

                    for (int j = 0; j < lists.size(); j++) {
                        bw.write("|" + lists.get(j));
                    }
                    bw.write("\n");
                    str = br.readLine();

                }

                br.close();
                isr.close();
                fis.close();
                bw.close();
                osw.close();
                fos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
