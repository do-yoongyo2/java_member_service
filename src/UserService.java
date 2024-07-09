class UserService {
    UserDAO userDAO = new UserDAO();

    public boolean join(User user) {
        if (user.getId() == null || user.getPw() == null || user.getName() == null) {
            System.out.println("모든 필드를 입력해 주세요.");
            return false;
        }

        boolean result = userDAO.save(user);

        if (result) {
            System.out.println(user.getName() + "님 가입을 환영합니다.");
            return true;
        } else {
            System.out.println("중복된 ID의 유저가 이미 존재합니다.");
            return false;
        }
    }

    public String login(String id, String pw) {
        User user = userDAO.find(id);

        if (user == null) {
            System.out.println("id 또는 pw가 올바르지 않습니다. ");
            return null;
        }

        if (user.getPw().equals(pw)) {
            System.out.println(user.getName() + "님 로그인을 환영합니다.");
            return user.getId();
        } else {
            System.out.println("id 또는 pw가 올바르지 않습니다.");
            return null;
        }
    }

    public void userInfoEdit(User newUserInfo) {
        User currentUser = userDAO.find(newUserInfo.getId());
        if (currentUser == null) {
            System.err.println("ERROR!!!!!!!!!!!!!!!");
            return;
        }
        userDAO.update(newUserInfo);
        System.out.println("수정이 완료되었습니다.");
    }

    void deleteAccount(String id, String pw) {
        User currentUser = userDAO.find(id);
        if (currentUser == null) {
            System.err.println("ERROR!!!!!!!!!!!!!!!");
            return;
        }
        if (!currentUser.getPw().equals(pw)) {
            System.err.println("비밀번호가 틀렸습니다.");
            return;
        }
        userDAO.delete(id);
        System.out.println("탈퇴가 완료되었습니다.");
    }
}
