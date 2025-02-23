package com.moneylogger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Identifiable {
    @Id
    @SequenceGenerator(name = "categories_seq", sequenceName = "categories_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_seq")
    private Long id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Long parentId;
}
