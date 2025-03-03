package org.example.quanlytuyendung.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "job_position", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name"),
        @UniqueConstraint(columnNames = "code")
})
public class JobPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = false)
    private IndustryEntity industry;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 250, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Code không được chứa ký tự đặc biệt")
    private String code;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(length = 250)
    private String description;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
