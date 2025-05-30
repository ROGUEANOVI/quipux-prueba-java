package com.quipux.prueba_java.config;

import com.quipux.prueba_java.entity.*;
import com.quipux.prueba_java.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TrackRepository trackRepository;
    private final PlaylistRepository playlistRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedDatabase() {

        return args -> {
            Role userRole = Role.builder().name("ROLE_USER").build();
            Role adminRole = Role.builder().name("ROLE_ADMIN").build();
            roleRepository.saveAll(List.of(userRole, adminRole));

            User user1 = User.builder().email("juan@example.com").password(passwordEncoder.encode("Xyz123AB")).role(userRole).build();
            User user2 = User.builder().email("ana@example.com").password(passwordEncoder.encode("aBcD9876")).role(userRole).build();
            User user3 = User.builder().email("admin@example.com").password(passwordEncoder.encode("Pa55wordX")).role(adminRole).build();
            userRepository.saveAll(List.of(user1, user2, user3));

            Track track1 = Track.builder().title("Rayando el sol").artist("Maná").album("Falta Amor").genre("Rock").year("1990").build();
            Track track2 = Track.builder().title("De música ligera").artist("Soda Stereo").album("Canción Animal").genre("Rock").year("1990").build();
            Track track3 = Track.builder().title("Ojos así").artist("Shakira").album("¿Dónde están los ladrones?").genre("Pop").year("1998").build();
            Track track4 = Track.builder().title("Me enamora").artist("Juanes").album("La vida... es un ratico").genre("Pop").year("2007").build();
            Track track5 = Track.builder().title("Corazón espinado").artist("Santana ft. Maná").album("Supernatural").genre("Rock").year("1999").build();
            Track track6 = Track.builder().title("Limón y sal").artist("Julieta Venegas").album("Limón y sal").genre("Pop").year("2006").build();
            Track track7 = Track.builder().title("La bicicleta").artist("Shakira ft. Carlos Vives").album("El Dorado").genre("Pop").year("2017").build();
            Track track8 = Track.builder().title("Eres").artist("Café Tacvba").album("Cuatro caminos").genre("Alternativo").year("2003").build();
            trackRepository.saveAll(List.of(track1, track2, track3, track4, track5, track6, track7, track8));

            Playlist playlist1 = Playlist.builder()
                    .name("Rock en Español")
                    .description("Lo mejor del rock latinoamericano")
                    .user(user1)
                    .tracks(List.of(track1, track2, track5, track8))
                    .build();

            Playlist playlist2 = Playlist.builder()
                    .name("Pop Latino")
                    .description("Grandes éxitos del pop en español")
                    .user(user2)
                    .tracks(List.of(track3, track4, track6, track7))
                    .build();

            Playlist playlist3 = Playlist.builder()
                    .name("Rock en Español copia")
                    .description("Lo mejor del rock latinoamericano (copia)")
                    .user(user1)
                    .tracks(List.of(track1, track2, track5, track8))
                    .build();

            playlistRepository.saveAll(List.of(playlist1, playlist2, playlist3));
        };
    }
}

