package festival.server.univ.univ.controller;

import festival.server.univ.univ.service.UnivMngService;
import festival.server.univ.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UnivController {

    final UnivMngService univMngService;

    @GetMapping("/univ/upload")
    public ResponseEntity<Integer> upload() throws Exception {
        // 로컬에 저장한 다음
        // 해당파일을 가져와 DB에 저장
        String excelFilePath = "C:/files/2023_2_univ.xlsx"; // 6번줄부터
        List<List<Object>> dataList = ExcelUtil.readExcel(ExcelUtil.readExcelFile(excelFilePath));

        univMngService.uploadUnivInfo(dataList);

        return ResponseEntity.ok(1);
    }
}
