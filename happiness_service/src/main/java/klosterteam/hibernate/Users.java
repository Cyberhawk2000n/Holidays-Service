/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Users implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @Column(name="name", unique = false, nullable = false, length = 32)
    private String name;
    @Column(name="surname", unique = false, nullable = false, length = 32)
    private String surname;
    @Column(name="patronymic", unique = false, nullable = true, length = 32)
    private String patronymic;
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", unique = false, nullable = false)
    private Date birthday;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "role_id", unique = false, nullable = false)
    private Roles roleId;
    @Column(name="email", unique = true, nullable = false, length = 64)
    private String email;
    @Column(name="about", unique = false, nullable = true, length = 256)
    private String about;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "dep_id", unique = false, nullable = false)
    private Departments depId;
    @Column(name="give_gift", unique = false, nullable = false)
    private boolean giveGift;

    public Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Departments getDepId() {
        return depId;
    }

    public void setDepId(Departments depId) {
        this.depId = depId;
    }

    public boolean isGiveGift() {
        return giveGift;
    }

    public void setGiveGift(boolean giveGift) {
        this.giveGift = giveGift;
    }


    public Users(String name, String surname, String patronymic, Date birthday, Roles roleId, String email, String about, Departments depId, boolean give_gift) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.roleId = roleId;
        this.email = email;
        this.about = about;
        this.depId = depId;
        this.giveGift = give_gift;
    }
    
}
