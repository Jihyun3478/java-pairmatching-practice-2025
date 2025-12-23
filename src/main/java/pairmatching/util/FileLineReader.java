package pairmatching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLineReader implements FileReader {
    @Override
    public List<String> readFile(String filePath) {
        List<String> crews = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                crews.add(fileLine);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식의 파일입니다.");
        }
        return crews;
    }
}
