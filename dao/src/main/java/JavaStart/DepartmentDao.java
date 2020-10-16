package JavaStart;

import java.util.List;

public interface DepartmentDao {
    Department add(Department department);

    //    get all
    List<Department> getAll();

    //    get by id
    Department getById(Long id);

    //    update
    Department update(Department department);

    //    delete
    void remove(Department department);

    //     delete by ID
    void delete(Integer id);
}
