package com.moneylogger.model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "cards")
public class BankCard implements Identifiable {
//public class BankCard {
//    @Id
//    @SequenceGenerator(name = "cards_seq", sequenceName = "cards_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cards_seq")
    private Long id;

//    @Column(name = "name", nullable = false, length = 500)
    private String name = "bank card";

//    @Column(name = "last_digits", nullable = false, length = 4)
    private String lastDigits;

    private Type type = Type.DEBIT;

//    private Bank bank;

//    private BankAccount account;

    public enum Type {
        DEBIT,
        CREDIT;
    }

}
