package baseball;

import java.util.*;

public class GameImpl implements GameInterface{

    User user = new User();

    Computer computer = new Computer();

    boolean notComAndUserSame = true;

    Scanner userInput = new Scanner(System.in);

    List<Integer> userNum = new ArrayList<>();

    Map<String, Integer> resultWord = new HashMap<>();

    int strikeCheck, ballCheck = 0;

    int nothingCheck = 3;

    @Override
    public void startGame() { //게임 클래스: 게임 안내 문구
        System.out.println("숫자 야구 게임을 시작합니다.");

        Set<Integer> comNums = computer.makeComputerNumber();

        //컴퓨터의 숫자와 유저의 숫자를 비교하는 메소드
        insertStrikeAndBallAndNothing(comNums);

    }

    @Override
    public Map<String, Integer> insertStrikeAndBallAndNothing(Set<Integer> com) { //게임 클래스: 스트라이크, 볼, 낫싱

        List<Integer> comNum = computer.changeComSetIntoNum(com);

        while (notComAndUserSame){
            System.out.println("숫자를 입력해주세요 : ");

            String userInputString = userInput.nextLine();

            user.checkUserNumber(userInputString);

            userNum = user.changeUserStringIntoNum(userInputString);

            notComAndUserSame = compareComAndUser(comNum, userNum);

            resultWord.put("스트라이크", 0);
            resultWord.put("볼", 0);
            resultWord.put("낫싱", 0);

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
        announceGameResult();
        }

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

        choiceRestartOrEndGame();

        return resultWord;
    }

    @Override
    public void announceGameResult() {
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

    @Override
    public void choiceRestartOrEndGame() { // 게임 클래스
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String userChoice = userInput.nextLine();

        if (userChoice.equals("1")) {
            notComAndUserSame = true;
            startGame();
        }
        if (userChoice.equals("2")) {
            System.exit(0);
        }
    }

    @Override
    public boolean compareComAndUser(List<Integer> comNum, List<Integer> userNum) { // 게임 클래스

        boolean isNotSame = false;

        for (int i = 0; i < comNum.size(); i++) {
            if (!Objects.equals(comNum.get(i), userNum.get(i))) {
                isNotSame = true;
            }
        }

        return isNotSame;
    }
}
