package com.fsfb.branchregister.branchregister.service;

import com.fsfb.branchregister.branchregister.model.*;
import com.fsfb.branchregister.branchregister.model.req.BranchKeyMasterReq;
import com.fsfb.branchregister.branchregister.repository.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchExcelDataServiceImpl implements BranchExcelDataService {

    @Autowired
    private BranchKeyMasterMainRepository branchKeyMasterMainRepository;


    @Override
    public void importToDb(List<MultipartFile> multipartfiles) {
        if (!multipartfiles.isEmpty()) {
            List<BranchKeyMasterMain> branchKeyMasterMainArrayList = new ArrayList<>();
            MultipartFile multipartfile = multipartfiles.get(0);
            try {
                XSSFWorkbook workBook = new XSSFWorkbook(multipartfile.getInputStream());

                XSSFSheet sheet = workBook.getSheetAt(0);
                // looping through each row
                for (int rowIndex = 0; rowIndex < getNumberOfNonEmptyCells(sheet, 0) - 1; rowIndex++) {
                    // current row
                    XSSFRow row = sheet.getRow(rowIndex);
                    // skip the first row because it is a header row
                    if (rowIndex == 0) {
                        continue;
                    }
                    Long branchCode = Long.parseLong(getValue(row.getCell(0)).toString());
                    String branchName = getValue(row.getCell(1)).toString();
                    String branchType = getValue(row.getCell(2)).toString();
                    String keySet = getValue(row.getCell(3)).toString();
                    String keyType = getValue(row.getCell(4)).toString();
                    String keyNumber = getValue(row.getCell(5)).toString();
                    Long keyHeldByUserId = Long.parseLong(getValue(row.getCell(6)).toString());
                    String keyHeldByUsername = getValue(row.getCell(7)).toString();
                    String empDesg = getValue(row.getCell(8)).toString();
                    String empDept = getValue(row.getCell(9)).toString();

                    System.out.println("---------------------" + branchCode);
                    BranchKeyMasterMain branchKeyMasterMain = new BranchKeyMasterMain(
                            branchCode, branchName, branchType, keySet, keyType, keyNumber, keyHeldByUserId, keyHeldByUsername,
                            empDesg, empDept
                    );
                    branchKeyMasterMainArrayList.add(branchKeyMasterMain);
                       /* Transaction transaction = Transaction.builder().senderId(senderId).receiverId(receiverId)
                                .initiatorId(initiatorId).bankCode(bankCode).serviceCode(serviceCode)
                                .trxnAmount(transactionAmount).feeAmount(feeAmount).build();
                        transactions.add(transaction);*/
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!branchKeyMasterMainArrayList.isEmpty() && branchKeyMasterMainArrayList.size() > 0) {
                // save to database
                branchKeyMasterMainRepository.saveAll(branchKeyMasterMainArrayList);
            }
        }
    }

    private Object getValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case ERROR:
                return cell.getErrorCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            case _NONE:
                return null;
            default:
                break;
        }
        return null;
    }

    public static int getNumberOfNonEmptyCells(XSSFSheet sheet, int columnIndex) {
        int numOfNonEmptyCells = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                XSSFCell cell = row.getCell(columnIndex);
                if (cell != null && cell.getCellType() != CellType.BLANK) {
                    numOfNonEmptyCells++;
                }
            }
        }
        return numOfNonEmptyCells;
    }

}
