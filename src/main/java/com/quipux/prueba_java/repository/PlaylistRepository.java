package com.quipux.prueba_java.repository;

import com.quipux.prueba_java.entity.Playlist;
import com.quipux.prueba_java.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findByUser(User user);

    Optional<Playlist> findByNameAndUser(String name, User user);

    void deleteByNameAndUser(String name, User user);

    boolean existsByNameAndUser(String name, User user);
}
