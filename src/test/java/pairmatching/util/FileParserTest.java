package pairmatching.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pairmatching.domain.model.Crew;

public class FileParserTest {
    @Nested
    @DisplayName("파일 입출력 테스트")
    class 파일_입출력_테스트 {
        @Test
        @DisplayName("페어 매칭에 필요한 크루들의 이름을 파일 입출력을 통해 불러온다.")
        void 페어_매칭에_필요한_크루들의_이름을_파일_입출력을_통해_불러온다() {
            List<Crew> backendCrews = FileParser.parseBackendCrews();
            List<Crew> frontendCrews = FileParser.parseFrontendCrews();

            assertThat(backendCrews.size()).isEqualTo(20);
            assertThat(frontendCrews.size()).isEqualTo(15);
        }
    }
}
