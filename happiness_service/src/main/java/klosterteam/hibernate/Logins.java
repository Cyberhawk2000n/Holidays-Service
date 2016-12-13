/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Logins", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id")})
public class Logins implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Logins_user_id_Users_id"),
            unique = true, nullable = false)
    private Users userId;
    @Column(name="login", unique = false, nullable = false, length = 32)
    private String login;
    @Column(name="password", unique = false, nullable = false, length = 32)
    private String password;

    public Logins() {
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Logins(Users userId, String login, String password) {
        this.userId = userId;
        this.login = login;
        this.password = password;
    }
    
}
