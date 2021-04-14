package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.notice.NoticeSaveDto;
import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Notice;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.notice.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"5. 공지사항"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class NoticeController {

    private final ResponseService responseService;
    private final NoticeService noticeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "공지사항 저장", notes = "관리자가 공지사항를 저장한다.")
    @PostMapping("/notice")
    public CommonResult noticeSave(@RequestBody NoticeSaveDto noticeSaveDto){
        noticeService.save(noticeSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "공지사항 전체 조회", notes = "관리자와 사용자가 공지사항를 전체 조회한다.")
    @GetMapping("/notice")
    public ListResult<Notice> findAllNotice(){
        List<Notice> notices = noticeService.findAll();
        return responseService.getListResult(notices);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "공지사항 단일 조회", notes = "관리자와 사용자가 공지사항를 단일 조회한다.")
    @GetMapping("/notice/{noticeidx}")
    public SingleResult<Notice> findByNoticeIdx(@PathVariable Long noticeidx){
        Notice notice = noticeService.findById(noticeidx);
        return responseService.getSingleResult(notice);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "공지사항 삭제", notes = "관리자가 공지사항을 삭제한다.")
    @DeleteMapping("/notice/{noticeidx}")
    public CommonResult delelteByIdx(@PathVariable Long noticeidx){
        noticeService.deleteById(noticeidx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "공지사항 업데이트", notes = "관리자가 공지사항을 업데이트 한다.")
    @PutMapping("/notice/{noticeidx}")
    public CommonResult updateByIdx(@PathVariable Long noticeidx, @RequestBody NoticeSaveDto noticeSaveDto){
        noticeService.updateId(noticeidx, noticeSaveDto);
        return responseService.getSuccessResult();
    }
}
