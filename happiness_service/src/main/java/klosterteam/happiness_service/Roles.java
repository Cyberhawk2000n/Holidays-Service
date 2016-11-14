/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.Serializable;
import javax.persistence.*;
/**
 *
 * @author Cyberhawk
 */
@Entity
@Table
public class Roles implements Serializable {
    @Id
    private long id;
    @Column(name="role", unique = true, nullable = false, length = 16)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
