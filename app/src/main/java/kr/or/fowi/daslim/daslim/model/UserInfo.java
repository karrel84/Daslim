package kr.or.fowi.daslim.daslim.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 2..
 */

@IgnoreExtraProperties
public class UserInfo {
    // 이름
    String name;
    // 닉네임
    String nick;
    // 전화번호
    String tel;

    public UserInfo(String name, String nick, String tel) {
        this.name = name;
        this.nick = nick;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
