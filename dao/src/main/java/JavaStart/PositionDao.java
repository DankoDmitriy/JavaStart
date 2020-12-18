package JavaStart;

import java.util.List;

public interface PositionDao {
    Position add(Position position);

    List<Position> getAll();

    Position getById(Long id);

    Position update(Position position);

    void remove(Position position);

    void delete(Integer id);
}
