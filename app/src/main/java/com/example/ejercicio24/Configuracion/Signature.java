package com.example.ejercicio24.Configuracion;

public class Signature {

    private Integer id;
    private byte[] image;
    private String descripcion;

    public Signature() {

    }
    public Signature(Integer id, byte[] image, String descripcion) {
        this.id = id;
        this.image = image;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
