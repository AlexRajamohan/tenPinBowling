import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum RollValuesEnum {
    ONE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    STRIKE("X", 10),
    SPARE("/", 10),
    MISS("-", 0);

    private final String value;
    private final int result;

    /**
     * Check if a given value is a value of the enum
     *
     * @param value
     * @return
     */
    public static boolean isInEnum(String value) {
        if (value == null) {
            return false;
        }
        return Stream.of(RollValuesEnum.values()).anyMatch(v -> v.getValue().equals(value));

    }

    /**
     * Give the result of a roll given a value
     *
     * @param value
     * @return
     */
    public static Integer getResultByValue(String value) {
        // 0 if no value is matching
        return Stream.of(RollValuesEnum.values()).filter(v -> v.getValue().equals(value)).map(RollValuesEnum::getResult).findFirst().orElse(0);

    }
}
