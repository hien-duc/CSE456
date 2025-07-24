package vn.edu.eiu.practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "major")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {

    @Id
    @Column(name = "major_id", length = 4)
    private String majorId;

    @Column(name = "major_name", length = 100, nullable = false)
    private String majorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @OneToMany(mappedBy = "major", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;

    public Major(String majorId, String majorName, School school) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.school = school;
    }
}
