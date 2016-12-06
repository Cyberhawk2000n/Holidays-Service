/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Cyberhawk
 */
@Entity
@Table(name = "Events", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class Events implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @Column(name="name", unique = false, nullable = false, length = 64)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "FK_Events_type_id_Event_types_id"), unique = false, nullable = false)
    private Event_types typeId;
    @Column(name="every_year", unique = false, nullable = false)
    private boolean everyYear;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Events_user_id_Users_id"), unique = false, nullable = true)
    private Users userId;
    @Temporal(TemporalType.DATE)
    @Column(name="date", unique = false, nullable = false)
    private Date date;
    @Column(name="active", unique = false, nullable = false)
    private boolean active;
    @Column(name="template", unique = false, nullable = false, length = 256)
    private String template;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", unique = false, nullable = true)
    private Users managerId;

    public Events() {
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

    public Event_types getTypeId() {
        return typeId;
    }

    public void setTypeId(Event_types typeId) {
        this.typeId = typeId;
    }

    public boolean isEveryYear() {
        return everyYear;
    }

    public void setEveryYear(boolean everyYear) {
        this.everyYear = everyYear;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Users getManagerId() {
        return managerId;
    }

    public void setManagerId(Users managerId) {
        this.managerId = managerId;
    }

    public Events(String name, Event_types typeId, boolean everyYear, Users userId, Date date, boolean active, String template, Users managerId) {
        this.name = name;
        this.typeId = typeId;
        this.everyYear = everyYear;
        this.userId = userId;
        this.date = date;
        this.active = active;
        this.template = template;
        this.managerId = managerId;
    }
    
}
