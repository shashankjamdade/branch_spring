package com.fsfb.branchregister.branchregister.controller;

import com.fsfb.branchregister.branchregister.Utils.Constants;
import com.fsfb.branchregister.branchregister.model.req.BranchKeyMasterReq;
import com.fsfb.branchregister.branchregister.model.CommonResponse;
import com.fsfb.branchregister.branchregister.model.User;
import com.fsfb.branchregister.branchregister.model.req.RequestCommonReq;
import com.fsfb.branchregister.branchregister.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BranchRegisterController {

    @Autowired
    private BranchRegisterService branchRegisterService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private BranchExcelDataService branchExcelDataService;

    @PostMapping("/addUser")
    public ResponseEntity<CommonResponse> addUsers(@Valid @RequestBody List<User> body) {
        return branchRegisterService.addUsers(body);
    }

    @PostMapping("/getBranchKeyMaster")
    public ResponseEntity<CommonResponse> findKeysByBranchId(@Valid @RequestBody BranchKeyMasterReq branchId) {
        return branchRegisterService.findAllKeysByBranchIdNew(branchId);
    }

    @PostMapping("/saveBranchMaster")
    public ResponseEntity<CommonResponse> addBranchKeyMaster(@Valid @RequestBody BranchKeyMasterReq branchId) {
        return branchRegisterService.addBranchKeyMaster(branchId);
    }

    @PostMapping("/getSortedData")
    public ResponseEntity<CommonResponse> fillDataFromBranchMasterMainTable(@Valid @RequestBody BranchKeyMasterReq branchId) {
        return branchRegisterService.findByBranchKeyMasterMainId(branchId);
    }

    @PostMapping(path = "/import_to_db")
    public void importToDbfun(@RequestPart(required = true) List<MultipartFile> files) {
        branchExcelDataService.importToDb(files);
    }

    /**
     * REQUEST APIS
     */
    @PostMapping("/getRequestDetailByIds")
    public ResponseEntity<CommonResponse> getRequestDetailById(@Valid @RequestBody RequestCommonReq requestCommonReq) {
        ResponseEntity<CommonResponse> res = requestService.getReqDetailById(requestCommonReq);
        System.out.println("####"+res.getBody().toString());
        return res;
    }

    @PostMapping("/addOrUpdateReqStatus")
    public ResponseEntity<CommonResponse> updateReqStatus(@Valid @RequestBody RequestCommonReq requestCommonReq) {
        return requestService.addOrUpdateRequest(requestCommonReq);
    }

    @PostMapping("/getRequestByOwnerId")
    public ResponseEntity<CommonResponse> getRequestByOwnerId(@Valid @RequestBody RequestCommonReq requestCommonReq) {
        return requestService.findReqByOwnerId(requestCommonReq);
    }

    @PostMapping("/getRequestByRecieverId")
    public ResponseEntity<CommonResponse> getRequestByRecieverId(@Valid @RequestBody RequestCommonReq requestCommonReq) {
        return requestService.findReqByRecieverId(requestCommonReq);
    }

    @PostMapping("/getRequestByBranches")
    public ResponseEntity<CommonResponse> getRequestByBranches(@Valid @RequestBody RequestCommonReq requestCommonReq) {
        return requestService.findReqByBranchList(requestCommonReq);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UnexpectedError.class)
    public @ResponseBody
    CommonResponse handleRuntimeError(UnexpectedError ex) {
        CommonResponse runErrors = new CommonResponse(false, Constants.EXCEPTION_MSG);
        return runErrors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestError.class)
    public @ResponseBody
    CommonResponse handleBadReqError(BadRequestError ex) {
        CommonResponse runErrors = new CommonResponse(false, Constants.EXCEPTION_MSG);
        return runErrors;
    }

}
