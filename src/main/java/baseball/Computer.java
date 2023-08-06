package baseball;

import java.util.*;

public class Computer {

    public Set<Integer> makeComputerNumber() { // 컴퓨터 클래스: 컴퓨터가 가지고 있을 숫자
        Set<Integer> comNums = new HashSet<>(); // 중복을 허용하지 않는 Set

        while (comNums.size() != 3) {
//            System.out.println(comNums.size());
            comNums.add((int) (Math.random() * 9 + 1));
        }

        System.out.println(comNums);
        return comNums; // 서로 다른 3자리 숫자 리턴
    }

    public List<Integer> changeComSetIntoNum(Set<Integer> com) { // 컴퓨터 클래스

        Iterator<Integer> comSet = com.iterator();

        List<Integer> comNum = new ArrayList<>();

        while (comSet.hasNext()) {
            comNum.add(comSet.next());
        }

        return comNum;
    }
}
