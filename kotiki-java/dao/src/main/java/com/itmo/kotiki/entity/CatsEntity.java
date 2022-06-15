package com.itmo.kotiki.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cats", schema = "public", catalog = "postgres")
public class CatsEntity {
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
    @Column(name = "color", nullable = true, length = 30)
    private String color;
    @Basic
    @Column(name = "breed", nullable = true, length = 30)
    private String breed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id", referencedColumnName = "id")
    private HumanEntity humanEntity;

    public CatsEntity() {
    }

    public CatsEntity(String name, Date dateBirthday, ColorCat color, String breed) {
        this.name = name;
        this.dateBirthday = dateBirthday;
        this.breed = breed;
        this.color = color.getNum();
    }


    public HumanEntity getHumanEntity() {
        return humanEntity;
    }

    public void setHumanEntity(HumanEntity humanEntity) {
        this.humanEntity = humanEntity;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "id: " + id + ", name:" + this.name + " , date birthday:" + this.dateBirthday + ", color:" + this.color + ", breed:" + this.breed;
    }

}
