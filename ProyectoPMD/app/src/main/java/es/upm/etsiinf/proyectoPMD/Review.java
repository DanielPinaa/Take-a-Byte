package es.upm.etsiinf.proyectoPMD;


public class Review {
    private int id;
    private String titulo;
    private String descripcion;
    private float valoracion;

    public Review(String titulo, String descripcion, float nota) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.valoracion = nota;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float nota) {
        this.valoracion = nota;
    }
}

