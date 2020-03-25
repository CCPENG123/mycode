package cn.ccp.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    private static Map<String,String> map = new HashMap<>();

    static {
        map.put("ujdh","zz1");
        map.put("capx","zzz2");
        map.put("qvkm","zzf");
        map.put("oil","zzze");
    }

    /**
     * 读取xls中的数据
     * @param filePath 文件路径
     * @param sheetName 表名
     * @return
     * @throws Exception
     */
    public static HSSFSheet readXls(String filePath,String sheetName) throws Exception {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook book = new HSSFWorkbook(fis);
//        HSSFSheet sheet = book.getSheetAt(0);//选择表
        HSSFSheet sheet = book.getSheet(sheetName);//选择表
        return sheet;
    }

    /**
     * 生成商家码sql
     * @param qmCodeSn 商家码对应编号 eg：9610393686273
     * @param qmCodeNo 企迈规则生成的二维码编号 eg：https://qm.lcsw.cn/ujdh/drRjDrTi7DRC
     * @param replace
     * @return
     */
    public static String generateSQL(String qmCodeSn,String qmCodeNo){
        //对qmCodeNo进行处理
        String[] qmCodeNoArray = qmCodeNo.split("/");
        qmCodeNo = qmCodeNoArray[4];//企迈二维码编号 eg：drRjDrTi7DRC
        //机构号
        String qm_urlZone = qmCodeNoArray[3];//企迈地址区段 eg：ujdh
        String qm_instno  = map.get(qm_urlZone);//企迈机构号 eg：zz1
        //生成指定的时间格式
        String time = DateUtil.formactDate("yyyy-MM-dd HH:mm:ss");
        String sql = "INSERT INTO `b_qmcode_terminal` (qmcodesn,qmcodeno,createtime,qm_instno,qm_urlzone) VALUES ('"+
                qmCodeSn+"' ,'"+qmCodeNo+"' ,'"+time+"' ,'"+qm_instno+"' ,'"+qm_urlZone+"');";
        return sql;
    }
}