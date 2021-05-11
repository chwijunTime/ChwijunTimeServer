package com.gsm.chwijuntime.controller.release;

import com.gsm.chwijuntime.dto.tag.TagSaveDto;
import com.gsm.chwijuntime.model.Tag;
import com.gsm.chwijuntime.model.response.CommonResult;
import com.gsm.chwijuntime.model.response.ListResult;
import com.gsm.chwijuntime.model.response.ResponseService;
import com.gsm.chwijuntime.model.response.SingleResult;
import com.gsm.chwijuntime.service.tag.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = {"7. 태그"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
@CrossOrigin("http://localhost:3000")
@Slf4j
public class TagController {

    private final TagService tagService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "태그 저장", notes = "관리자가 태그를 저장한다.")
    @PostMapping("/tag")
    public CommonResult tagSave(@Valid @RequestBody TagSaveDto tagSaveDto){
        tagService.insertTag(tagSaveDto);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "태그 단일 조회", notes = "관리자가 태그를 단일 조회한다.")
    @GetMapping("/tag/{tagIdx}")
    public SingleResult<Tag> findByTagIdx(@PathVariable Long tagIdx){
        return responseService.getSingleResult(tagService.findByTagIdxOne(tagIdx));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "태그 전체 조회", notes = "관리자가 태그를 전체 조회한다.")
    @GetMapping("/tag")
    public ListResult<Tag> findAll(){
        List<Tag> tags = tagService.findAll();
        return responseService.getListResult(tags);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "태그 삭제", notes = "관리자가 태그를 전체 삭제한다.")
    @DeleteMapping("/tag/{tagIdx}")
    public CommonResult deleteByTagIdx(@PathVariable Long tagIdx){
        tagService.deleteTag(tagIdx);
        return responseService.getSuccessResult();
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ResponseBody
    @ApiOperation(value = "태그 이름 수정", notes = "관리자가 태그의 이름을 수정한다.")
    @PutMapping("/tag/{tagIdx}")
    public SingleResult<Tag> updateByTagIdx(@PathVariable Long tagIdx, @Valid @RequestBody TagSaveDto tagSaveDto){
        Tag tag = tagService.updateTag(tagIdx, tagSaveDto.getTagName());
        return responseService.getSingleResult(tag);
    }
}
