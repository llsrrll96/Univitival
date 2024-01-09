package festival.server.univ.util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    public static Map<String,Object> readIDPWFromFile() {
        Map<String,Object> map = new HashMap<String,Object>();
        String[] idpw = new String[2];
        int i = 0;
        try{
            //파일 객체 생성
            File file = new File("C:\\files\\idpw");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
                idpw[i++]= line;
            }
            //.readLine()은 끝에 개행문자를 읽지 않는다.
            bufReader.close();
        } catch (FileNotFoundException e) {
            // TODO: handle exception
        } catch(IOException e) {
            System.out.println(e);
        }
        map.put("username", idpw[0]);
        map.put("password", idpw[1]);
        return map;
    }
}
