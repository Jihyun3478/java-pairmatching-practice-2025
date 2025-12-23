package pairmatching;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CourseTest {
    @Nested
    @DisplayName("교육과정 입력 예외 테스트")
    class 교육과정_입력_예외_테스트 {
        @Test
        @DisplayName("존재하지 않는 교육과정이 입력되면 예외가 발생한다.")
        void 존재하지_않는_교육과정이_입력되면_예외가_발생한다() {
            assertThatThrownBy(() -> Course.isExistCourse("안드로이드"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 존재하지 않는 교육과정 입니다. 다시 입력해주세요.");
        }
    }
}
