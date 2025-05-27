package com.quipux.prueba_java.mapper;

import com.quipux.prueba_java.entity.Playlist;
import com.quipux.prueba_java.entity.Track;
import com.quipux.prueba_java.model.PlaylistResponse;
import com.quipux.prueba_java.model.TrackDto;

import java.util.List;
import java.util.stream.Collectors;

public class PlaylistMapper {

    public static Track toEntity(TrackDto dto) {
        return Track.builder()
                .title(dto.getTitle())
                .artist(dto.getArtist())
                .album(dto.getAlbum())
                .year(dto.getYear())
                .genre(dto.getGenre())
                .build();
    }

    public static TrackDto toDto(Track track) {
        return TrackDto.builder()
                .title(track.getTitle())
                .artist(track.getArtist())
                .album(track.getAlbum())
                .year(track.getYear())
                .genre(track.getGenre())
                .build();
    }

    public static PlaylistResponse toResponse(Playlist playlist) {
        List<TrackDto> trackDtos = playlist.getTracks().stream()
                .map(PlaylistMapper::toDto)
                .collect(Collectors.toList());

        return PlaylistResponse.builder()
                .name(playlist.getName())
                .description(playlist.getDescription())
                .tracks(trackDtos)
                .build();
    }
}
