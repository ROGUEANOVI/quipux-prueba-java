package com.quipux.prueba_java.service;

import com.quipux.prueba_java.entity.User;
import com.quipux.prueba_java.model.PlaylistRequest;
import com.quipux.prueba_java.model.PlaylistResponse;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    PlaylistResponse createPlaylist(PlaylistRequest playlistRequest, User user);

    List<PlaylistResponse> getAllByUser(User user);

    Optional<PlaylistResponse> getByName(String name, User user);

    void deleteByName(String name, User user);

    boolean existsByName(String name, User user);
}
