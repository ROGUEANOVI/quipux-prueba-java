package com.quipux.prueba_java.service;

import com.quipux.prueba_java.entity.Playlist;
import com.quipux.prueba_java.entity.Track;
import com.quipux.prueba_java.entity.User;
import com.quipux.prueba_java.mapper.PlaylistMapper;
import com.quipux.prueba_java.model.PlaylistRequest;
import com.quipux.prueba_java.model.PlaylistResponse;
import com.quipux.prueba_java.model.TrackDto;
import com.quipux.prueba_java.repository.PlaylistRepository;
import com.quipux.prueba_java.repository.TrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.quipux.prueba_java.mapper.PlaylistMapper.toEntity;
import static com.quipux.prueba_java.mapper.PlaylistMapper.toResponse;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{

    private final PlaylistRepository playlistRepository;
    private final TrackRepository trackRepository;

    @Override
    public PlaylistResponse createPlaylist(PlaylistRequest playlistRequest, User user) {
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
                .user(user)
                .build();

        Playlist playlistSaved = playlistRepository.save(playlist);
        return toResponse(playlistSaved);
    }

    @Override
    public List<PlaylistResponse> getAllByUser(User user) {
        return playlistRepository.findByUser(user).stream()
                .map(PlaylistMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PlaylistResponse> getByName(String name, User user) {
        return playlistRepository.findByNameAndUser(name, user)
                .map(PlaylistMapper::toResponse);
    }
    @Override
    public void deleteByName(String name, User user) {
        playlistRepository.deleteByNameAndUser(name, user);
    }

    @Override
    public boolean existsByName(String name, User user) {
        return playlistRepository.existsByNameAndUser(name, user);
    }

    private String generateTrackSignature(TrackDto dto) {
        return (dto.getTitle() + "|" + dto.getArtist() + "|" + dto.getAlbum()).toLowerCase().trim();
    }
}
