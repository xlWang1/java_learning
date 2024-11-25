import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bytedeco.javacpp.opencv_videostab;

import java.io.*;
import java.util.*;

public class User extends Thread {
    private String getStudentUrl = UrlSet.ACCOUNTLIST_URL;
    public File stuinfofile = new File("YiDongZhanV2\\src\\main\\resources\\stuinfo.txt");
    public HttpClientUtils client;
    private boolean isGetSportStandard = false;
    private String tempSportId = null;


    public User(HttpClientUtils client) {
        this.client = client;
    }
    public void getSportSkuList(){
        String url = UrlSet.SPORTS_URL;
        Map<String, String> data = Headers.getInstance().getHeaders();
        //读取场景代码
        try {
            BufferedReader br = new BufferedReader(new FileReader("YiDongZhanV2\\src\\main\\resources\\sceneId.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] sceneInfo = line.split(",");
                String sceneCode = sceneInfo[2];

                data.put("sceneId", sceneCode);//2002:组织测试，2001：自主测试,4002：运动会
                data.put("appCode", "102101");
                data.put("delFlag", "false");
                JSONObject jsondata = client.DO_POST(url, JSONObject.fromObject(data));
                saveSportInfo(jsondata, sceneCode);
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveSportInfo(JSONObject jsondata,String sceneId){
        File sportFile = new File("YiDongZhanV2\\src\\main\\resources\\sportSkuListInfo" + sceneId + ".txt");

        try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(sportFile))){
            JSONObject data = JSONObject.fromObject(jsondata.get("data"));
            JSONArray dataArray = data.getJSONArray("records");
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject dataItem = dataArray.getJSONObject(i);
                String sportSkuId = dataItem.getString("sportSkuId");
                String sportSkuName = dataItem.getString("sportSkuName");
                //measureUnitCode中如果存在·则替换为.
                String measureUnitCode = dataItem.getString("measureUnitCode");
                if(measureUnitCode.contains("·")){
                    measureUnitCode = measureUnitCode.replace("·", ".");
                }
                bw.write((sportSkuId + "," + sportSkuName + "," + measureUnitCode + "\n").getBytes());
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void getStuInfo(){

        JSONObject map = JSONObject.fromObject("{}");
        JSONObject json =client.DO_POST(getStudentUrl, map);
        SaveStuInfo(json);
    }
    public void SaveStuInfo(JSONObject json){
        try(FileOutputStream fos = new FileOutputStream(stuinfofile);
            BufferedOutputStream bw = new BufferedOutputStream(fos))
        {
            JSONArray dataArray = json.getJSONArray("data");
            List<String[]> resultList = new ArrayList<>();
            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject dataItem = dataArray.getJSONObject(i);
                String accountId = dataItem.getString("accountId");
                String accountName = dataItem.getString("accountName");
                String gradeId = dataItem.getString("gradeId");
                //当前年级为空，则不写入文件
                if(gradeId != "null"){
                    resultList.add(new String[]{accountId, accountName, gradeId});
                }
            }
            for (String[] item : resultList) {

                bw.write((item[0] + "," + item[1] + "," + item[2] + "\n").getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    //获取场景代码
    public void getSceneId(){
        String url = UrlSet.SCENE_URL;
        Map<String, String> headers = Headers.getInstance().getHeaders();
        headers.put("skuStatus", "false");
        headers.put("appCode", "102101");
        JSONObject jsondata = client.DO_POST(url, JSONObject.fromObject(headers));
        JSONArray dataArray = JSONArray.fromObject(jsondata.get("data"));

        try(FileOutputStream fos = new FileOutputStream(new File("YiDongZhanV2\\src\\main\\resources\\sceneId.txt"));
            BufferedOutputStream bw = new BufferedOutputStream(fos))
            {
                for ( int i = 0; i < dataArray.size(); i++) {
                    JSONObject dataItem = dataArray.getJSONObject(i);
                    String sceneId = dataItem.getString("id");
                    String sceneName = dataItem.getString("sceneName");
                    String sceneCode = dataItem.getString("sceneCode");
                    //写入文件
                    bw.write((sceneId + "," + sceneName + "," + sceneCode + "\n").getBytes());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    public void upload(String secenId){
        ArrayList sportInfoList = new SportList(secenId).sportInfoList;
        if (secenId.equals("2002")){
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入测试计划名称：");
            String planName = scanner.nextLine();
            scanner.close();
            HashMap<String, String> testPlanList = getTestPlanList(planName);
            if (testPlanList == null){
                System.out.println("未找到测试计划，请检查测试计划名称及当前登录账号");
                return;
            }
            uploadoforganize(secenId, sportInfoList, testPlanList);
        }else if (secenId.equals("2001")){
            uploadResultOfFree(secenId, sportInfoList);
        }

    }

    private void uploadoforganize(String secenId, ArrayList sportInfoList, HashMap<String, String> testPlanList) {
        String url = UrlSet.UPLOAD_URL;
        SportDetailOfUpload sportDetail = new SportDetailOfUpload();
        //转化为json
        JSONObject stuAndSportId = JSONObject.fromObject(testPlanList.get("students"));
        String planid = testPlanList.get("planId");
        //标准id
        String standardId = testPlanList.get("standardId");
        String sportSkuUnit = null;
        try {
            for ( int i = 0; i < stuAndSportId.size(); i++) {
                //键
                String stuId = stuAndSportId.names().get(i).toString();
                //值
                JSONArray sportIdsJsonArray = JSONArray.fromObject(stuAndSportId.getString(stuId));
                for (int j = 0; j < sportIdsJsonArray.size(); j++){
                    JSONObject sportIdItem = JSONObject.fromObject(sportIdsJsonArray.get(j));
                    String sportId = null;
                    if (testPlanList.get("standardType").equals("TC")){
                        sportId = sportIdItem.getString("sportProjectCode");
                    }else {
                        sportId = sportIdItem.getString("sportSkuId");
                    }
                    for (int m = 0; m < sportInfoList.size(); m++) {
                        String[] spo = (String[]) sportInfoList.get(m);
                        if (spo.length >= 3) {
                            String sportSkuId = spo[0];
                            if (sportSkuId.equals(sportId)){
                                sportSkuUnit = spo[2];
                                break;
                            }
                        }
                    }
                    JSONObject uploaddata = sportDetail.getSportDetail(client,secenId, stuId, sportId, planid, standardId,sportSkuUnit);
                    JSONObject jsondata;
                    try {
                        jsondata = client.DO_POST(url, JSONObject.fromObject(uploaddata));
                    } catch (Exception e) {
                        Thread.sleep(10);
                        continue;
                    }
                    if (jsondata == null){
                        Thread.sleep(5);
                    }
                    else if (!jsondata.get("msg").equals("操作成功")){
                        System.out.println(uploaddata);
                        System.out.println("上传失败：" + jsondata);
                    }
                }
                int done = i + 1;
                System.out.println(done);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void uploadResultOfFree(String secenId,ArrayList sportInfoList) {
        String url = UrlSet.UPLOAD_URL;
        SportDetailOfUpload sportDetail = new SportDetailOfUpload();
        ReadStuFromFile readStuFromFile = new ReadStuFromFile();
        try {
            for (int i = 0; i < sportInfoList.size(); i++) {
                String[] spo = (String[]) sportInfoList.get(i);
                if (spo.length >= 3){
                    String sportSkuId = spo[0];
                    String sportSkuUnit = spo[2];
                    for (Object stuo : readStuFromFile.stuInfoList) {

                        String[] stuInfo = (String[]) stuo;
                        String stuId = stuInfo[0];
                        JSONObject sportdata = sportDetail.getSportDetail(secenId,stuId, sportSkuId,sportSkuUnit);
                        JSONObject jsondata = client.DO_POST(url, JSONObject.fromObject(sportdata));
                        if (!jsondata.get("msg").equals("操作成功")){
                            System.out.println(sportdata);
                            System.out.println(jsondata);
                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getTestPlanList(String planname) {
        String url = UrlSet.TESTPLANLIST_URL;
        JSONObject data = JSONObject.fromObject("{\"current\": 1, \"pageSize\": 500, \"standardType\": \"\", \"status\": 0}");
        JSONObject respjson = client.DO_POST(url, data);
        if (respjson.get("code").equals(200)){
            JSONObject dataJson = respjson.getJSONObject("data");
            JSONArray planlist = dataJson.getJSONArray("records");
            HashMap<String, String> planinfo = new HashMap<>();
            //遍历计划列表
            for (int i = 0; i < planlist.size(); i++) {
                JSONObject planItem = planlist.getJSONObject(i);
                String planName = planItem.getString("planName");
                if (planName.equals(planname)){
                    String planId = planItem.getString("planId");
                    String planStatus = planItem.getString("status");
                    String standardType = planItem.getString("standardType");
                    String standardId = planItem.getString("standardId");
                    if (planStatus.equals("2")) {
                        planinfo.put("planName",planName);
                        planinfo.put("planId",planId);
                        planinfo.put("standardType",standardType);
                        planinfo.put("standardId",standardId);
                        HashMap<String, String> students = getPlanStu(planId);
                        planinfo.put("students",students.toString());
                        return planinfo;
                    }else {
                        System.out.println("该计划未发布或已结束，请检查计划名称是否正确");
                    }
                }
            }
        }
        return null;
    }
    //获取标准详细
    public JSONObject getStandardConfig(String standardId,String sportProjectCode) {
        String url = UrlSet.STANDARD_URL;
        JSONObject data = JSONObject.fromObject("{\"sportProjectCode\": \""+ sportProjectCode + "\",\"standardId\":\""+ standardId + "\"}");
        return client.DO_POST(url, data);
    }
    //获取参加测试的学生列表
    public HashMap<String, String> getPlanStu(String planId) {
        int pageIndex = 1;
        String url = UrlSet.STUDENTLIST_URL;
        String testplanlistUrl = UrlSet.TESTPLANLIST_URL;
        JSONObject projectsInfo = client.DO_POST(testplanlistUrl, JSONObject.fromObject("{\"current\": 1, \"pageSize\": 500, \"standardType\": \"\", \"status\": 0}"));
        JSONArray projects = new JSONArray();
        for (int i = 0; i < projectsInfo.getJSONObject("data").getJSONArray("records").size(); i++){
            JSONObject projectItem = projectsInfo.getJSONObject("data").getJSONArray("records").getJSONObject(i);
            String projectId = projectItem.getString("planId");
            if (projectId.equals(planId)){
                projects = (JSONArray) projectItem.get("sportProjects");
                break;
            }
        }
        HashMap<String, String> stuofplan = new HashMap<>();
        while (true) {
            JSONObject requestdata = JSONObject.fromObject("{\"current\": " + pageIndex+ ",\"pageSize\": 500, \"planId\":\"" + planId + "\"}");
            JSONObject respjson = client.DO_POST(url, requestdata);

            if (respjson.get("code").equals(200)){
                JSONArray stuList = respjson.getJSONObject("data").getJSONArray("records");
                for (int i = 0; i < stuList.size(); i++){
                    JSONObject o = JSONObject.fromObject(stuList.get(i));
                    if (o.getJSONArray("projects").size() > 0){
                        stuofplan.put(stuList.getJSONObject(i).getString("accountId"), String.valueOf(o.getJSONArray("projects")));
                    }else {
                        stuofplan.put(stuList.getJSONObject(i).getString("accountId"), String.valueOf(projects));
                    }

                }
            }
            pageIndex++;
            if (respjson.getJSONObject("data").getJSONArray("records").size()==0){
                break;
            }
        }
        return stuofplan;
    }
}
