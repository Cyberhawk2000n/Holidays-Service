/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Users implements Serializable {
    @Id
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_Money_hist_id_History_id"), unique = false, nullable = false)
    private Roles roleId;
    @Column(name="email", unique = true, nullable = false, length = 64)
    private String email;
    @Column(name="about", unique = false, nullable = true, length = 256)
    private String about;

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
    
}
