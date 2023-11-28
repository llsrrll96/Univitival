package festival.server.univ.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel 조작 유틸리티 클래스
 * @author lisuo
 *
 */
public abstract class ExcelUtil {

    public static InputStream readExcelFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        return fileInputStream;
    }

    /**
     * Excel 읽기, 모든 불규칙한 Excel 파일을 지원합니다.
     * 외부 List는 모든 데이터 행을 나타내며, 내부 List는 각 행의 cell 단위 데이터를 나타냅니다.
     * 예를 들어 Excel의 세 번째 행 두 번째 셀의 데이터를 얻는 경우:
     * FileInputStream excelStream = new FileInputStream(path);
     * List<List<Object>> list = ExcelUtil.readExcel(excelStream);
     * System.out.println(list.get(2).get(1));// 세 번째 행 두 번째 열, 인덱스 행 위치는 2, 열의 인덱스 위치는 1
     * @param excelStream Excel 파일 스트림
     * @param sheetIndex Excel-Sheet의 인덱스
     * @return List<List<Object>>
     * @throws Exception
     */
    public static List<List<Object>> readExcel(InputStream excelStream,int sheetIndex)throws Exception {
        List<List<Object>> datas = new ArrayList<List<Object>>();
        Workbook workbook = WorkbookFactory.create(excelStream);
        // 첫 번째 sheet만 읽기
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            short cellNum = row.getLastCellNum();
            List<Object> item = new ArrayList<Object>(cellNum);
            for(int j=0;j<cellNum;j++){
                Cell cell = row.getCell(j);
                Object value = ExcelUtil.getCellValue(cell);
                item.add(value);
            }
            datas.add(item);
        }
        return datas;
    }

    /**
     * Excel 읽기, 모든 불규칙한 Excel 파일을 지원합니다. 기본적으로 첫 번째 sheet만 읽습니다.
     * 외부 List는 모든 데이터 행을 나타내며, 내부 List는 각 행의 cell 단위 데이터를 나타냅니다.
     * 예를 들어 Excel의 세 번째 행 두 번째 셀의 데이터를 얻는 경우:
     * FileInputStream excelStream = new FileInputStream(path);
     * List<List<Object>> list = ExcelUtil.readExcel(excelStream);
     * System.out.println(list.get(2).get(1));// 세 번째 행 두 번째 열, 인덱스 행 위치는 2, 열의 인덱스 위치는 1
     * @param excelStream Excel 파일 스트림
     * @return List<List<Object>>
     * @throws Exception
     */
    public static List<List<Object>> readExcel(InputStream excelStream)throws Exception {
        return readExcel(excelStream,0);
    }

    /**
     * Cell의 값을 설정합니다.
     *
     * @param cell
     * @param value
     */
    public static void setCellValue(Cell cell, Object value) {
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Number) {
                cell.setCellValue(Double.parseDouble(String.valueOf(value)));
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value);
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
            } else {
                cell.setCellValue(value.toString());
            }
        }
    }

    /**
     * Cell 값 가져오기
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        if (null != cell) {
            switch (cell.getCellType()) {
                // 공백
                case BLANK:
                    break;
                // Boolean
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                // 오류 형식
                case ERROR:
                    break;
                // 수식
                case FORMULA:
                    Workbook wb = cell.getSheet().getWorkbook();
                    CreationHelper crateHelper = wb.getCreationHelper();
                    FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
                    value = getCellValue(evaluator.evaluateInCell(cell));
                    break;
                // 숫자
                case NUMERIC:
                    // 날짜 형식 처리
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                        value = cell.getDateCellValue();
                    } else {
                        value = NumberToTextConverter.toText(cell.getNumericCellValue());
                    }
                    break;
                case STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                default:
                    value = null;
            }
        }
        return value;
    }
}

