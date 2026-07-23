package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "LOG_REQUEST")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String requestGUID;

    @Column(length = 255)
    private String applicationName;

    @Column(length = 255)
    private String accountName;

    @Column(length = 255)
    private String realmName;

    @Column(length = 50)
    private String clientIP;

    @Column(length = 255)
    private String globalReference;

    @Column(length = 255)
    private String localReference;

    private Integer totalRecords;

    private Boolean enableGovernmentLogging;

    private Boolean enableBlindLogging;

    private Boolean enableEncryptedLogging;

    @Column(length = 255)
    private String encryptionKeyGroup;

    private Boolean enableForwardLog;

    private Boolean enableAutoMultiInsert;

    private Boolean enableCompressBlobs;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String logDetailXml;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String rawPayload;

}
