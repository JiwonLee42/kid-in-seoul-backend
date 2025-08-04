package app.kidsInSeoul.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Events,Long> {
}
