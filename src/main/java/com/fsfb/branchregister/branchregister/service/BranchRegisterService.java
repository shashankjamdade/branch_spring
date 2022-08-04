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
import java.util.HashMap;
import java.util.List;

@Service
public class BranchRegisterService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BranchKeyMasterRepository branchKeyMasterRepository;

    @Autowired
    private BranchKeyMasterMainRepository branchKeyMasterMainRepository;

    @Autowired
    private KeyMasterRepository keyMasterRepository;

    @Autowired
    private RequestDetailRepository requestDetailRepository;

    @Autowired
    private RequestExceptionRepository requestExceptionRepository;

    @Autowired
    private RequestHistoryRepository requestHistoryRepository;

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<CommonResponse> addUsers(List<User> users) {
        try {
            System.out.println("-----------" + users.toString());
            userRepository.saveAll(users);
            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "User saved successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    public ResponseEntity<CommonResponse> findKeysByBranchId(BranchKeyMasterReq branchId) {
        try {
//            List<BranchKeyMaster> branchKeyMasterList = branchKeyMasterRepository.findByBranchId(branchId.getBranchId());
            List<BranchKeyMaster> branchKeyMasterList = branchKeyMasterRepository.findAll();
            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Data fetched successfully", branchKeyMasterList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    public ResponseEntity<CommonResponse> findAllKeysByBranchIdNew(BranchKeyMasterReq branchId) {
        try {
//            List<BranchKeyMaster> branchKeyMasterList = branchKeyMasterRepository.findAllKeysByBranchIdNew();
            List<BranchKeyMaster> branchKeyMasterList = branchKeyMasterRepository.findAll();
            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Data fetched successfully", branchKeyMasterList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    public ResponseEntity<CommonResponse> addBranchKeyMaster(BranchKeyMasterReq req) {
        try {
            req.getBranchKeyMaster().setCreatedDate(LocalDateTime.now());
            req.getBranchKeyMaster().setModifiedDate(LocalDateTime.now());
            BranchKeyMaster branchKeyMasterObj = branchKeyMasterRepository.save(req.getBranchKeyMaster());
            List<KeyMaster> keyMasters = req.getKeyMasters();
            for (KeyMaster k : keyMasters) {
                k.setBranchkeymaster_id(branchKeyMasterObj.getBranchId());
                k.setCreatedDate(LocalDateTime.now());
                k.setModifiedDate(LocalDateTime.now());
            }
            List<KeyMaster> keyMasterObj = keyMasterRepository.saveAll(req.getKeyMasters());
            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Data saved successfully", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    public ResponseEntity<CommonResponse> findByBranchKeyMasterMainId(BranchKeyMasterReq branchId) {
        try {
            List<BranchKeyMasterMain> branchKeyMasterList = branchKeyMasterMainRepository.findAll();
            HashMap<String, BranchKeyMasterReq> hashmapBranchKeySetMain = new HashMap<>();

            for (BranchKeyMasterMain branchMasterObj : branchKeyMasterList) {
                ArrayList<KeyMaster> keylist = new ArrayList<>();
                String branchCodeKeySetId = branchMasterObj.getBranchCode().toString() + "#" + branchMasterObj.getKeySet().toString();
                if (!hashmapBranchKeySetMain.containsKey(branchCodeKeySetId)) {
                    //New
                    keylist.add(new KeyMaster(branchMasterObj.getKeySet(), branchMasterObj.getKeyType(), branchMasterObj.getKeyNumber(), 1));
                    BranchKeyMaster bkmObj = new BranchKeyMaster(branchMasterObj.getKeySet(), 1);
                    bkmObj.setCreatedDate(LocalDateTime.now());
                    bkmObj.setModifiedDate(LocalDateTime.now());
                    bkmObj.setUserid(branchMasterObj.getKeyHeldByUserId());
                    bkmObj.setUsername(branchMasterObj.getKeyHeldByUsername());
                    bkmObj.setBranchId(branchMasterObj.getBranchCode());
                    bkmObj.setKey_name(branchMasterObj.getKeySet());
                    BranchKeyMasterReq masterReq = new BranchKeyMasterReq();
                    masterReq.setBranchKeyMaster(bkmObj);
                    masterReq.setKeyMasters(keylist);
                    masterReq.setBranchId(branchMasterObj.getBranchCode());
                    hashmapBranchKeySetMain.put(branchCodeKeySetId, masterReq);
                } else {
                    //Existing
                    BranchKeyMasterReq reqObj = hashmapBranchKeySetMain.get(branchCodeKeySetId);
                    keylist = reqObj.getKeyMasters();
                    keylist.add(new KeyMaster(branchMasterObj.getKeySet(), branchMasterObj.getKeyType(), branchMasterObj.getKeyNumber(), 1));
                    reqObj.setKeyMasters(keylist);
                    reqObj.setBranchId(branchMasterObj.getBranchCode());
                    hashmapBranchKeySetMain.put(branchCodeKeySetId, reqObj);
                }
            }
            ArrayList<BranchKeyMasterReq> reqList = new ArrayList<>();
            for (BranchKeyMasterReq req : hashmapBranchKeySetMain.values()) {
                //Push BranchMaster table
                BranchKeyMaster branchKeyMasterObj = branchKeyMasterRepository.save(req.getBranchKeyMaster());
                //Push KeyMaster table
                ArrayList<KeyMaster> keyMasters = req.getKeyMasters();
                for (KeyMaster k : keyMasters) {
                    k.setBranchkeymaster_id(branchKeyMasterObj.getId());
                    k.setCreatedDate(LocalDateTime.now());
                    k.setModifiedDate(LocalDateTime.now());
                }
                System.out.println("############"+keyMasters.toString());
                req.setKeyMasters(keyMasters);
                keyMasterRepository.saveAll(req.getKeyMasters());
                req.setBranchKeyMaster(branchKeyMasterObj);
                req.setKeyMasters(keyMasters);
                reqList.add(req);
            }
            System.out.println("----------------"+reqList.toString());
            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Data fetched successfully", reqList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }


}
