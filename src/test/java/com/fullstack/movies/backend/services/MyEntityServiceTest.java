package com.fullstack.movies.backend.services;

import com.fullstack.movies.backend.exceptions.NotFoundException;
import com.fullstack.movies.backend.models.converters.MyEntityConverter;
import com.fullstack.movies.backend.models.dtos.MyDto;
import com.fullstack.movies.backend.models.entities.MyEntity;
import com.fullstack.movies.backend.repositories.MyEntityRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MyEntityServiceTest {

    @InjectMocks
    private MyEntityService myEntityService;
    @Mock
    private MyEntityRepository myEntityRepository;
    @Mock
    private MyEntityConverter myEntityConverter;
    @Mock
    static Clock clock;

    static MyDto defaultMyDto;
    static MyEntity defaultMyEntity;

    static UUID defaultId = UUID.randomUUID();

    @BeforeAll
    public static void setUp() {
        clock = Clock.fixed(Instant.now(), Clock.systemDefaultZone().getZone());

        String defaultName = "John";
        Integer defaultAge = 25;

        defaultMyDto = createMyDto(defaultName, defaultAge);
        defaultMyEntity = createMyEntity(defaultName, defaultAge);
    }

    @Test
    void testRegisterMyEntity_ok_returnsMyDto() {

        Mockito.when(myEntityRepository.save(Mockito.any())).thenReturn(defaultMyEntity);
        Mockito.when(myEntityConverter.convert(Mockito.any())).thenReturn(defaultMyDto);

        MyDto result = myEntityService.registerMyEntity(defaultMyDto);

        Mockito.verify(myEntityRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(myEntityConverter, Mockito.times(1)).convert(Mockito.any());

        assertEquals(defaultMyDto, result);
    }

    @Test
    void testGetById_ok_returnsMyDto() {

        Mockito.when(myEntityRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(defaultMyEntity));
        Mockito.when(myEntityConverter.convert(Mockito.any(MyEntity.class))).thenReturn(defaultMyDto);

        MyDto result = myEntityService.getById(defaultId);

        Mockito.verify(myEntityRepository, Mockito.times(1)).findById(defaultId);

        assertEquals(defaultMyDto, result);
    }

    @Test
    void testGetById_notFound_throwsNotFoundException() {

        Mockito.when(myEntityRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        Mockito.verify(myEntityConverter, Mockito.never()).convert(Mockito.any(MyEntity.class));

        assertThrows(NotFoundException.class, () -> myEntityService.getById(defaultId));
    }

    // Helper methods
    private static MyDto createMyDto(String name, Integer age) {
        return MyDto.builder()
                .name(name)
                .age(age)
                .build();
    }

    private static MyEntity createMyEntity(String name, Integer age) {
        MyEntity entity = MyEntity.builder()
                .id(defaultId)
                .name(name)
                .age(age)
                .build();

        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());

        return entity;
    }
}
