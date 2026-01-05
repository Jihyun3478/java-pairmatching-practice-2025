package pairmatching.util;

import java.io.File;
import java.util.List;

public class FileParser {
	private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
	private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";

	public static List<String> parseBackendCrews() {
		return getCrews(BACKEND_FILE_PATH);
	}

	public static List<String> parseFrontendCrews() {
		return getCrews(FRONTEND_FILE_PATH);
	}

	private static List<String> getCrews(String filePath) {
		return FileReader.readFile(new File(filePath));
	}
}
