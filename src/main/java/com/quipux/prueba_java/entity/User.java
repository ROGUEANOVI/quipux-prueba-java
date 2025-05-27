package com.quipux.prueba_java.entity;

import com.quipux.prueba_java.constant.Messages;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = Messages.USER_TABLE_NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = Messages.ROLE_ID, nullable = false)
    private Role role;

    @OneToMany(mappedBy = Messages.MAPPED_BY_USER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Playlist> playlists;
}
