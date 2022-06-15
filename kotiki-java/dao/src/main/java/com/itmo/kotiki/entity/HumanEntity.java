package com.itmo.kotiki.entity;

import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "human", schema = "public", catalog = "postgres")
public class HumanEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = 30)
    private String name;
    @Basic
    @Column(name = "date_birthday", nullable = true)
    private Date dateBirthday;
    @Basic
    @Column(name = "username", nullable = true, length = 30)
    private String username;
    @OneToMany(mappedBy = "humanEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CatsEntity> catsById;

    public HumanEntity() {
    }
    public HumanEntity(String name, Date dateBirthday, String username) {
        this.name = name;
        this.dateBirthday = dateBirthday;
        this.catsById = new HashSet<>();
        this.username = username;
    }

    public void addCat(CatsEntity cat) {
        cat.setHumanEntity(this);
        this.catsById.add(cat);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(Date dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Set<CatsEntity> getCatsById() {
        return catsById;
    }

    public void setCatsById(Set<CatsEntity> catsById) {
        this.catsById = catsById;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name:" + this.name + " , date birthday:" + this.dateBirthday;
    }
}
