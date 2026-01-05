package pairmatching.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Crew;

public class FileParser {
	private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
	private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";

	public static List<Crew> parseBackendCrews() {
		return getCrews(BACKEND_FILE_PATH, Course.BACKEND);
	}

	public static List<Crew> parseFrontendCrews() {
		return getCrews(FRONTEND_FILE_PATH, Course.FRONTEND);
	}

	private static List<Crew> getCrews(String backendFilePath, Course course) {
		List<String> names = FileReader.readFile(new File(backendFilePath));
		List<Crew> crews = new ArrayList<>();
		for (String name : names) {
			crews.add(new Crew(course, name));
		}
		return crews;
	}
}
