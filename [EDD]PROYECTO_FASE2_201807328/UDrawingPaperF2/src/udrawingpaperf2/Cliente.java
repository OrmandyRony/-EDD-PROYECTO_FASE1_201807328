/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udrawingpaperf2;

/**
 *
 * @author orman
 */
public class Cliente {
    private String nombre;
    private String dpi;
    private String password;

    public Cliente(String nombre, String dpi, String password) {
        this.nombre = nombre;
        this.dpi = dpi;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDpi() {
        return dpi;
    }

    public String getPassword() {
        return password;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
