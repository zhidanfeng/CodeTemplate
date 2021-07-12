package template.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\dump\heap.hprof -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
 */
public class OomApp {
    public static void main(String[] args) {
        List<Byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new Byte[1024 * 1024]); // 每次增加一个1M大小的数组对象
        }
    }
}
