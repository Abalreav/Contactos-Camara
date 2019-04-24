package com.temas.selectos.contactos;

public class Contacto {
    private String nmbreContact;
    private String telefono;
    private String Correo;
    private int idFoto;

    public Contacto(String telefono, String correo, int idFoto) {
        this.telefono = telefono;
        Correo = correo;
        this.idFoto = idFoto;
    }


    public String getNmbreContact() {
        return nmbreContact;
    }

    public void setNmbreContact(String nmbreContact) {
        this.nmbreContact = nmbreContact;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }


    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }
}
