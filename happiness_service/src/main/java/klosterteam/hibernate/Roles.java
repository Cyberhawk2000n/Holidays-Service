/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")}) 
public class Roles implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private short id;
    @Column(name="role", unique = true, nullable = false, length = 16)
    private String role;

    public Roles() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Roles(String role) {
        this.role = role;
    }
    
}
