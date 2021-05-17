package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.tipstorage.TipsStorageResDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageSaveDto;
import com.gsm.chwijuntime.dto.tipstorage.TipsStorageUpdateDto;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.tipstorage.TipsStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"12. 꿀팁"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class TipsStorageController {

    private final TipsStorageService tipsStorageService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 저장", notes = "사용자가 꿀팁을 저장한다.")
    @PostMapping("/tips-storage")
    public CommonResult savetip(@Valid @RequestBody TipsStorageSaveDto tipsStorageSaveDto){
        tipsStorageService.saveTipsStorage(tipsStorageSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 전체 조회", notes = "사용자가 꿀팁을 전체 조회한다.")
    @GetMapping("/tips-storage")
    public ListResult<TipsStorageResDto> findAll(){
        List<TipsStorageResDto> all = tipsStorageService.findAll();
        return responseService.getListResult(all);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 단일 조회", notes = "사용자가 꿀팁을 단일 조회한다.")
    @GetMapping("/tips-storage/{tipidx}")
    public SingleResult<TipsStorageResDto> findByIdx(@PathVariable Long tipidx){
        TipsStorageResDto byIdx = tipsStorageService.findByIdx(tipidx);
        return responseService.getSingleResult(byIdx);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 수정", notes = "사용자가 꿀팁을 수정한다.")
    @PutMapping("/tips-storage/{tipidx}")
    public CommonResult updateByIdx(@PathVariable Long tipidx, @Valid @RequestBody TipsStorageUpdateDto tipsStorageUpdateDto){
        tipsStorageService.updateTipsStorage(tipidx, tipsStorageUpdateDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 삭제", notes = "사용자가 꿀팁을 삭제한다.")
    @DeleteMapping("/tips-storage/{tipidx}")
    public CommonResult deleteByIdx(@PathVariable Long tipidx){
        tipsStorageService.deleteTipsStorage(tipidx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "꿀팁 키워드 검색(회사 이름)", notes = "사용자가 키워드를 검색한다.")
    @DeleteMapping("/tips-storage-keyword}")
    public ListResult<TipsStorageResDto> findByKeword(@RequestParam String keyword){
        List<TipsStorageResDto> byWorkCompanyName = tipsStorageService.findByWorkCompanyName(keyword);
        return responseService.getListResult(byWorkCompanyName);
    }
}
