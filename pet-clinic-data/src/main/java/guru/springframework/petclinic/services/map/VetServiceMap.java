package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Pet;
import guru.springframework.petclinic.model.Vet;
import guru.springframework.petclinic.services.CrudService;
import guru.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends  AbstractMapService<Vet, Long> implements VetService {

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }
}
