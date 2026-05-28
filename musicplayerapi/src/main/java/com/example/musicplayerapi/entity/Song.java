package com.example.musicplayerapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "songs")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    private String album;

    @Column(name = "file_url")
    private String fileUrl;
}