/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klosterteam.happiness_service;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @Column(name="name", unique = false, nullable = false, length = 64)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "FK_Events_type_id_Event_types_id"), unique = false, nullable = false)
    private Event_types typeId;
    @Column(name="every_year", unique = false, nullable = false)
    private boolean everyYear;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_Events_user_id_Users_id"), unique = false, nullable = true)
    private Users userId;
    @Temporal(TemporalType.DATE)
    @Column(name="date", unique = false, nullable = false)
    private Date date;

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
    
}
