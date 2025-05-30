package com.quipux.prueba_java.entity;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.constant.Values;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = Messages.ROLE_TABLE_NAME)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = Values.ROLE_NAME_LENGTH)
    private String name;
}
