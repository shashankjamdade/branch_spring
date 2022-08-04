package com.fsfb.branchregister.branchregister.service;

import com.fsfb.branchregister.branchregister.model.*;
import com.fsfb.branchregister.branchregister.model.req.BranchKeyMasterReq;
import com.fsfb.branchregister.branchregister.model.req.RequestCommonReq;
import com.fsfb.branchregister.branchregister.model.req.RequestDetailRes;
import com.fsfb.branchregister.branchregister.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fsfb.branchregister.branchregister.model.RequestStatus.*;

@Service
public class RequestService {

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

    /**
     * Add/update request with status
     */
    public ResponseEntity<CommonResponse> addOrUpdateRequest(RequestCommonReq requestCommonReq) {
        try {
            System.out.println("-----------" + requestCommonReq.toString());
            switch (requestCommonReq.getRequestStatus()) {
                case "REQ_RAISED": {
                    if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Unexpected value: " + requestCommonReq.getReqOwnerId());
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Unexpected value: " + requestCommonReq.getRecieverId());
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Unexpected value: " + requestCommonReq.getOwnerReason());
                    } else {
                        //save
                        try {
                            RequestDetail requestDetail = new RequestDetail(requestCommonReq.getBranchId(), requestCommonReq.getBranchName(), requestCommonReq.getKeysetId(), requestCommonReq.getReqOwnerId(),
                                    requestCommonReq.getOwnerReason(), requestCommonReq.getOwnerComment(), requestCommonReq.getRecieverId(), REQ_RAISED.name());

                            RequestDetail req = requestDetailRepository.save(requestDetail);
                            if (requestCommonReq.getExceptionMsg() != null && !requestCommonReq.getExceptionMsg().isEmpty()) {
                                //Exception raised
                                RequestException requestException = new RequestException(req.getId(), requestCommonReq.getExceptionMsg(), ReqExceptionType.ALERT.name());
                                requestExceptionRepository.save(requestException);
                            }
                            RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), REQ_RAISED.name());
                            requestHistoryRepository.save(requestHistory);
                            return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request raised successfully", req.getId()), HttpStatus.OK);
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                }
                case "BOM_APPROVED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else {
                        //save
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (!requestDetail.getRequestStatus().equals(REQ_RAISED.name())) {
                                    throw new UnexpectedError("Invalid req status");
                                } else {
                                    //REq = REQ_RAISED
                                    //Check for ZH flow - already same user having any key??
                                    List<BranchKeyMaster> branchKeyMasterList = branchKeyMasterRepository.findByuserid(requestDetail.getRecieverId());
                                    if (branchKeyMasterList.size() > 0) {
                                        RequestException requestException = new RequestException(requestDetail.getId(), "This user has aquired already aquired another keyset " + branchKeyMasterList.get(branchKeyMasterList.size() - 1).getKey_name(), ReqExceptionType.ALERT.name());
                                        requestExceptionRepository.save(requestException);
                                    }
                                    requestDetail.setIsZhRequired(branchKeyMasterList.size() > 0 ? 1 : 0);
                                    requestDetail.setBomId(requestCommonReq.getBomId());
                                    requestDetail.setBomComment(requestCommonReq.getBomComment());
                                    requestDetail.setRequestStatus(BOM_APPROVED.name());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), BOM_APPROVED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request approved successfully", req.getId()), HttpStatus.OK);
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                case "BOM_REJECTED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else {
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (!requestDetail.getRequestStatus().equals(REQ_RAISED.name())) {
                                    throw new UnexpectedError("Invalid req status");
                                } else {
                                    //REq = REQ_RAISED
                                    requestDetail.setBomId(requestCommonReq.getBomId());
                                    requestDetail.setBomComment(requestCommonReq.getBomComment());
                                    requestDetail.setRequestStatus(BOM_REJECTED.name());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), BOM_REJECTED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request rejected successfully", req.getId()), HttpStatus.OK);
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                case "ZH_APPROVED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else {
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (!requestDetail.getRequestStatus().equals(BOM_APPROVED.name()) && requestDetail.getIsZhRequired() != 1) {
                                    throw new UnexpectedError("Invalid req status");
                                } else {
                                    //REq = REQ_RAISED
                                    //Check for ZH flow - already same user having any key??
                                    requestDetail.setZhUserId(requestCommonReq.getZhUserId());
                                    requestDetail.setZhUserComment(requestCommonReq.getZhUserComment());
                                    requestDetail.setModifiedDate(LocalDateTime.now());
                                    requestDetail.setRequestStatus(ZH_APPROVED.name());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), ZH_APPROVED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request approved successfully", req.getId()), HttpStatus.OK);
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                case "ZH_REJECTED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else {
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (!requestDetail.getRequestStatus().equals(BOM_APPROVED.name()) && requestDetail.getIsZhRequired() != 1) {
                                    throw new UnexpectedError("Invalid req status");
                                } else {
                                    //REq = REQ_RAISED
                                    //Check for ZH flow - already same user having any key??
                                    requestDetail.setZhUserId(requestCommonReq.getZhUserId());
                                    requestDetail.setZhUserComment(requestCommonReq.getZhUserComment());
                                    requestDetail.setModifiedDate(LocalDateTime.now());
                                    requestDetail.setRequestStatus(ZH_REJECTED.name());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), ZH_REJECTED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request approved successfully", req.getId()), HttpStatus.OK);
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                case "RECIEVER_ACCEPTED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else if (requestCommonReq.getRecieverId() != requestCommonReq.getReqByid()) {
                        throw new UnexpectedError("No access to approve it");
                    } else {
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (requestDetail.getIsZhRequired() == 1 && !requestDetail.getRequestStatus().equals(ZH_APPROVED.name())) {
                                    throw new UnexpectedError("You didn't got approval from ZH user yet!");
                                }
                                if ((requestDetail.getRequestStatus().equals(BOM_APPROVED.name()) || requestDetail.getRequestStatus().equals(ZH_APPROVED.name()))) {
                                    requestDetail.setModifiedDate(LocalDateTime.now());
                                    requestDetail.setRequestStatus(RECIEVER_ACCEPTED.name());
                                    requestDetail.setRecieverComment(requestCommonReq.getRecieverComment());
                                    requestDetail.setRecieverComment(requestCommonReq.getRecieverComment());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    //Change key owner in branchmaster by reciever id
                                    BranchKeyMaster branchKeyMaster = branchKeyMasterRepository.findById(requestDetail.getKeysetId()).get();
                                    branchKeyMaster.setUserid(req.getRecieverId());
                                    branchKeyMaster.setUsername(requestCommonReq.getReqByName());
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), RECIEVER_ACCEPTED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request accepted successfully", req.getId()), HttpStatus.OK);
                                } else {
                                    throw new UnexpectedError("Invalid req status");
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                case "RECIEVER_REJECTED": {
                    if (requestCommonReq.getReqId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid req id");
                    } else if (requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner id");
                    } else if (requestCommonReq.getRecieverId().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid Reciever id");
                    } else if (requestCommonReq.getOwnerReason().toString().isEmpty()) {
                        throw new UnexpectedError("Invalid owner reason");
                    } else {
                        try {
                            RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                            if (requestDetail != null) {
                                if (!requestDetail.getRequestStatus().equals(BOM_APPROVED.name()) && requestDetail.getIsZhRequired() != 1) {
                                    throw new UnexpectedError("Invalid req status");
                                } else {
                                    //REq = REQ_RAISED
                                    //Check for ZH flow - already same user having any key??
                                    requestDetail.setZhUserId(requestCommonReq.getZhUserId());
                                    requestDetail.setZhUserComment(requestCommonReq.getZhUserComment());
                                    requestDetail.setModifiedDate(LocalDateTime.now());
                                    requestDetail.setRequestStatus(ZH_REJECTED.name());
                                    RequestDetail req = requestDetailRepository.save(requestDetail);
                                    RequestHistory requestHistory = new RequestHistory(req.getId(), requestCommonReq.getReqByid(), requestCommonReq.getBranchId(), requestCommonReq.getReqByName(), ZH_REJECTED.name());
                                    requestHistoryRepository.save(requestHistory);
                                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request approved successfully", req.getId()), HttpStatus.OK);
                                }
                            }
                        } catch (Exception e) {
                            throw new UnexpectedError(e.getLocalizedMessage());
                        }
                    }
                    break;
                }
                default: {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Something went wrong"), HttpStatus.OK);
                }
            }
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Nothing to do..."), HttpStatus.OK);
        } catch (
                Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    /**
     * Fetch requestdetail by ID
     */
    public ResponseEntity<CommonResponse> getReqDetailById(RequestCommonReq requestCommonReq) {
        try {
            if (requestCommonReq.getReqId() != null && !requestCommonReq.getReqId().toString().isEmpty()) {
                RequestDetail requestDetail = requestDetailRepository.findById(requestCommonReq.getReqId()).get();
                if (requestDetail != null) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request fetched successfully", requestDetail), HttpStatus.OK);
                } else {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request not found"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request id missing"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    /**
     * Fetch requestdetail by owner Id
     */
    public ResponseEntity<CommonResponse> findReqByOwnerId(RequestCommonReq requestCommonReq) {
        try {
            if (requestCommonReq.getReqOwnerId() != null && !requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                List<RequestDetail> requestDetailList = new ArrayList<>();
                if (requestCommonReq.getReqOwnerId() == null || !requestCommonReq.getReqOwnerId().toString().isEmpty()) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Owner id missing", requestDetailList), HttpStatus.OK);
                } else {
                    if (requestCommonReq.getReqId() != null && requestCommonReq.getReqFromDate() != null && requestCommonReq.getReqToDate() != null) {
                        requestDetailList = requestDetailRepository.findAllByCreatedDateBetweenByIdOwner(requestCommonReq.getReqId().toString(), requestCommonReq.getReqFromDate(), requestCommonReq.getReqToDate(), requestCommonReq.getReqOwnerId().toString());
                    } else if (requestCommonReq.getReqFromDate() != null && requestCommonReq.getReqToDate() != null) {
                        requestDetailList = requestDetailRepository.findAllByCreatedDateBetweenOwner(requestCommonReq.getReqFromDate(), requestCommonReq.getReqToDate(), requestCommonReq.getReqOwnerId().toString());
                    } else if (requestCommonReq.getReqOwnerId() != null) {
                        requestDetailList = requestDetailRepository.findByReqOwnerId(requestCommonReq.getReqOwnerId());
                    } else {
                        return new ResponseEntity<CommonResponse>(new CommonResponse(false, "BAD request", requestDetailList), HttpStatus.OK);
                    }
                }
                if (requestDetailList != null && requestDetailList.size() > 0) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request fetched successfully", requestDetailList), HttpStatus.OK);
                } else {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request not found"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request ownerid missing"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    /**
     * Fetch requestdetail by recieverId
     */
    public ResponseEntity<CommonResponse> findReqByRecieverId(RequestCommonReq requestCommonReq) {
        try {
            if (requestCommonReq.getRecieverId() != null && !requestCommonReq.getRecieverId().toString().isEmpty()) {
                List<RequestDetail> requestDetailList = new ArrayList<>();
                if (requestCommonReq.getRecieverId() == null || !requestCommonReq.getRecieverId().toString().isEmpty()) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Reciever id missing", requestDetailList), HttpStatus.OK);
                } else {
                    if (requestCommonReq.getReqId() != null && requestCommonReq.getReqFromDate() != null && requestCommonReq.getReqToDate() != null) {
                        requestDetailList = requestDetailRepository.findAllByCreatedDateBetweenByIdReciever(requestCommonReq.getReqId().toString(), requestCommonReq.getReqFromDate(), requestCommonReq.getReqToDate(), requestCommonReq.getRecieverId().toString());
                    } else if (requestCommonReq.getReqFromDate() != null && requestCommonReq.getReqToDate() != null) {
                        requestDetailList = requestDetailRepository.findAllByCreatedDateBetweenReciever(requestCommonReq.getReqFromDate(), requestCommonReq.getReqToDate(), requestCommonReq.getRecieverId().toString());
                    } else if (requestCommonReq.getRecieverId() != null) {
                        requestDetailList = requestDetailRepository.findByRecieverId(requestCommonReq.getRecieverId());
                    } else {
                        return new ResponseEntity<CommonResponse>(new CommonResponse(false, "BAD request", requestDetailList), HttpStatus.OK);
                    }
                }
                if (requestDetailList != null && requestDetailList.size() > 0) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request fetched successfully", requestDetailList), HttpStatus.OK);
                } else {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request not found"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request reciever id missing"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    /**
     * Fetch requestdetail by branchArraylist
     */
    public ResponseEntity<CommonResponse> findReqByBranchList(RequestCommonReq requestCommonReq) {
        try {
            if (requestCommonReq.getBranchlist() != null && requestCommonReq.getBranchlist().size() > 0) {
                List<RequestDetail> requestDetailList = requestDetailRepository.findBybranchIdIn(requestCommonReq.getBranchlist());
                if (requestDetailList != null && requestDetailList.size() > 0) {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(true, "Request fetched of branches successfully", requestDetailList), HttpStatus.OK);
                } else {
                    return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request not found"), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<CommonResponse>(new CommonResponse(false, "Request recieverid missing"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<CommonResponse>(new CommonResponse(false, e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

}
