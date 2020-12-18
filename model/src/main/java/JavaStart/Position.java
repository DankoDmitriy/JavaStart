package JavaStart;

public class Position {
    private Long id;
    private boolean status;
    private String positionName;
    private Long subordinationLevel;
    private String description;
    private Department department;

    public Position() {
    }

    public Position(Long id, boolean status, String positionName, Long subordinationLevel, Department department) {
        this.id = id;
        this.status = status;
        this.positionName = positionName;
        this.subordinationLevel = subordinationLevel;
        this.department = department;
    }

    public Position(Long id, boolean status, String positionName, Long subordinationLevel, String description, Department department) {
        this.id = id;
        this.status = status;
        this.positionName = positionName;
        this.subordinationLevel = subordinationLevel;
        this.description = description;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getSubordinationLevel() {
        return subordinationLevel;
    }

    public void setSubordinationLevel(Long subordinationLevel) {
        this.subordinationLevel = subordinationLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", status=" + status +
                ", positionName='" + positionName + '\'' +
                ", subordinationLevel=" + subordinationLevel +
                ", description='" + description + '\'' +
                ", department=" + department +
                '}';
    }
}
