package ski.serwon.petassistant.service.vaccine;

import ski.serwon.petassistant.entity.animal.Animal;
import ski.serwon.petassistant.entity.vaccine.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    public List<Vaccine> getVaccinesOfAnimals(List<Animal> animals);
    public Vaccine getVaccineById(Long id);
    public Vaccine addVaccine(Vaccine vaccine);
    public void deleteVaccine(Long id);
    public Vaccine updateVaccine(Vaccine vaccineToUpdate);
    public List<Vaccine> getVaccineByDate(LocalDate date);
}
