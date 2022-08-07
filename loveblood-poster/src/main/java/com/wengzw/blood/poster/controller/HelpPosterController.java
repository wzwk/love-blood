package com.wengzw.blood.poster.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.entity.HelpPoster;
import com.wengzw.blood.poster.service.poster.HelpPosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author wengzw
 * @date 2022/8/2 22:42
 */
@RestController
@RequestMapping(HelpPosterController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class HelpPosterController {
    public static final String PREFIX = "/blood/helpPoster";

    private final HelpPosterService helpPosterService;

    /**
     * 发布寻求帮助信息
     * @param token
     * @param helpPoster
     * @return
     */
    @PostMapping("/insertHelpPoster")
    @ResponseBody
    public ResponseResult insertHelpPoster(@RequestHeader("token") String token, @RequestBody HelpPoster helpPoster) {
        String id = JwtUtils.parse(token).get("id");
        helpPoster.setUserId(Integer.valueOf(id));
        helpPoster.setGmtCreate(new Date());
        return helpPosterService.insertHelpPoster(helpPoster);
    }

    /**
     * 分页获取文章信息
     * @param pageNum
     * @return
     */
    @GetMapping("getHelpPoster/{pageNum}")
    @ResponseBody
    public ResponseResult getHelpPoster(@PathVariable int pageNum) {
        PageHelper.startPage(pageNum, 10);
        PageInfo<HelpPoster> pageInfo = new PageInfo<>(helpPosterService.getHelpPoster());
        return new ResponseResult(RespStatusEnum.SUCCESS,pageInfo);
    }

    /**
     * 根据文章id获取文章详情
     *
     * @param posterId
     * @return
     */
    @GetMapping("getHelpPosterByPosterId/{posterId}")
    @ResponseBody
    public ResponseResult getHelpPosterByPosterId(@PathVariable Integer posterId) {
        return helpPosterService.getHelpPosterByPosterId(posterId);
    }

}
