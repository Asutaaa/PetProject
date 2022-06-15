package com.itmo.kotiki.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "userinfo", schema = "public", catalog = "postgres")
public class UserinfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "username", nullable = true, length = 30)
    private String username;
    @Basic
    @Column(name = "password", nullable = true, length = 30)
    private String password;
    @Basic
    @Column(name = "id_human", nullable = true)
    private Long idHuman;
    @Basic
    @Column(name = "role", nullable = true, length = 30)
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public UserinfoEntity() {
    }

    public UserinfoEntity(String username, String password, Long idHuman, Role role) {
        this.username = username;
        this.password = password;
        this.idHuman = idHuman;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdHuman() {
        return idHuman;
    }

    public void setIdHuman(Long idHuman) {
        this.idHuman = idHuman;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserinfoEntity that = (UserinfoEntity) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(idHuman, that.idHuman);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, idHuman);
    }
}
