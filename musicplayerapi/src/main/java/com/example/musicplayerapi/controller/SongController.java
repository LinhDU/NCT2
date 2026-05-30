package com.example.musicplayerapi.controller;

import com.example.musicplayerapi.entity.Song;
import com.example.musicplayerapi.respository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @PostMapping
    public Song addSong(@RequestBody Song song) {
        return songRepository.save(song);
    }
    // Trong SongController.java

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song songDetails) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài hát: " + id));

        song.setTitle(songDetails.getTitle());
        song.setArtist(songDetails.getArtist());
        song.setAlbum(songDetails.getAlbum());
        song.setFileUrl(songDetails.getFileUrl());

        return songRepository.save(song);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable Long id) {
        songRepository.deleteById(id);
    }
}