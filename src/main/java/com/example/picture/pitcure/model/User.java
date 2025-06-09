package com.example.picture.pitcure.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    private String name;

    //@Lob
    @Column(columnDefinition = "TEXT")
    private String pictureBase64;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }



    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }
}
