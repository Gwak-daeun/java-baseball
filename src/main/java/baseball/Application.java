package baseball;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Application {

    public static boolean isInteger(String userInputNum) {

        char temp;

        boolean isNum = true;

        for (int word = 0; word < userInputNum.length(); word++) {
            temp = userInputNum.charAt(word);

            if (Character.isDigit(temp) == false) {
                isNum = false;
            }
        }
        System.out.println(isNum);
        return isNum;
    }

    public String userNumberInput() { // 사용자가 입력한 숫자를 받는다.

        Scanner userInput = new Scanner(System.in);

        try {

            String userInputNum = userInput.nextLine();

            if (userInputNum.length() != 3 || isInteger(userInputNum)){
                throw new IllegalArgumentException("잘못입력하셨습니다. 숫자 3자리를 입력해주세요.");
            }

        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage());
            System.exit(0);
        }

        return "";
    }

    public Set<Integer> computerNumber() { // 컴퓨터가 가지고 있을 숫자
        Set<Integer> comNums = new HashSet<>(); // 중복을 허용하지 않는 Set

        while (comNums.size() != 3) {
//            System.out.println(comNums.size());
            comNums.add((int)(Math.random() * 9 + 1));
        }

        System.out.println(comNums);
        return comNums; // 서로 다른 3자리 숫자 리턴
    }

    public void gameAnnounce() { // 게임 안내 문구
        System.out.println("숫자 야구 게임을 시작합니다.");

        Set<Integer> comNums = computerNumber();

        System.out.println("숫자를 입력해주세요 : ");

        userNumberInput();

    }

    public static void main(String[] args) {
        Application application = new Application();

        application.gameAnnounce();
    }
}
