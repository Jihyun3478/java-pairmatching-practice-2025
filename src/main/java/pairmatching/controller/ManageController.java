package pairmatching.controller;

import java.util.List;
import pairmatching.domain.model.Crew;
import pairmatching.util.FileParser;

public class ManageController {
    public void start() {
        List<Crew> backendCrews = FileParser.parseBackendCrews();
        List<Crew> frontendCrews = FileParser.parseFrontendCrews();
    }
}
