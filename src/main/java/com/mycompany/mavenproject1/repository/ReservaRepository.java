package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.Reserva;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservaRepository {

    private final Map<Long, Reserva> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Reserva save(Reserva reserva) {
        if (reserva.getId() == null) {
            reserva.setId(idSequence.getAndIncrement());
        }
        storage.put(reserva.getId(), reserva);
        return reserva;
    }

    public List<Reserva> findAll() {
        return storage.values().stream()
                .sorted(Comparator.comparing(Reserva::getId).reversed())
                .toList();
    }

    public Optional<Reserva> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public long count() {
        return storage.size();
    }
}
