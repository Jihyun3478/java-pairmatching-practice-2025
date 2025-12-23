package pairmatching.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pairmatching.Course;
import pairmatching.Crew;
import pairmatching.Crews;

public class BackendInfoReader implements FileReader {
    @Override
    public Crews readFile(String filePath) {
        List<Crew> crews = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String fileLine;
            while ((fileLine = br.readLine()) != null) {

                crews.add(new Crew(Course.BACKEND, fileLine));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식의 파일입니다.");
        }
        return new Crews(crews);
    }
}
