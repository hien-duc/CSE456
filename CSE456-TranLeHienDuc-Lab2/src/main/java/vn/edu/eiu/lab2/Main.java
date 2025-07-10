package vn.edu.eiu.lab2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import vn.edu.eiu.lab2.entity.Lecturer;
import vn.edu.eiu.lab2.entity.Student;
import vn.edu.eiu.lab2.entity.Subject;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu1-mysql-masterapp");

    public static void main(String[] args) {
        // goi ham them sinh vien
//        insertStudent();
//        getStudentById();
        getAllStudentBy();
        updateStudentById("STD2", "Khanh Linh", 1999, 3.75);
        deleteStudentById("STD3");

        // --- SUBJECT ---
        insertSubjects();
        getAllSubjects();
        updateSubject("S01", "Advanced Math", "Algebra & Geometry", 4, 60);
        deleteSubject("S02");

        // --- LECTURER ---
        insertLecturers();
        getAllLecturers();
        updateLecturer("L01", "Nguyen Van Dong", 1995, 1000);
        deleteLecturer("L02");
    }

    // CRUD functions
    public static void getStudentByCondition(String name, double gpa) {
        //1. tao nguoi quan ly viec tuong tac database
        EntityManager em = emf.createEntityManager();

        List<Student> students = em.createQuery("select s from Student s where s.name like :pName and s.gpa > :pGpa").setParameter("pName", "%" + name + "%").setParameter("pGpa", gpa).getResultList();
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    // tim sinh vien boi id
    public static void getStudentById() {
        //1. tao nguoi quan ly viec tuong tac database
        EntityManager em = emf.createEntityManager();

        Student std = em.find(Student.class, "STD1");
        System.out.println("Student found:" + std.toString());
        /*co the dun cu phap
         * sql thuan
         * hql: duoc chinh sua boi hibernate
         * jpql: duoc ching sua boi jpa lenh truy van theo kieu oop*/
    }


    // lay tat ca sinh vien
    public static void getAllStudentBy() {
        //1. tao nguoi quan ly viec tuong tac database
        EntityManager em = emf.createEntityManager();

        List<Student> students = em.createQuery("select s from Student s", Student.class).getResultList();
        System.out.println("All student found:");
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }


    public static void insertStudent() {
        //1. tao nguoi quan ly viec tuong tac database
        EntityManager em = emf.createEntityManager();

        //2. chuan bi data de insert
        Student std1 = new Student("STD1", "Tran Thanh", 2000, 3.33);
        Student std2 = new Student("STD2", "Tran Tuan", 2000, 3.22);
        Student std3 = new Student("STD3", "Tran Ngoc", 2000, 3.44);

        //3. nguoi quan ly thuc hien insert

/*        khi thuc thi cac cau len DML (Data Manipulation Language) co lam thay doi du lieu thi bat buoc
        phai dat trong 1 transaction de dam bao tinh ACID: la thuc thi cau lenh tu dau den cuoi, nguoc lai
        thi khong lam gi ca:roll back*/

        em.getTransaction().begin();
//        em.persist(std1); // ghi xuong db nhung chua thuc hien ghi
        em.persist(std2); // ghi xuong db nhung chua thuc hien ghi
        em.persist(std3); // ghi xuong db nhung chua thuc hien ghi
        em.getTransaction().commit(); // da ghi xuong db neu khong thanh cong thi huy
        em.close();

    }

    public static void updateStudentById(String studentId, String newName, int newYearOfBirth, double newGpa) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Find the student
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                student.setName(newName);
                student.setYob(newYearOfBirth);
                student.setGpa(newGpa);
                System.out.println("Student updated: " + student);
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteStudentById(String studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Find the student
            Student student = em.find(Student.class, studentId);
            if (student != null) {
                em.remove(student); // Remove student
                System.out.println("Student deleted: " + student);
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void insertLecturers() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Lecturer l1 = new Lecturer("L01", "Nguyen Van Dong", 1990, 5000);
        Lecturer l2 = new Lecturer("L02", "Tran Thi Bich", 1997, 5000);
        em.persist(l1);
        em.persist(l2);

        em.getTransaction().commit();
        em.close();
    }

    public static void getAllLecturers() {
        EntityManager em = emf.createEntityManager();
        List<Lecturer> lecturers = em.createQuery("select l from Lecturer l", Lecturer.class).getResultList();
        System.out.println("All Lecturers:");
        lecturers.forEach(System.out::println);
        em.close();
    }

    public static void updateLecturer(String id, String name, int yob, double salary) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Lecturer lecturer = em.find(Lecturer.class, id);
        if (lecturer != null) {
            lecturer.setName(name);
            lecturer.setYob(yob);
            lecturer.setSalary(salary);
            System.out.println("Updated Lecturer: " + lecturer);
        } else {
            System.out.println("Lecturer not found.");
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void deleteLecturer(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Lecturer lecturer = em.find(Lecturer.class, id);
        if (lecturer != null) {
            em.remove(lecturer);
            System.out.println("Deleted Lecturer: " + lecturer);
        } else {
            System.out.println("Lecturer not found.");
        }

        em.getTransaction().commit();
        em.close();
    }
    public static void insertSubjects() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Subject s1 = new Subject("S01", "Math", "Basic Math", 3, 45);
        Subject s2 = new Subject("S02", "Physics", "Introduction to Physics", 4, 60);
        em.persist(s1);
        em.persist(s2);

        em.getTransaction().commit();
        em.close();
    }

    public static void getAllSubjects() {
        EntityManager em = emf.createEntityManager();
        List<Subject> subjects = em.createQuery("select s from Subject s", Subject.class).getResultList();
        System.out.println("All Subjects:");
        subjects.forEach(System.out::println);
        em.close();
    }

    public static void updateSubject(String code, String name, String desc, int credits, int hours) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Subject subject = em.find(Subject.class, code);
        if (subject != null) {
            subject.setName(name);
            subject.setDescription(desc);
            subject.setCredits(credits);
            subject.setStudyHours(hours);
            System.out.println("Updated Subject: " + subject);
        } else {
            System.out.println("Subject not found.");
        }

        em.getTransaction().commit();
        em.close();
    }

    public static void deleteSubject(String code) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Subject subject = em.find(Subject.class, code);
        if (subject != null) {
            em.remove(subject);
            System.out.println("Deleted Subject: " + subject);
        } else {
            System.out.println("Subject not found.");
        }

        em.getTransaction().commit();
        em.close();
    }



}