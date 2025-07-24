package vn.edu.eiu.temp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "school")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    @Id
    @Column(name = "school_id", length = 3)
    private String schoolId;

    @Column(name = "school_name", length = 100, nullable = false)
    private String schoolName;

    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Student> students;

    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Major> majors;

    public School(String schoolId, String schoolName, String location) {
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.location = location;
    }
}
