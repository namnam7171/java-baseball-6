package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String restart;

        do {
            // 게임 시작하면 "숫자 야구 게임을 시작합니다." 출력
            System.out.println("숫자 야구 게임을 시작합니다.");

            // 컴퓨터가 랜덤으로 서로다른 3자리의 수 생성
            List<Integer> computer = generateComputerNumber();

            while (true) { // 사용자가 숫자를 모두 맞힐 때까지 반복

                // 사용자가 3자리의 수를 입력
                System.out.print("숫자를 입력해주세요 : ");
                String userInput = Console.readLine();

                try {
                    validateInput(userInput);

                    int userNumber = Integer.parseInt(userInput);

                    int strikes = 0;
                    int balls = 0;

                    // 컴퓨터가 생성한 수와 사용자가 입력한 수를 비교
                    for (int i = 0; i < 3; i++) {
                        int userDigit = getUserDigit(userNumber, i);
                        int computerDigit = getComputerDigit(computer, i);

                        // 같은 수가 같은 자리에 있으면 스트라이크
                        if (userDigit == computerDigit) {
                            strikes++;
                        }
                        // 같은 수가 다른 자리에 있으면 볼
                        else if (computer.contains(userDigit)) {
                            balls++;
                        }
                    }

                    // 3스트라이크면 게임 종료
                    if (strikes == 3) {
                        System.out.println("3스트라이크");
                        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                        break; // 게임 종료
                    }
                    // 같은 수가 전혀 없으면 낫싱
                    else if (strikes == 0 && balls == 0) {
                        System.out.println("낫싱");
                    }
                    // n볼 0스트라이크
                    else if (strikes == 0) {
                        System.out.println(balls + "볼");
                    }
                    // 0볼 n스트라이크
                    else if (balls == 0) {
                        System.out.println(strikes + "스트라이크");
                    }
                    // n볼 n스트라이크
                    else {
                        System.out.println(balls + "볼 " + strikes + "스트라이크");
                    }
                } catch (IllegalArgumentException e) { // 사용자가 잘못된 값을 입력하면 게임 종료
                    System.exit(0);
                }
            }
            // 게임을 새로 시작 할 지 질문(1이면 새로 시작, 2이면 종료)
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            restart = Console.readLine();

            if ("2".equals(restart)) {
                break; // 게임 완전히 종료
            }
        } while ("1".equals(restart));
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
}
