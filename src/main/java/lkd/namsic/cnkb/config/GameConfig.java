package lkd.namsic.cnkb.config;

import lkd.namsic.cnkb.enums.object.ItemEnum;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class GameConfig {
    
    //MINE
    public final List<Long> MINE_ITEM_ID = Arrays.stream(ItemEnum.values())
        .map(ItemEnum::getValue)
        .filter(value -> value >= 1 && value <= 17)
        .collect(Collectors.toList());
    public final Map<Integer, List<Double>> MINE_PERCENT = new HashMap<>() {{
        try {
            FileInputStream inputStream = new FileInputStream(System.getenv("cnkb_random"));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Mining");
    
            for(int i = 1; i <= 9; i++) {
                this.put(i, new ArrayList<>());
            }
    
            double cell;
            XSSFRow row;
            for(int rowNum = 2; rowNum < 19; rowNum++) {
                row = sheet.getRow(rowNum);
        
                for(int columnNum = 7; columnNum < 16; columnNum++) {
                    cell = row.getCell(columnNum).getNumericCellValue();
                    this.get(columnNum - 6).add(cell);
                }
            }
    
            workbook.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }};

}