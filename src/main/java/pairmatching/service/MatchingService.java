package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.model.Crew;
import pairmatching.domain.model.Crews;
import pairmatching.domain.model.Pair;
import pairmatching.domain.model.Pairs;
import pairmatching.dto.MatchingInfo;
import pairmatching.repository.MatchingRepository;

public class MatchingService {
    private final ShuffleGenerator shuffleGenerator;

    public MatchingService(ShuffleGenerator shuffleGenerator) {
        this.shuffleGenerator = shuffleGenerator;
    }


    public Crews shuffle(List<Crew> crews) {
        return Crews.fromCrews(shuffleGenerator.shuffle(crews));
    }

    public Pairs validateMatching(Crews crews, MatchingInfo matchingInfo) {
        List<Pairs> existingPairs = MatchingRepository.findByCourseAndLevel(
                matchingInfo.getCourse(),
                matchingInfo.getLevel()
        );

        int attemptCount = 0;
        while (attemptCount < 3) {
            attemptCount++;

            Pairs newPairs = pairMatching(crews);
            if (!hasDuplicatePair(newPairs, existingPairs)) {
                return newPairs;
            }

            crews = shuffle(crews.getCrews());
        }
        throw new IllegalArgumentException("[ERROR] 매칭에 실패했습니다.");
    }

    public Pairs pairMatching(Crews crews) {
        List<Pair> pairs = new ArrayList<>();
        int index = 0;

        while (index < crews.size()) {
            Pair pair = new Pair();
            pair.matching(crews.getCrews().get(index));
            pair.matching(crews.getCrews().get(index + 1));
            index += 2;
            pairs.add(pair);

            if (crews.isOdd() && index == crews.size() - 3) {
                Pair lastPair = new Pair();
                lastPair.matching(crews.getCrews().get(index));
                lastPair.matching(crews.getCrews().get(index + 1));
                lastPair.matching(crews.getCrews().get(index + 2));
                pairs.add(lastPair);
                break;
            }
        }
        return new Pairs(pairs);
    }

    private boolean hasDuplicatePair(Pairs newPairs, List<Pairs> existingPairs) {
        return newPairs.getPairs().stream()
                .anyMatch(newPair -> isDuplicatedInExisting(newPair, existingPairs));
    }

    private boolean isDuplicatedInExisting(Pair newPair, List<Pairs> existingPairs) {
        return existingPairs.stream()
                .flatMap(pairs -> pairs.getPairs().stream())
                .anyMatch(existingPair -> existingPair.equals(newPair));
    }
}
