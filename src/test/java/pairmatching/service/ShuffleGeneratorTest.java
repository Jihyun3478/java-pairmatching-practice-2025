package pairmatching.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Crew;

class ShuffleGeneratorTest {
    private ShuffleGenerator shuffleGenerator;

    @BeforeEach
    void setUp() {
        shuffleGenerator = new RandomShuffleGenerator();
    }

    @Nested
    @DisplayName("크루 섞기 테스트")
    class 크루_섞기_테스트 {
        @Test
        @DisplayName("크루 목록의 순서를 랜덤으로 섞는다.")
        void 크루_목록의_순서를_랜덤으로_섞는다() {
            List<Crew> crews = new ArrayList<>();
            crews.add(new Crew(Course.BACKEND, "백호"));
            crews.add(new Crew(Course.BACKEND, "태웅"));
            crews.add(new Crew(Course.BACKEND, "치수"));

            assertThat(crews).isNotSameAs(shuffleGenerator.shuffle(crews));
        }
    }
}
