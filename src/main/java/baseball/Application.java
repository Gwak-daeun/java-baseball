package baseball;

import java.util.*;

public class Application {

    List<Integer> userNum = new ArrayList<>();

    boolean isComAndUserSame = true;

    Scanner userInput = new Scanner(System.in);


    public Map<String, Integer> insertStrikeAndBallAndNothing(Set<Integer> com) { // 스트라이크, 볼, 낫싱
        //user 문자열을 배열로 나눠 com과 비교해야

        List<Integer> comNum = changeComSetIntoNum(com);

        Map<String, Integer> resultWord = new HashMap<>();

        while (isComAndUserSame){
            System.out.println("숫자를 입력해주세요 : ");

            String userInputString = userInput.nextLine();

            checkUserNumber(userInputString);

            userNum = changeUserStringIntoNum(userInputString);

            isComAndUserSame = compareComAndUser(comNum, userNum);

//        List<String> resultWord = new ArrayList<>();

            resultWord.put("스트라이크", 0);
            resultWord.put("볼", 0);
            resultWord.put("낫싱", 0);

            int strikeCheck = 0;
            int ballCheck = 0;
            int nothingCheck = 3;

            boolean strikeChecked = true;

            for (int userNumIdx = 0; userNumIdx < userNum.size(); userNumIdx++) {
                for (int comIdx = 0; comIdx < comNum.size(); comIdx++) {
                    if (comNum.get(comIdx) == userNum.get(userNumIdx)) {
                        if (comIdx == userNumIdx) { // 스트라이크인 경우
                            strikeCheck++;
                            strikeChecked = false;
                        }
                        if (comIdx != userNumIdx && strikeChecked) { // 볼인 경우
                            if (strikeCheck == comIdx + 1) {
                                continue;
                            } else {
                                ballCheck++;
                            }
                        }
                        strikeChecked = true;
                    }
                }
            }

            nothingCheck -= strikeCheck + ballCheck;

            resultWord.replace("스트라이크", strikeCheck);
            resultWord.replace("볼", ballCheck);
            resultWord.replace("낫싱", nothingCheck);

            if (resultWord.get("스트라이크") != 0) {
                System.out.print(resultWord.get("스트라이크") + "스트라이크 ");
            }
            if (resultWord.get("볼") != 0) {
                System.out.print(resultWord.get("볼") + "볼 ");
            }
            if (resultWord.get("낫싱") != 0) {
                System.out.print(resultWord.get("낫싱") + "낫싱 " + "\n");
            }
        }

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

        choiceRestartOrEndGame();


        return resultWord;
    }

    public void choiceRestartOrEndGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        

    }

    public boolean compareComAndUser(List<Integer> comNum, List<Integer> userNum) {

        boolean isNotSame = false;

        for (int i = 0; i < comNum.size(); i++) {
            if (!Objects.equals(comNum.get(i), userNum.get(i))) {
                isNotSame = true;
            }
        }

        return isNotSame;
    }

    public List<Integer> changeComSetIntoNum(Set<Integer> com) {

        Iterator<Integer> comSet = com.iterator();

        List<Integer> comNum = new ArrayList<>();

        while (comSet.hasNext()) {
            comNum.add(comSet.next());
        }

        return comNum;
    }

    public List<Integer> changeUserStringIntoNum(String userInputString) {

        List<Integer> userNum = new ArrayList<>();

        for (int userNumIdx = 0; userNumIdx < userInputString.length(); userNumIdx++) {
            char userInt = userInputString.charAt(userNumIdx);
            userNum.add(Character.getNumericValue(userInt));
        }
        System.out.println(userNum);

        return userNum;
    }

    public boolean isInteger(String userInputNum) {

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

    public String checkUserNumber(String userInputString) { // 사용자가 입력한 숫자를 받는다.(숫자 입력했는지 확인 후 예외처리)

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

    public Set<Integer> makeComputerNumber() { // 컴퓨터가 가지고 있을 숫자
        Set<Integer> comNums = new HashSet<>(); // 중복을 허용하지 않는 Set

        while (comNums.size() != 3) {
//            System.out.println(comNums.size());
            comNums.add((int) (Math.random() * 9 + 1));
        }

        System.out.println(comNums);
        return comNums; // 서로 다른 3자리 숫자 리턴
    }

    public void announceGame() { // 게임 안내 문구
        System.out.println("숫자 야구 게임을 시작합니다.");

        Set<Integer> comNums = makeComputerNumber();

//        System.out.println("숫자를 입력해주세요 : ");

//        String userInputString = checkUserNumber();

        //컴퓨터의 숫자와 유저의 숫자를 비교하는 메소드
        Map<String, Integer> resultWord = insertStrikeAndBallAndNothing(comNums);


    }

    public static void main(String[] args) {
        Application application = new Application();

        application.announceGame();
    }
}