package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;

public class RandomShuffleGenerator implements ShuffleGenerator {
    @Override
    public List<Crew> shuffle(List<Crew> crews) {
        List<String> names = crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());

        List<String> shuffledNames = Randoms.shuffle(names);

        List<Crew> result = new ArrayList<>();
        for (String name : shuffledNames) {
            for (Crew crew : crews) {
                if (crew.getName().equals(name)) {
                    result.add(crew);
                    break;
                }
            }
        }
        return result;
    }
}
