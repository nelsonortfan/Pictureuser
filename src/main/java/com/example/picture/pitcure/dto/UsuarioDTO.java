package com.example.picture.pitcure.dto;

import lombok.Data;

public class UsuarioDTO {

    private String nombre;
    private String imagenBase64;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public void setImagenBase64(String imagenBase64) {
        this.imagenBase64 = imagenBase64;
    }
}
