package com.moneylogger.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "records")
public class OperationRecord implements Identifiable { // todo rename to OperationRecord
    @Id
    @SequenceGenerator(name = "records_seq", sequenceName = "records_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "records_seq")
    private Long id;

    @JoinColumn(name = "category_id", referencedColumnName = "id", table = "categories")
    private Long categoryId;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column(name = "spending", nullable = false)
    private boolean spending = true; // false for income

    @Column(name = "amount", nullable = false)
    private Double amount = 0.0;

    @Column(name = "currency", length = 10, nullable = false)
    private String currencyCode = "RUR"; // валюта

    @Column(name = "cash_back", nullable = false)
    private Double cashBack = 0.0;


}
