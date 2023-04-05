package step2;

import step2.common.Separator;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculateSeparator {

    private static final Pattern CUSTOM_SEPARATE_PATTERN = Pattern.compile(Separator.CUSTOM_SEPARATE.getRegexp());
    private static final int CUSTOM_SEPARATE_GROUP_INDEX = 1;
    private static final String EMPTY_STRING = "";
    private static final String SEPARATOR_CONCAT_DELIMITER = "|";

    private static class SeparatorInstanceLazyHolder {
        private static final StringCalculateSeparator INSTANCE = new StringCalculateSeparator();
    }

    public static StringCalculateSeparator getInstance() {
        return SeparatorInstanceLazyHolder.INSTANCE;
    }

    public List<String> separate(String input) {

        input = trim(input);


        if (input.isBlank()) {
            return Collections.emptyList();
        }

        return List.of(input.split(concatSeparate(input)));

    }

    private String trim(String input) {
        return input.strip();
    }

    private String concatSeparate(String input) {
        StringJoiner stringJoiner = new StringJoiner(SEPARATOR_CONCAT_DELIMITER);

        return stringJoiner.add(Separator.REGULAR_SEPARATE.getRegexp())
                .add(Separator.CUSTOM_SEPARATE.getRegexp())
                .add(extractCustomSeparate(input))
                .toString();
    }

    private String extractCustomSeparate(String input) {

        Matcher matcher = CUSTOM_SEPARATE_PATTERN.matcher(input);

        if (matcher.find()) {

            return matcher.group(CUSTOM_SEPARATE_GROUP_INDEX);
        }

        return EMPTY_STRING;
    }

}