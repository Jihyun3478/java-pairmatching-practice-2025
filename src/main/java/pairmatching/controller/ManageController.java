package pairmatching.controller;

import java.util.List;
import pairmatching.util.FileParser;

public class ManageController {
    public void start() {
        List<String> backendCrews = FileParser.parseBackendCrews();
        List<String> frontendCrews = FileParser.parseFrontendCrews();
    }
}
