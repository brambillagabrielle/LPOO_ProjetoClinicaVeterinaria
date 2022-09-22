package br.edu.ifsul.bcc.lpoo.cv.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cliente extends Pessoa {

    private Calendar data_ultima_visita;
    private List<Pet> pets;

    public Cliente() {
        pets = new ArrayList();
    }

    public Calendar getData_ultima_visita() {
        return data_ultima_visita;
    }

    public void setData_ultima_visita(Calendar data_ultima_visita) {
        this.data_ultima_visita = data_ultima_visita;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setPet(Pet pet) {
        this.pets.add(pet);
    }
    
}