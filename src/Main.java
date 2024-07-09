import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserService userService = new UserService();
    private static String loggedInUserId = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1: 회원가입 / 2: 로그인 / 3: 회원정보 수정 / 4: 탈퇴 / 5: 로그아웃 / 0: 종료");
            System.out.print("입력: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    editUserInfo();
                    break;
                case 4:
                    deleteUserAccount();
                    break;
                case 5:
                    logoutUser();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    private static void registerUser() {
        System.out.println("가입하실 아이디, 비밀번호, 이름을 (띄어쓰기로 구분하여) 입력하세요.");
        System.out.print("입력: ");
        String[] userInfo = scanner.nextLine().split(" ");
        if (userInfo.length < 3) {
            System.out.println("입력이 올바르지 않습니다. 다시 시도해주세요.");
            return;
        }

        User user = new User();
        user.setId(userInfo[0]);
        user.setPw(userInfo[1]);
        user.setName(userInfo[2]);

        userService.join(user);
    }

    private static void loginUser() {
        System.out.println("아이디와 비밀번호를 입력하세요.");
        System.out.print("입력: ");
        String[] loginInfo = scanner.nextLine().split(" ");
        if (loginInfo.length < 2) {
            System.out.println("입력이 올바르지 않습니다. 다시 시도해주세요.");
            return;
        }

        loggedInUserId = userService.login(loginInfo[0], loginInfo[1]);
    }

    private static void editUserInfo() {
        if (loggedInUserId == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        System.out.println("변경할 비밀번호와 이름을 입력하세요.");
        System.out.print("입력: ");
        String[] newUserInfo = scanner.nextLine().split(" ");
        if (newUserInfo.length < 2) {
            System.out.println("입력이 올바르지 않습니다. 다시 시도해주세요.");
            return;
        }

        User user = new User();
        user.setId(loggedInUserId);
        user.setPw(newUserInfo[0]);
        user.setName(newUserInfo[1]);

        userService.userInfoEdit(user);
    }

    private static void deleteUserAccount() {
        if (loggedInUserId == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        System.out.println("비밀번호를 입력하세요.");
        System.out.print("입력: ");
        String pw = scanner.nextLine();

        userService.deleteAccount(loggedInUserId, pw);
        loggedInUserId = null; // 탈퇴 후 로그아웃 처리
    }

    private static void logoutUser() {
        if (loggedInUserId == null) {
            System.out.println("로그인 상태가 아닙니다.");
            return;
        }

        loggedInUserId = null;
        System.out.println("로그아웃 되었습니다.");
    }
}
