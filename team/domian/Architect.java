package team.domian;

/**
 * ClassName: Architect
 * Package: team.domian
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/5 23:28
 * @Version 1.0
 */
public class Architect extends Designer{
    private int stock;

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bouns, int stock) {
        super(id, name, age, salary, equipment, bouns);
        this.stock = stock;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return getDetails() + "\t架构师\t" + getStatus() + "\t"+ getBouns() +"\t"+getStock()+"\t"  + getEquipment().getDescription();
    }
    public String getDetailsForTeam(){
        return getBasicDetailsorTeam() + "架构师\t" + getBouns() + "\t" + getStock();
    }
}
