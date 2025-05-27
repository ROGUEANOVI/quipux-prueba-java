package com.quipux.prueba_java.model;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.constant.Values;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaylistRequest {

    @NotBlank(message = Messages.NAME_REQUIRED)
    private String name;

    @NotBlank(message = Messages.DESCRIPTION_REQUIRED)
    private String description;

    @NotNull(message = Messages.TRACKS_REQUIRED)
    @Size(min = Values.TRACKS_SIZE_MIN, message = Messages.TRACKS_SIZE_REQUIRED)
    @Valid
    private List<TrackDto> tracks;
}
