package cn.ccp;

import cn.ccp.util.ExcelUtils;
import cn.ccp.util.TxTData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;

import java.io.IOException;

/**
 * Hello world!
 */
public class FunctionApplication {
    public static void main(String[] args) throws IOException {
        String xlsPath = "C:\\Users\\CCPENG\\Desktop\\印刷聚合二维码 (财来聚收银2000个付款码_20200328).xls";//xls文件路径
        String sheetName = "score";//xls表名
        String txtPath = "C:\\Users\\CCPENG\\Desktop\\商家码sql.txt";//保存txt文件路径
        String unquieTxt = "C:\\Users\\CCPENG\\Desktop\\检查商家码是否重复sql.txt";
        //生成txt对象
        TxTData txt = new TxTData(txtPath);
        TxTData txt1 = new TxTData(unquieTxt);
        try {
            HSSFSheet sheet = ExcelUtils.readXls(xlsPath, sheetName);
            //处理数据
            int rows = sheet.getLastRowNum();//表的行数
            String roll1 = "";
            String roll2 = "";
            String sql = "";
            //打开文件流
            txt.open();
            txt1.open();
            for (int i = 1; i <= rows; i++) {
                HSSFRow row = sheet.getRow(i);// 行数
                //读取数据前设置单元格类型
                row.getCell(0).setCellType(CellType.STRING);
                roll1 = row.getCell(0).getStringCellValue();
                roll2 = row.getCell(1).getStringCellValue();
                //得到sql
                sql = ExcelUtils.generateSQL(roll1, roll2);
                //将sql写入txt文件中
                txt.wirteTxt(sql+"\r\n");
            }
            String uniquesql = ExcelUtils.unquieSql.toString();
            uniquesql = uniquesql.substring(0,uniquesql.length()-1).concat(")");
            txt1.wirteTxt(uniquesql);
            System.out.println("一共处理"+rows+"条数据！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭文件流
            txt.cloese();
            txt1.cloese();
        }
    }
}
