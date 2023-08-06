package baseball;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GameInterface {
     void startGame();

     Map<String, Integer> insertStrikeAndBallAndNothing(Set<Integer> com);

     void choiceRestartOrEndGame();

    boolean compareComAndUser(List<Integer> comNum, List<Integer> userNum);

    void announceGameResult();
}
