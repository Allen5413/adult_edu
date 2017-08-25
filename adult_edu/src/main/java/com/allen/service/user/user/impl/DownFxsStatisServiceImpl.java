package com.allen.service.user.user.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.user.user.FindUserDao;
import com.allen.service.user.user.DownFxsStatisService;
import com.allen.util.excel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/8/25.
 */
@Service
public class DownFxsStatisServiceImpl implements DownFxsStatisService {

    @Autowired
    private FindUserDao findUserDao;

    @Override
    public String down(PageInfo pageInfo, Map<String, Object> params, Map<String, Boolean> sortMap, String fileName) throws Exception {
        pageInfo = findUserDao.findFxsStudentNumForByCenterIdPage(pageInfo, params, sortMap);
        return this.createExcel(pageInfo.getPageResults(), fileName);
    }

    private String createExcel(List data, String fileName)throws Exception{
        ExcelObject exo = new ExcelObject();
        ExcelSheet sheet = new ExcelSheet();
        exo.addExcelSheet(sheet);
        int index = 0;

        ExcelRow runRow = new ExcelRow(index);
        ExcelCell runCell_1 = new ExcelCell(index,(byte)0, "招生分销商",false,"",30);
        ExcelCell runCell_2 = new ExcelCell(index,(byte)1, "电话",false,"",30);
        ExcelCell runCell_3 = new ExcelCell(index,(byte)2, "高校",false,"",30);
        ExcelCell runCell_4 = new ExcelCell(index,(byte)3, "招生类型",false,"",30);
        ExcelCell runCell_5 = new ExcelCell(index,(byte)4, "层次",false,"",30);
        ExcelCell runCell_6 = new ExcelCell(index,(byte)5, "层次",false,"",30);
        ExcelCell runCell_7 = new ExcelCell(index,(byte)6, "批次",false,"",30);
        ExcelCell runCell_8 = new ExcelCell(index,(byte)7, "招生人数",false,"",30);

        runRow.addExcelCell(runCell_1);
        runRow.addExcelCell(runCell_2);
        runRow.addExcelCell(runCell_3);
        runRow.addExcelCell(runCell_4);
        runRow.addExcelCell(runCell_5);
        runRow.addExcelCell(runCell_6);
        runRow.addExcelCell(runCell_7);
        runRow.addExcelCell(runCell_8);
        sheet.addExcelRow(runRow);
        index ++;
        if(null != data && data.size() > 0){
            for(int i = 0; i < data.size(); i++){
                Map<String, Object> map = (Map<String, Object>) data.get(i);
                ExcelRow runRow_2 = new ExcelRow(index);
                ExcelCell runCell_14 = new ExcelCell(index,(byte)0, map.get("name").toString().substring(0,10),false,"",9);
                ExcelCell runCell_15 = new ExcelCell(index,(byte)1, map.get("phone").toString(),false,"",9);
                ExcelCell runCell_16 = new ExcelCell(index,(byte)2, map.get("scName").toString(),false,"", 9);
                ExcelCell runCell_17 = new ExcelCell(index,(byte)3, map.get("rtName").toString(),false,"",9);
                ExcelCell runCell_18 = new ExcelCell(index,(byte)4, map.get("lName").toString(),false,"",9);
                ExcelCell runCell_19 = new ExcelCell(index,(byte)5, map.get("spName").toString(),false,"",9);
                ExcelCell runCell_20 = new ExcelCell(index,(byte)6, map.get("year").toString()+"年"+("0".equals(map.get("term").toString()) ? "春季":"秋季"),false,"",9);
                ExcelCell runCell_21 = new ExcelCell(index,(byte)7, map.get("num").toString(),false,"",9);

                runRow_2.addExcelCell(runCell_14);
                runRow_2.addExcelCell(runCell_15);
                runRow_2.addExcelCell(runCell_16);
                runRow_2.addExcelCell(runCell_17);
                runRow_2.addExcelCell(runCell_18);
                runRow_2.addExcelCell(runCell_19);
                runRow_2.addExcelCell(runCell_20);
                runRow_2.addExcelCell(runCell_21);

                sheet.addExcelRow(runRow_2);

                index ++;
            }
        }
        ExcelRow endTitleRow = new ExcelRow(index);
        sheet.addExcelRow(endTitleRow);
        //设置列宽
        Map<Integer, Integer> columnWidthMap = new HashMap<Integer, Integer>();
        columnWidthMap.put(0, 5000);
        columnWidthMap.put(1, 8000);
        columnWidthMap.put(2, 11000);
        columnWidthMap.put(3, 5000);
        columnWidthMap.put(4, 5000);
        columnWidthMap.put(5, 5000);
        columnWidthMap.put(6, 5000);
        columnWidthMap.put(7, 5000);
        return ExcelUtil.writeExcelFile(exo, columnWidthMap, fileName);
    }
}
