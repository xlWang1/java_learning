package team.junit;

import org.junit.Test;
import team.domian.Employee;
import team.service.NameListService;
import team.service.TeamException;

/**
 * ClassName: NameListTest
 * Package: team.junit
 * Description: 垃圾代码
 *
 * @Author 王小龙
 * @Create 2024/3/6 21:15
 * @Version 1.0
 */
public class NameListTest {
    @Test
    public void testGetAllEmployees(){
        NameListService nameListService = new NameListService();
        Employee[] employees =  nameListService.getAllEmployees();
        for (int i = 0; i < employees.length; i++) {
            System.out.println(employees[i]);
        }
    }
    @Test
    public void testGetEmployee(){
        try{
            NameListService nameListService = new NameListService();
//            Scanner input = new Scanner(System.in);
//            System.out.println("请输入");
//            int id1 = input.nextInt();
            int id = 3;
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }
    }

}
