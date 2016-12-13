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
import javax.persistence.GeneratedValue;
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
@Table(name = "User_vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
public class User_vote implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_User_vote_user_id_Users_id"),
            unique = false, nullable = false)
    private Users userId;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hist_id", foreignKey = @ForeignKey(name = "FK_User_vote_hist_id_Events_id"),
            unique = false, nullable = false)
    private Events histId;
    @ManyToOne(fetch = FetchType.EAGER/*, cascade = CascadeType.REMOVE*/)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "vote_id", foreignKey = @ForeignKey(name = "FK_User_vote_vote_id_Vote_id"),
            unique = false, nullable = false)
    private Vote voteId;

    public User_vote() {
    }

    public User_vote(Users userId, Events histId, Vote voteId) {
        this.userId = userId;
        this.histId = histId;
        this.voteId = voteId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Events getHistId() {
        return histId;
    }

    public void setHistId(Events histId) {
        this.histId = histId;
    }

    public Vote getVoteId() {
        return voteId;
    }

    public void setVoteId(Vote voteId) {
        this.voteId = voteId;
    }
    
}
