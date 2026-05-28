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
}