package team.domian;

/**
 * ClassName: Designer
 * Package: team.domian
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/5 23:24
 * @Version 1.0
 */
public class Designer extends Programmer{
    private double bouns;

    public double getBouns() {
        return bouns;
    }

    public void setBouns(double bouns) {
        this.bouns = bouns;
    }

    public Designer(int id, String name, int age, double salary, Equipment equipment, double bouns) {
        super(id, name, age, salary, equipment);
        this.bouns = bouns;
    }

    @Override
    public String toString() {
        return getDetails() + "\t设计师\t" + getStatus() + "\t"+ getBouns() +"\t\t\t"  + getEquipment().getDescription();
    }
    public String getDetailsForTeam(){
        return getBasicDetailsorTeam() + "设计师\t" + getBouns();
    }
}
