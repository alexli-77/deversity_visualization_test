package org.util;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter
@NoArgsConstructor
public class FileUtil {
    private List<String> lineList = new ArrayList<>();

    public File file;

    public FileUtil(String filename){
        try {
            file = new File(URLDecoder.decode(filename, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void reader(String name) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = bufferedReader.readLine())  != null) {
                // 处理每一行数据 .....
                processLines(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        print("BufferedReader");
    }
    public String getResourceFilePath(String name) {
        URL resourceFile = getClass().getResource(name);
        return resourceFile.getPath();
    }
    public int getFileLines() {
        return lineList.size();
    }
    private void print(String name) {
        System.out.println("*************" + name + "*******************");
    }

    private void processLines(String line) {
        lineList.add(line);
    }

    public boolean write(String data) {
        try {
            //if file doesnt exists, then create it
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(),true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.newLine();
            bufferWritter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
