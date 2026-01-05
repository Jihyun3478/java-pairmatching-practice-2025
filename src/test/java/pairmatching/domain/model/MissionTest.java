package pairmatching.domain.model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MissionTest {
    @Nested
    @DisplayName("미션 생성 예외 테스트")
    class 미션_생성_예외_테스트 {
        @Test
        @DisplayName("각 레벨별 미션에 해당하지 않고 존재하지 않는 미션인 경우, 예외가 발생한다.")
        void 각_레벨별_미션에_해당하지_않고_존재하지_않는_미션인_경우_예외가_발생한다() {
            assertThatThrownBy(() -> Mission.of(Level.LEVEL1, "성능개선"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 존재하지 않는 미션입니다. 다시 입력해주세요.");
        }
    }
}
