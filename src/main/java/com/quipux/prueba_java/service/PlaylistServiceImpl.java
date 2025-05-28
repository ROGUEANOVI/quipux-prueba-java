package com.quipux.prueba_java.service;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.entity.Playlist;
import com.quipux.prueba_java.entity.Track;
import com.quipux.prueba_java.entity.User;
import com.quipux.prueba_java.exception.PlaylistBadRequestException;
import com.quipux.prueba_java.exception.PlaylistNotFoundException;
import com.quipux.prueba_java.mapper.PlaylistMapper;
import com.quipux.prueba_java.model.PlaylistRequest;
import com.quipux.prueba_java.model.PlaylistResponse;
import com.quipux.prueba_java.model.TrackDto;
import com.quipux.prueba_java.repository.PlaylistRepository;
import com.quipux.prueba_java.repository.TrackRepository;
import com.quipux.prueba_java.security.UserDetailsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import static com.quipux.prueba_java.mapper.PlaylistMapper.toEntity;
import static com.quipux.prueba_java.mapper.PlaylistMapper.toResponse;

@Service
@RequiredArgsConstructor
@Validated
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistRepository playlistRepository;
    private final TrackRepository trackRepository;
    private final UserDetailsServiceImpl userDetailsService;


    @Transactional
    @Override
    public PlaylistResponse createPlaylist(@Valid PlaylistRequest playlistRequest) {

        if (playlistRepository.existsByNameAndUser(playlistRequest.getName(), getCurrentUser())) {
            throw new PlaylistBadRequestException(List.of(Map.of(Messages.NAME, Messages.NAME_EXISTS + ": " + playlistRequest.getName())));
        }

        Set<String> trackSignatures = new HashSet<>();

        List<Track> tracks = playlistRequest.getTracks().stream()
                .filter(dto -> {
                    String signature = generateTrackSignature(dto);
                    return trackSignatures.add(signature);
                })
                .map(dto -> trackRepository
                        .findByTitleAndArtistAndAlbum(dto.getTitle(), dto.getArtist(), dto.getAlbum())
                        .orElseGet(() -> trackRepository.save(toEntity(dto))))
                .toList();

        Playlist playlist = Playlist.builder()
                .name(playlistRequest.getName())
                .description(playlistRequest.getDescription())
                .tracks(tracks)
                .user(getCurrentUser())
                .build();

        Playlist playlistSaved = playlistRepository.save(playlist);
        return toResponse(playlistSaved);
    }

    @Override
    public List<PlaylistResponse> getAllPlaylistsByUser() {

        return playlistRepository.findByUser(getCurrentUser()).stream()
                .map(PlaylistMapper::toResponse)
                .toList();
    }

    @Override
    public PlaylistResponse getPlaylistByName(String name) {

        User currentUser = userDetailsService.getCurrentUser();

        Optional<PlaylistResponse> playlist = playlistRepository.findByNameAndUser(name, currentUser)
                .map(PlaylistMapper::toResponse);

        if (playlist.isEmpty()) {
            throw new PlaylistNotFoundException(Messages.PLAYLIST_NOT_FOUND, name);
        }

        return playlist.get();
    }

    @Transactional
    @Override
    public void deletePlaylistByName(String name) {

        if (!playlistRepository.existsByNameAndUser(name, getCurrentUser())) {
            throw new PlaylistNotFoundException(Messages.PLAYLIST_NOT_FOUND, name);
        }

        playlistRepository.deleteByNameAndUser(name, getCurrentUser());
    }

    private User getCurrentUser() {
        return userDetailsService.getCurrentUser();
    }

    private String generateTrackSignature(TrackDto dto) {
        return (dto.getTitle() + "|" + dto.getArtist() + "|" + dto.getAlbum()).toLowerCase().trim();
    }
}
