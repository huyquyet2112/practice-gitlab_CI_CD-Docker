package org.example.quanlytuyendung.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "benifit_map")
public class BenifitMapEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "benifit_id", nullable = false)
    private BenifitEntity benifit;
    @Column(nullable = false)
    private Integer department;
}
