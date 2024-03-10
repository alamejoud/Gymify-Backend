package com.capstone.app.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "G_LOOKUPS")
public class LookupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "G_LOOKUPS_SEQ")
    @SequenceGenerator(name = "G_LOOKUPS_SEQ", sequenceName = "G_LOOKUPS_SEQ", allocationSize = 1)
    @Column(name = "LOOKUP_ID")
    private int lookupId;
    @Column(name = "LOOKUP_CODE")
    private String lookupCode;
    @Column(name = "LOOKUP_TYPE")
    private String lookupType;
    @Column(name = "LOOKUP_NAME")
    private String lookupName;
}
