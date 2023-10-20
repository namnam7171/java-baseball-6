package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        String restartInput;

        do {
            System.out.println("숫자 야구 게임을 시작합니다.");

            List<Integer> computer = generateComputerNumber();

            while (true) {
                System.out.print("숫자를 입력해주세요 : ");
                String userInput = Console.readLine();

                try {
                    validateInput(userInput);

                    int userNumber = Integer.parseInt(userInput);

                    int strikes = 0;
                    int balls = 0;

                    for (int i = 0; i < 3; i++) {
                        int userDigit = getUserDigit(userNumber, i);
                        int computerDigit = getComputerDigit(computer, i);

                        if (userDigit == computerDigit) {
                            strikes++;
                        } else if (computer.contains(userDigit)) {
                            balls++;
                        }
                    }

                    if (strikes == 3) {
                        System.out.println("3스트라이크");
                        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                        break; // 게임 종료
                    } else if (strikes == 0 && balls == 0) {
                        System.out.println("낫싱");
                    } else {
                        if (balls > 0 && strikes > 0) {
                            System.out.println(balls + "볼 " + strikes + "스트라이크");
                        } else if (balls == 0) {
                            System.out.println(strikes + "스트라이크");
                        } else {
                            System.out.println(balls + "볼");
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.exit(0);
                }
            }

            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            restartInput = Console.readLine();

            if ("2".equals(restartInput)) {
                break; // 게임 완전히 종료
            }
        } while ("1".equals(restartInput));
    }
    private static List<Integer> generateComputerNumber() {
        List<Integer> computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
        return computer;
    }

    private static void validateInput(String input) {
        if (input.length() != 3 || !input.matches("\\d{3}")) {
            throw new IllegalArgumentException();
        }
    }

    private static int getUserDigit(int number, int position) {
        return (number / (int) Math.pow(10, 2 - position)) % 10;
    }

    private static int getComputerDigit(List<Integer> computer, int position) {
        return computer.get(position);
    }
    /*
    do {
        게임 시작하면 "숫자 야구 게임을 시작합니다." 출력
        컴퓨터가 랜덤으로 서로다른 3자리의 수 생성

        사용자가 숫자를 모두 맞힐 때까지 반복 {
            "숫자를 입력해주세요 : " 출력 후 사용자가 3자리의 수를 입력
            사용자가 잘못된 값을 입력하면
                게임 종료
            사용자가 옳은 값을 입력하면
                컴퓨터가 생성한 수와 사용자가 입력한 수를 비교
                같은 수가 같은 자리에 있으면 스트라이크
                같은 수가 다른 자리에 있으면 볼
                같은 수가 전혀 없으면 낫싱
                스트라이크, 볼, 낫싱을 출력
                3스트라이크면 게임 종료
        }
    }
    while (게임을 새로 시작 할 지 질문(1이면 새로 시작, 2이면 종료))
     */
}
