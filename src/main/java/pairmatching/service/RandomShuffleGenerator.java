package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import pairmatching.domain.model.Crew;

public class RandomShuffleGenerator implements ShuffleGenerator {
    @Override
    public List<Crew> shuffle(List<Crew> crews) {
        return Randoms.shuffle(crews);
    }
}
