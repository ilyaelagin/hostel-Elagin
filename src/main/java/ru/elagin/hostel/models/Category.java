package ru.elagin.hostel.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories", schema = "hostel")
public class Category {
    @Id
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description")
    private String description;

}
