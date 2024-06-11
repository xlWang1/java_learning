package team.domian;

/**
 * ClassName: PC
 * Package: team.domian
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/5 22:24
 * @Version 1.0
 */
public class PC implements Equipment{
    private String model;
    private String display;

    public PC() {
    }

    public PC(String model, String display) {
        this.model = model;
        this.display = display;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String getDescription() {
        return model + "(" + display + ")";
    }
}
