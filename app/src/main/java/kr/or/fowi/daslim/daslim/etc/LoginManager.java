package kr.or.fowi.daslim.daslim.etc;

import com.karrel.mylibrary.RLog;

/**
 * Created by Rell on 2017. 10. 2..
 */

public class LoginManager {
    private static LoginManager manager;

    public LoginManager() {
    }

    public static LoginManager getInstance() {
        if (manager == null) {
            manager = new LoginManager();
        }
        return manager;
    }

    public boolean login(String name, String nick) {
        PP.name.set(name);
        PP.nick.set(nick);

        return true;
    }

    public boolean isLogined() {
        final String name = PP.name.get();
        final String nick = PP.nick.get();
        // 이름이나 닉네임이 저장된게 없으면 false 를 리턴한다
        if (name == null || nick == null) return false;

        boolean haveName = !PP.name.get().isEmpty();
        boolean haveNick = !PP.nick.get().isEmpty();

        RLog.d(String.format("haveName : %s, haveNick : %s", haveName, haveNick));

        // 저장된 이름과 닉이 있으면 로그인되어있는거로 하자
        return haveName && haveNick;
    }
}
