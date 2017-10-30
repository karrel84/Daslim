package kr.or.fowi.daslim.daslim.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Rell on 2017. 10. 2..
 */

@IgnoreExtraProperties
public class UserInfo {
    // 이름
    public String name;
    // 닉네임
    public String nick;
    // 전화번호
    public String tel;

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
