package com.example.myapplication.Modelo;

import java.util.Date;

public class Artista {
    private String nombres;
    private String apellidos;
    private String NombreArtistico;

    public String getFotoArt() {
        return fotoArt;
    }

    public void setFotoArt(String fotoArt) {
        this.fotoArt = fotoArt;
    }

    private String fotoArt;
    private int foto;
    private Date fechaNacimiento;

    public String getNombreArtistico() {
        return NombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        NombreArtistico = nombreArtistico;
    }



    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


}
