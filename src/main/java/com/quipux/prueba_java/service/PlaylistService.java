package com.quipux.prueba_java.service;

import com.quipux.prueba_java.entity.User;
import com.quipux.prueba_java.model.PlaylistRequest;
import com.quipux.prueba_java.model.PlaylistResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    PlaylistResponse createPlaylist(@Valid PlaylistRequest playlistRequest);

    List<PlaylistResponse> getAllPlaylistsByUser();

    PlaylistResponse getPlaylistByName(String name);

    void deletePlaylistByName(String name);
}
