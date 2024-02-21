package com.gamesstorebe.entity.image;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "image_avatar")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Transient

    private String path;

    public String getPath() {
        return "http://localhost:8888/api/v1/file/image/avatar/" + name;
    }
}