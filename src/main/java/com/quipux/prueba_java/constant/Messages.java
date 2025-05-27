package com.quipux.prueba_java.constant;

public class Messages {

    public static final String TITLE_REQUIRED = "El título no puede estar vacío";
    public static final String ARTIST_REQUIRED = "El artista no puede estar vacío";
    public static final String ALBUM_REQUIRED = "El álbum no puede estar vacío";
    public static final String YEAR_REQUIRED = "El año no puede estar vacío";
    public static final String GENRE_REQUIRED = "El género no puede estar vacío";
    public static final String YEAR_FORMAT = "El año debe tener 4 caracteres";

    public static final String NAME = "name";
    public static final String NAME_REQUIRED = "El nombre de la lista no puede estar vacío";
    public static final String DESCRIPTION_REQUIRED = "La descripción no puede estar vacía";
    public static final String TRACKS_REQUIRED = "La lista de canciones no puede estar vacía";
    public static final String TRACKS_SIZE_REQUIRED = "Debe haber al menos una canción en la lista";

    public static final String USER_TABLE_NAME = "\"user\"";
    public static final String MAPPED_BY_USER = "user";

    public static final String ROLE_TABLE_NAME = "role";
    public static final String ROLE_ID = "role_id";

    public static final String PLAYLIST_TABLE_NAME = "playlist";
    public static final String PLAYLIST_TRACK_TABLE_NAME = "playlist_track";
    public static final String PLAYLIST_ID = "playlist_id";
    public static final String USER_ID = "user_id";

    public static final String TRACK_TABLE_NAME = "track";
    public static final String TRACK_ID = "track_id";
    public static final String MAPPED_BY_TRACKS = "tracks";
    public static final String COLUMN_YEAR_NAME = "release_year";

    public static final String SPLIT_REGEX = "\\.";

    public static final String NAME_EXISTS = "Ya existe una lista con el mismo nombre";

    public static final String PLAYLIST_NOT_FOUND = "No se ha encontrado ninguna lista con el nombre %s";

    public static final String MESSAGE = "message";


    private Messages() {}
}
