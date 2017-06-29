package toolutils;

import java.io.*;

/**
 * Created by C_C on 2017/6/29.
 * txt文件的读写
 */
public class txtUtils {
    //读取txt文件的内容
    public static String readFile(String filePath){
        try {
            File file = new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),"GBK");//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null,words = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    words += lineTxt + "\n";
                }
                read.close();
                return words;
            }else{
                return "404";
            }
        } catch (IOException e) {
            return "500";
        }
    }
}
