package team.service;

import team.domian.Architect;
import team.domian.Designer;
import team.domian.Employee;
import team.domian.Programmer;

/**
 * ClassName: TeamService
 * Package: team.service
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/6 22:14
 * @Version 1.0
 */
public class TeamService {
    private int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;


    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++) {
            team[i] = this.team[i];

        }
        return team;
    }
    public void addMember(Employee e) throws TeamException{
        if(total >= MAX_MEMBER){
            throw new TeamException("成员已满,无法添加");
        }if(!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        Programmer p = (Programmer) e;
        Status status = p.getStatus();
        switch (status){
            case BUSY:
                throw new TeamException("该员工已经是某团队成员");
            case VOCATION:
                throw new TeamException("该员工正在休假");
        }
        int progNum = 0, desNum = 0, arcNum = 0;
        for (int i = 0; i < total; i++) {
            if(team[i] instanceof Architect){
                arcNum++;
            }else if(team[i] instanceof Designer){
                desNum++;
            }else {
                progNum++;
            }
        }
        if (p instanceof Architect){
            if(arcNum >= 1){
                throw new TeamException("当前团队最多有1名架构师");
            }
        }else if (p instanceof Designer){
            if(desNum >= 2){
                throw new TeamException("当前团队最多有2名设计师");
            }
        }else {
            if(progNum >= 3){
                throw new TeamException("当前团队最多有3名程序员");
            }
        }
        //到这里，则p可以添加到team数组中
        team[total++] = p;
        p.setMemberld(counter++);
        p.setStatus(Status.BUSY);
    }

    public void removeMember(int memberid) throws TeamException{
        int i = 0;
        for (; i < total; i++) {
            if(team[i].getMemberld()==memberid){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if (i == total){
            throw new TeamException("查无此人，删除失败");
        }

        for (int j = i; j < total-1; j++) {
            team[j] = team[j +1];
        }
        team[--total] = null;
    }
}
