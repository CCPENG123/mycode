package cn.ccp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxTData {
    //txt文件路径
    private String filePath;
    FileWriter fw = null;
    BufferedWriter bw = null;

    /**
     * 构造对象
     * @param filePath
     */
    public TxTData(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 打开文件流
     *
     * @throws IOException
     */
    public void open() throws IOException {
        //文件不存在则创建文件
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        fw = new FileWriter(filePath);
        bw = new BufferedWriter(fw);
    }

    /**
     * 关闭文件流
     * @param content
     * @throws IOException
     */
    public void wirteTxt(String content) throws IOException {
        bw.write(content);
    }

    public void cloese() throws IOException {
        bw.flush();
        bw.close();
    }
}
