package br.edu.ifsul.bcc.lpoo.cv.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa {
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataUltimaVisita;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pet> pets;

    public Cliente() {
        this.setTipo("C");
        pets = new ArrayList();
    }

    public Calendar getDataUltimaVisita() {
        return dataUltimaVisita;
    }

    public void setDataUltimaVisita(Calendar dataUltimaVisita) {
        this.dataUltimaVisita = dataUltimaVisita;
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