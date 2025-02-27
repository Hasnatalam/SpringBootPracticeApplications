package com.hasnat.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shopkeepers")
public class Shopkeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;
}
