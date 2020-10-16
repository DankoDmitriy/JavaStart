package JavaStart;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.*;


public class DepartmentServiceTest {
    private DepartmentService departmentService = new DepartmentService();
    private Department departmentTest1 = new Department("Test1");

    @Test
    public void addNewDepartmentTest() {
        Department departmentReturned = departmentService.add(departmentTest1);
        departmentService = new DepartmentService();
        departmentService.delete(Integer.valueOf(departmentReturned.getId().intValue()));
        assertEquals("Adding a department was fall", departmentTest1.getName(), departmentReturned.getName());
    }

    @Test
    public void getDepartmentByIdTest() {
        Department departmentReturned = departmentService.add(departmentTest1);
        departmentService = new DepartmentService();
        Department department = departmentService.getById(departmentReturned.getId());
        departmentService = new DepartmentService();
        departmentService.delete(Integer.valueOf(departmentReturned.getId().intValue()));
        assertEquals("Returned not valid object of Department", department.getName(), departmentTest1.getName());
    }

    @Test
    public void getAllTest() {
        List<Department> departments;
        departmentService.add(departmentTest1);
        departmentService = new DepartmentService();
        departments = departmentService.getAll();
        departmentService = new DepartmentService();
        departmentService.delete(departments.get(0).getId().intValue());
        assertNotNull(departments);
        assertTrue("The list is empty",departments.size() > 0);
        assertEquals("there is more data in the database than expected", departments.size(), 1);
    }

    @Test
    public void updateTest() {
        Department department = departmentService.add(departmentTest1);
        department.setName("NewName");
        departmentService = new DepartmentService();
        Department departmentAfterUpdate = departmentService.update(department);
        departmentService = new DepartmentService();
        departmentService.delete(department.getId().intValue());
        assertEquals("Objects are not equal", department, departmentAfterUpdate);
    }


}
