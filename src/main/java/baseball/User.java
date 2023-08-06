package baseball;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    Scanner userInput = new Scanner(System.in);

    public String checkUserNumber(String userInputString) { // 유저 클래스: 사용자가 입력한 숫자를 받는다.(숫자 입력했는지 확인 후 예외처리)

        try {

            if (userInputString.length() != 3 || isInteger(userInputString)) {
                throw new IllegalArgumentException("잘못입력하셨습니다. 숫자 3자리를 입력해주세요.");
            }

            return userInputString;

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            System.exit(0);
        }

        return "";
    }

    public List<Integer> changeUserStringIntoNum(String userInputString) { // 유저 클래스

        List<Integer> userNum = new ArrayList<>();

        for (int userNumIdx = 0; userNumIdx < userInputString.length(); userNumIdx++) {
            char userInt = userInputString.charAt(userNumIdx);
            userNum.add(Character.getNumericValue(userInt));
        }
        System.out.println(userNum);

        return userNum;
    }

    public boolean isInteger(String userInputNum) { // 유저 클래스

        char temp;

        boolean isNum = false;

        for (int word = 0; word < userInputNum.length(); word++) {
            temp = userInputNum.charAt(word);

            if (Character.isDigit(temp) == false) {
                isNum = true;
            }
        }
        return isNum;
    }

}
