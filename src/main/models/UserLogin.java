package main.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQueries(
        @NamedQuery(name="ul.findAll", query="SELECT ul FROM UserLogin ul")
)
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String loginToken;

    private long userId;

    private LocalDateTime loginTime;

    public UserLogin() {
        loginTime = LocalDateTime.now();
    }

    public UserLogin(long userId, String loginToken) {
        this();
        this.userId = userId;
        this.loginToken = loginToken;
        this.loginTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", loginToken='" + loginToken + '\'' +
                ", userId=" + userId +
                ", loginTime=" + loginTime +
                '}';
    }
}
