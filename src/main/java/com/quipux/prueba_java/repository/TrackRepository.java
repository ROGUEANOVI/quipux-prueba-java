package com.quipux.prueba_java.repository;

import com.quipux.prueba_java.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Optional<Track> findByTitleAndArtistAndAlbum(String title, String artist, String album);
}
