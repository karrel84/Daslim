package kr.or.fowi.daslim.daslim.etc;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class LoginManager {
    private static LoginManager manager;
    private boolean isLogined;

    public LoginManager() {
        this.isLogined = false;
    }

    public static LoginManager getInstance() {
        if (manager == null) {
            manager = new LoginManager();
        }
        return manager;
    }

    public boolean login(String id, String pw) {

        return true;
    }

    public boolean isLogined() {
        return isLogined;
    }
}
