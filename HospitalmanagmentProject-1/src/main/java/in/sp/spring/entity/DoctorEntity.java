package in.sp.spring.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String specialization;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false)
    private String status = "ACTIVE"; // ACTIVE, INACTIVE

    @Column(nullable = false)
    private String consultingHours;

    @Column
    private String roomNumber;

    @OneToMany(mappedBy = "doctorId", cascade = CascadeType.ALL)
    private List<Appoitment> appointments = new ArrayList<>();

    // Constructors
    public DoctorEntity() {}

    public DoctorEntity(String name, String email, String phone, String specialization, 
                  String department, String qualification, int experience, 
                  String consultingHours, String roomNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.specialization = specialization;
        this.department = department;
        this.qualification = qualification;
        this.experience = experience;
        this.consultingHours = consultingHours;
        this.roomNumber = roomNumber;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getConsultingHours() { return consultingHours; }
    public void setConsultingHours(String consultingHours) { this.consultingHours = consultingHours; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public List<Appoitment> getAppointments() { return appointments; }
    public void setAppointments(List<Appoitment> appointments) { this.appointments = appointments; }
}