package step1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringClassStudy {

    @Nested
    @DisplayName("[요구사항1] 쉼표로 구분된 문자열 ")
    class Describe_CommaSeparatedString {

        private final static String DELIMITER = ",";

        @Nested
        @DisplayName("1,2이 주어진다면")
        class Context_with_ONE_DELIMITER_TWO_String {

            private final static String GIVEN_STRING = "1,2";
            private final String[] SHOULD_RETURN_ARRAY = {"1", "2"};

            @Test
            @DisplayName("1과 2로 구성된 배열을 반환한다")
            void returnStringArray() {

                String[] result = GIVEN_STRING.split(DELIMITER);

                assertThat(result).containsExactly(SHOULD_RETURN_ARRAY);
            }
        }

        @Nested
        @DisplayName("1,이 주어진다면")
        class Context_with_ONE_DELIMITER_String {

            private final static String GIVEN_STRING = "1,";
            private final String[] SHOULD_RETURN_ARRAY = {"1"};

            @Test
            @DisplayName("1로 구성된 배열을 반환한다")
            void returnStringArray() {

                String[] result = GIVEN_STRING.split(DELIMITER);

                assertThat(result).containsExactly(SHOULD_RETURN_ARRAY);
            }
        }
    }

    @Nested
    @DisplayName("[요구사항2] 쉼표로 구분된 소괄호안 문자열")
    class Describe_StringInCommaSeparatedBrackets {

        private final static String SHOULD_REMOVE_SPECIAL_CHARACTERS = "[/(|/)]";
        // apache lang StringUtils 클래스가 없어 대체합니다
        private final static String EMPTY_STRING = "";

        @Nested
        @DisplayName("(1,2)이 주어진다면")
        class Context_with_ONE_DELIMITER_TWO_String {

            private final static String GIVEN_STRING = "(1,2)";
            private final static String SHOULD_RETURN_STRING = "1,2";

            @Test
            @DisplayName("1,2을 반환한다")
            void returnStringArray() {
                String resultWithRegex = GIVEN_STRING.replaceAll(SHOULD_REMOVE_SPECIAL_CHARACTERS, EMPTY_STRING);
                String resultWithSubString = GIVEN_STRING.substring(1, 4);

                Assertions.assertAll(
                        () -> assertThat(resultWithRegex).isEqualTo(SHOULD_RETURN_STRING),
                        () -> assertThat(resultWithSubString).isEqualTo(SHOULD_RETURN_STRING)
                );
            }
        }

    }

}
