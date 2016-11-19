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
@Table(name = "Event_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Event_types implements Serializable {
    @Id
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @Column(name="name", unique = true, nullable = false, length = 32)
    private String name;

    public Event_types() {
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
}
