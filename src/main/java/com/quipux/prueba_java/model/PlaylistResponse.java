package com.quipux.prueba_java.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistResponse {

    private String name;
    private String description;
    private List<TrackDto> tracks;
}
