package stream;

/**
 *
 */
class Student {
    private String name;
    private int age;
    private int course;
    private double avgMark;

    Student(String name, int age, int course, double avgMark) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.avgMark = avgMark;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    int getCourse() {
        return course;
    }

    void setCourse(int course) {
        this.course = course;
    }

    double getAvgMark() {
        return avgMark;
    }

    void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", avgMark=" + avgMark +
                '}';
    }
}
