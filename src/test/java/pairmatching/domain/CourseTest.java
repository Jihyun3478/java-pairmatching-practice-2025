package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CourseTest {
    @Nested
    @DisplayName("교육과정 생성 예외 테스트")
    class 교육과정_생성_예외_테스트 {
        @Test
        @DisplayName("존재하지 않는 과정인 경우, 예외가 발생한다.")
        void 존재하지_않는_과정인_경우_예외가_발생한다() {
            assertThatThrownBy(() -> Course.fromName("안드로이드"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 존재하지 않는 과정입니다. 다시 입력해주세요.");
        }
    }
}
