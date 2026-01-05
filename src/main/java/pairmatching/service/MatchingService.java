package pairmatching.service;

import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
import pairmatching.domain.Pair;
import pairmatching.dto.MatchingInfo;
import pairmatching.domain.repository.MatchingRepository;

public class MatchingService {
    private final ShuffleGenerator shuffleGenerator;

    public MatchingService(ShuffleGenerator shuffleGenerator) {
        this.shuffleGenerator = shuffleGenerator;
    }

    public Crews shuffle(List<Crew> crews) {
        return Crews.fromCrews(shuffleGenerator.shuffle(crews));
    }

    public List<Pair> validateMatching(Crews crews, MatchingInfo matchingInfo) {
        List<List<Pair>> existingPairs = MatchingRepository.findByCourseAndLevel(
                matchingInfo.getCourse(),
                matchingInfo.getLevel()
        );

        int attemptCount = 0;
        while (attemptCount < 3) {
            attemptCount++;

            List<Pair> newPairs = pairMatching(crews);
            if (!hasDuplicatePair(newPairs, existingPairs)) {
                return newPairs;
            }
            crews = shuffle(crews.getCrews());
        }
        throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
    }

    public List<Pair> pairMatching(Crews crews) {
        return crews.createPairs();
    }

    private boolean hasDuplicatePair(List<Pair> newPairs, List<List<Pair>> existingPairs) {
        return newPairs.stream()
                .anyMatch(pair -> pair.existsIn(existingPairs));
    }
}
