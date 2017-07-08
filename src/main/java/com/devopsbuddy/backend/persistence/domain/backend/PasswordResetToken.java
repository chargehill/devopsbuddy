package com.devopsbuddy.backend.persistence.domain.backend;

import com.devopsbuddy.backend.persistence.converters.LocalDateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by octavio on 12/25/16.
 */
@Entity
public class PasswordResetToken implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_TOKEN_EXPIRATION_IN_MINUTES = 120;

    private static final Logger LOG = LoggerFactory.getLogger(PasswordResetToken.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime expiration;


    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, User user, LocalDateTime creationDateTime, int expirationInMinutes) {
        if(token == null || user == null || creationDateTime == null){
            throw new IllegalArgumentException("Token, User, and creationDateTime cannot be null");
        }
        if(expirationInMinutes == 0){
            LOG.warn("Token expiration set to 0 minutes.  Assigning default value of {}", DEFAULT_TOKEN_EXPIRATION_IN_MINUTES);
            expirationInMinutes = DEFAULT_TOKEN_EXPIRATION_IN_MINUTES;
        }

        this.token = token;
        this.user = user;
        this.expiration = creationDateTime.plusMinutes(expirationInMinutes);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetToken that = (PasswordResetToken) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
