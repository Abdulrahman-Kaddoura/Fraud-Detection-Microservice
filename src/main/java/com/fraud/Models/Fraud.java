package com.fraud.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "fraud", schema = "fraud")
@Audited
public class Fraud {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fraud_id")
    private UUID fraudId;

    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "reason")
    private String reason;
}
