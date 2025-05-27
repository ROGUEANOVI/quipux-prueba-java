package com.quipux.prueba_java.entity;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.constant.Values;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = Messages.TRACK_TABLE_NAME)
public class Track {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private String album;

    @Column(name = Messages.COLUMN_YEAR_NAME, nullable = false, length = Values.YEAR_LENGTH)
    private String year;

    @Column(nullable = false, length = Values.GENRE_LENGTH)
    private String genre;

    @ManyToMany(mappedBy = Messages.MAPPED_BY_TRACKS)
    private List<Playlist> playlists = new ArrayList<>();
}
