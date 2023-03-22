package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    Owner returnOwner;
    String smith1 = "Smith";

    @InjectMocks
    private OwnerSDJPAService service;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1l).lastName(smith1).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(smith1);

        assertEquals(smith1, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(Owner.builder().id(1l).build());
        returnOwnerSet.add(Owner.builder().id(2l).build());

        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());

    }

    @Test
    void findById() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = service.findById(1L);
        assertNotNull(owner);

    }

    @Test
    void findByIdNotFound() {

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = service.findById(1L);
        assertNull(owner);

    }

    @Test
    void save() {

        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}