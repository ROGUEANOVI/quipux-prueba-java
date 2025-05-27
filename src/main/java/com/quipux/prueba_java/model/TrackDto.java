package com.quipux.prueba_java.model;

import com.quipux.prueba_java.constant.Messages;
import com.quipux.prueba_java.constant.Values;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackDto {

    @NotBlank(message = Messages.TITLE_REQUIRED)
    private String title;

    @NotBlank(message = Messages.ARTIST_REQUIRED)
    private String artist;

    @NotBlank(message = Messages.ALBUM_REQUIRED)
    private String album;

    @NotBlank(message = Messages.YEAR_REQUIRED)
    @Size(min = Values.YEAR_LENGTH, max = Values.YEAR_LENGTH, message = Messages.YEAR_FORMAT)
    private String year;

    @NotBlank(message = Messages.GENRE_REQUIRED)
    private String genre;
}
