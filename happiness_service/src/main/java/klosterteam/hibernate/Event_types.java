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
@Table(name = "Event_types", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Event_types implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private short id;
    @Column(name="name", unique = true, nullable = false, length = 32)
    private String name;

    public Event_types() {
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event_types(String name) {
        this.name = name;
    }
}
