package com.example.musicplayerapi.respository;

import com.example.musicplayerapi.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}