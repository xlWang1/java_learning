package team.domian;

import team.service.Status;

/**
 * ClassName: Programmer
 * Package: team.domian
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/5 22:44
 * @Version 1.0
 */
public class Programmer extends Employee{

    private int memberld;
    private Status status=Status.FREE;
    private Equipment equipment;

    public int getMemberld() {
        return memberld;
    }

    public void setMemberld(int memberld) {
        this.memberld = memberld;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Programmer() {
    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return getDetails() + "\t程序员\t" + status + "\t\t\t\t\t"  + equipment.getDescription();
    }
    public String getBasicDetailsorTeam(){
        return memberld + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t" + getSalary();
    }
    public String getDetailsForTeam(){
        return getBasicDetailsorTeam() + "\t程序员";
    }
}
