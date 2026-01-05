package pairmatching.service;

import java.util.List;
import pairmatching.domain.model.Crew;

public interface ShuffleGenerator {
    List<Crew> shuffle(List<Crew> crews);
}
