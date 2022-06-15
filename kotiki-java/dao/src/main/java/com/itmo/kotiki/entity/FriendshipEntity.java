package com.itmo.kotiki.entity;

import javax.persistence.*;

@Entity
@Table(name = "friendship", schema = "public", catalog = "postgres")
public class FriendshipEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "id_one", nullable = true)
    private Integer idOne;
    @Basic
    @Column(name = "id_two", nullable = true)
    private Integer idTwo;

    public FriendshipEntity() {
    }

    public FriendshipEntity(Integer idOne, Integer idTwo) {
        this.idOne = idOne;
        this.idTwo = idTwo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdOne() {
        return idOne;
    }

    public void setIdOne(Integer idOne) {
        this.idOne = idOne;
    }

    public Integer getIdTwo() {
        return idTwo;
    }

    public void setIdTwo(Integer idTwo) {
        this.idTwo = idTwo;
    }

    @Override
    public String toString() {
        return "id: " + id + " Id one: " + idOne + " Id two: " + idTwo;
    }
}
