package pairmatching.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class FileReaderTest {
    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        fileReader = new FileLineReader();
    }

    @Nested
    @DisplayName("파일 입력 테스트")
    class 파일_입력_테스트 {
        @Test
        @DisplayName("백엔드 크루는 총 20명이다.")
        void 백엔드_크루는_총_20명이다() {
            List<String> crews = fileReader.readFile("src/main/resources/backend-crew.md");

            assertThat(crews.size()).isEqualTo(20);
        }

        @Test
        @DisplayName("프론트엔드 크루는 총 15명이다.")
        void 프론트엔드_크루는_총_15명이다() {
            List<String> crews = fileReader.readFile("src/main/resources/frontend-crew.md");

            assertThat(crews.size()).isEqualTo(15);
        }
    }

    @Nested
    @DisplayName("파일 입력 예외 테스트")
    class 파일_입력_예외_테스트 {
        @Test
        @DisplayName("잘못된 형식의 파일이 입력된 경우 예외가 발생한다")
        void 잘못된_형식의_파일이_입력된_경우_예외가_발생한다() {
            assertThatThrownBy(() -> fileReader.readFile("src/main/resources/backend-crew"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 잘못된 형식의 파일입니다.");

            assertThatThrownBy(() -> fileReader.readFile("src/main/resources/frontend-crew"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 잘못된 형식의 파일입니다.");
        }
    }
}
