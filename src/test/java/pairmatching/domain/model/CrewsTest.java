package pairmatching.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CrewsTest {
    @Nested
    @DisplayName("크루 생성 테스트")
    class 크루_생성_테스트 {
        @Test
        @DisplayName("크루를 생성해 인원수를 확인한다.")
        void 크루를_생성해_인원수를_확인한다() {
            List<Crew> crew = new ArrayList<>();
            crew.add(new Crew(Course.BACKEND, "백호"));
            crew.add(new Crew(Course.BACKEND, "태웅"));
            crew.add(new Crew(Course.BACKEND, "치수"));
            Crews crews = Crews.fromCrews(crew);

            assertThat(crews.isOdd()).isTrue();
        }
    }
}
