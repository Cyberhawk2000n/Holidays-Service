/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Passwd", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")}) 
public class Passwd implements Serializable {
    @Id
    @Column(name="id", unique = true, nullable = false)
    private short id;
    @Column(name="passwd", unique = false, nullable = false, length = 64)
    private String passwd;

    public Passwd() {
    }

    public Passwd(short id, String passwd) {
        this.id = id;
        this.passwd = passwd;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}