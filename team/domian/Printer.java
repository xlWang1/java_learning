package team.domian;

/**
 * ClassName: Printer
 * Package: team.domian
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/5 22:33
 * @Version 1.0
 */
public class Printer implements Equipment{
    private String name;
    private String type;

    public Printer() {
    }
    public Printer(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String getDescription(){
        return name + "(" + type + ")";
    }
}
