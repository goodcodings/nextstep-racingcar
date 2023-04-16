package step4.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

class UsingNameCarRacingInformationTest {

    @Test
    @DisplayName("레이싱카 생성 테스트")
    void 레이싱카_생성_테스트() {
        String given = "안녕하세요,반갑습니다";
        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 5);

        assertAll(
                () -> assertThat(usingNameCarRacingInformation.getRacingCarList().stream().map(UsingNameRacingCar::getName)).containsExactly("안녕하세요", "반갑습니다"),
                () -> assertThat(usingNameCarRacingInformation.getCurrentReps().getReps()).isEqualTo(5)
        );
    }


    @ParameterizedTest
    @ValueSource(strings = {",,,,,",",", ",,"})
    @DisplayName("콤마(,)만 있을 때 에러를 던진다")
    void 콤마_만_있을_때_에러를_던진다(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> UsingNameCarRacingInformation.of(input, 5))
                .withMessage("자동차 이름을 입력해주세요");

    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null or 빈값 일 경우 예외를 던진다")
    void null_or_빈값_일_경우_예외를_던진다(String input) {
        assertThatIllegalArgumentException().isThrownBy(() -> UsingNameCarRacingInformation.of(input, 5))
                .withMessage("자동차 이름을 입력해주세요");

    }

    @Test
    @DisplayName("같은 이름이 있을 때 에러를 던진다")
    void 같은_이름이_있을_때_에러를_던진다() {
        String carNames = "pobi1,pobi1,pobi3";

        assertThatIllegalArgumentException().isThrownBy(() -> UsingNameCarRacingInformation.of(carNames, 5))
                .withMessage("같은 이름이 있습니다");
    }

    @Test
    @DisplayName("우승 자동차 위치 구하기 테스트")
    void 우승_자동차_위치_구하기_테스트() {
        String given = "안녕하세요,반갑습니다";

        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 5);
        usingNameCarRacingInformation.getRacingCarList().get(0).move(()->true);

        assertThat(usingNameCarRacingInformation.getWinnerLocation()).isEqualTo(1);
    }


    @Test
    @DisplayName("단독 1등 우승자 구하기 테스트")
    void 단독_1등_우승자_구하기_테스트() {
        String given = "안녕하세요,반갑습니다,포포로로";

        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 5);
        usingNameCarRacingInformation.getRacingCarList().get(0).move(()->true);

        assertThat(usingNameCarRacingInformation.getWinner()).containsExactly("안녕하세요");
    }

    @Test
    @DisplayName("공동 우승자 구하기 테스트")
    void 공동_우승자_구하기_테스트() {
        String given = "안녕하세요,반갑습니다,포포로로";

        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 5);
        usingNameCarRacingInformation.getRacingCarList().get(0).move(()->true);
        usingNameCarRacingInformation.getRacingCarList().get(1).move(()->true);

        assertThat(usingNameCarRacingInformation.getWinner()).containsExactly("안녕하세요","반갑습니다");
    }


    @Test
    @DisplayName("레이싱_진행중_테스트")
    void 레이싱_진행중_테스트() {
        String given = "안녕하세요,반갑습니다,포포로로";

        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 5);


        assertThat(usingNameCarRacingInformation.isContinue()).isTrue();
    }

    @Test
    @DisplayName("레이싱_종료_테스트")
    void 레이싱_종료_테스트() {
        String given = "안녕하세요,반갑습니다,포포로로";

        UsingNameCarRacingInformation usingNameCarRacingInformation = UsingNameCarRacingInformation.of(given, 0);


        assertThat(usingNameCarRacingInformation.isContinue()).isFalse();
    }
}