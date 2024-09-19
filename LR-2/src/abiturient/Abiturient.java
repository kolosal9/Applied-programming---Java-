package abiturient;

public class Abiturient {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String address;
    private String phone;
    private double averageMark;

    public Abiturient(int id, String lastName, String firstName, String patronymic, String address, String phone, double averageMark){
        this.id=id;
        this.lastName=lastName;
        this.firstName=firstName;
        this.patronymic=patronymic;
        this.address=address;
        this.phone=phone;
        this.averageMark=averageMark;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAverageMark() {
        return averageMark;
    }
    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public String toString() {
        return "Abiturient {" +
                "id=" + id +
                ", lastName = '" + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", patronymic = '" + patronymic + '\'' +
                ", address = '" + address + '\'' +
                ", phone = '" + phone + '\'' +
                ", averageMark = " + averageMark +
                '}';
    }
}
