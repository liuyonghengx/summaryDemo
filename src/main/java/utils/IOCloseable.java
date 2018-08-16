package utils;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import org.junit.Test;


/**
 * @ClassName: IOCloseable
 * @Description: 批量对流进行关闭
 * @author: liuyongheng
 * @date: 2018/8/16 10:43
 */
public class IOCloseable {
    @Test
    public void test() {
        InputStream is = null;
        BufferedWriter bw = null;
        ZipInputStream zis = null;
        try {
            is = new FileInputStream("c:/test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(is, bw, zis);
        }

    }

    /**
     * 对流进行关闭
     * @param streams
     */
    public void close(Closeable... streams) {
        for (Closeable stream : streams) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}