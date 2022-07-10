package juc.tools;

import lombok.Getter;

/**
 * 枚举模板
 */
public enum CountryEnum {
    ONE(1, "韩"), TWO(2, "赵"), THREE(3, "楚"), FOUR(4, "魏"), FIVE(5, "燕"), SIX(6, "齐");
    @Getter
    private Integer reCode;
    @Getter
    private String retMessage;

    CountryEnum(Integer reCode, String retMessage) {
        this.reCode = reCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray) {
            if (index == element.getReCode()) {
                return element;
            }
        }
        return null;
    }
}
