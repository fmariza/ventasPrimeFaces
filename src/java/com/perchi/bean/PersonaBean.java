

package com.perchi.bean;

import com.perchi.dao.PersonaDAO;
import com.perchi.model.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class PersonaBean {
    private Persona persona = new Persona();
    private List<Persona> lstPersonas;
    private String accion;

    public List<Persona> getLstPersonas() {
        return lstPersonas;
    }

    public void setLstPersonas(List<Persona> lstPersonas) {
        this.lstPersonas = lstPersonas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void operar() throws Exception{
    switch(accion){
        case "Registrar": this.registrar();
                          this.limpiar();
            break;
        case "Modificar": this.modificar(); 
                          this.limpiar();
            break;
    } }
    
    private void limpiar(){
    this.persona.setCodigo(0);
    this.persona.setNombre("");
    this.persona.setSexo("");
    }
    
    private void registrar() throws Exception{
        PersonaDAO dao;
        try {
        dao =new PersonaDAO();
        dao.registrar(persona);
        this.listar();
        } catch (Exception e) {
            throw e;
        }   }
    
    private void modificar() throws Exception{
        PersonaDAO dao;
        try {
        dao =new PersonaDAO();
        dao.modificar(persona);
        this.listar();
        } catch (Exception e) {
            throw e;
        }    }
    
    
    public void listar() throws Exception{
        PersonaDAO dao;
        try {
        dao =new PersonaDAO();
        lstPersonas= dao.listar();
        } catch (Exception e) {
            throw e;
        }
    
    
    }
    
    public void leerID(Persona per) throws Exception{
    PersonaDAO dao;
    Persona temp;
        try {
        dao =new PersonaDAO();
       temp= dao.leerID(per);
       if(temp != null){
       this.persona =temp;
       this.accion="Modificar";
       
       }
        
        } catch (Exception e) {
            throw e;
        }
        
    }
    
    
    
    public void eliminar(Persona per) throws Exception{
        PersonaDAO dao;
        try {
        dao =new PersonaDAO();
        dao.eliminar(per);
        this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
}
