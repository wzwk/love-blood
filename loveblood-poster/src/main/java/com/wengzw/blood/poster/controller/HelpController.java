package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.service.poster.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengzw
 * @date 2022/8/7 10:34
 */

@RestController
@RequestMapping(HelpController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class HelpController {
    public static final String PREFIX = "/blood/help";

    private final HelpService helpService;

    /**
     * 加入关注信息
     *
     * @param posterId
     * @return
     */
    @PostMapping("insertConcernInfo/{posterId}")
    @ResponseBody
    public ResponseResult insertConcernInfo(@RequestHeader("token") String token, @PathVariable Integer posterId) {
        return helpService.insertConcernInfo(token, posterId);
    }

    /**
     * 获取我的关注信息
     *
     * @param token
     * @return
     */
    @GetMapping("getMyConcernInfo")
    @ResponseBody
    public ResponseResult getMyConcernInfo(@RequestHeader("token") String token) {
        return helpService.getMyConcernInfo(token);
    }

    /**
     * 取消关注
     *
     * @param id
     * @return
     */
    @DeleteMapping("deleteConcernInfo/{id}")
    @ResponseBody
    public ResponseResult deleteConcernInfo(@PathVariable Integer id) {
        return helpService.deleteConcernInfo(id);
    }


    /**
     * 加入帮助信息
     *
     * @param token
     * @param posterId
     * @return
     */
    @PostMapping("insertHelpInfo/{posterId}")
    @ResponseBody
    public ResponseResult insertHelpInfo(@RequestHeader("token") String token, @PathVariable Integer posterId) {
        return helpService.insertHelpInfo(token, posterId);
    }

    /**
     * 根据文章id查看谁帮助了我
     *
     * @param posterId
     * @return
     */
    @GetMapping("getHelpInfoByPosterId/{posterId}")
    @ResponseBody
    public ResponseResult getHelpInfoByPosterId(@PathVariable Integer posterId) {
        return helpService.getHelpInfoByPosterId(posterId);
    }

    /**
     * 查看我帮助了谁
     *
     * @return
     */
    @GetMapping("getMyHelp")
    @ResponseBody
    public ResponseResult getMyHelp(@RequestHeader("token") String token) {
        return helpService.getMyHelp(token);
    }

    /**
     * 删除帮助文章
     *
     * @param token
     * @param posterId
     * @return
     */
    @DeleteMapping("deleteMyHelp/{posterId}")
    @ResponseBody
    public ResponseResult deleteMyHelp(@RequestHeader("token") String token, @PathVariable Integer posterId) {
        return helpService.deleteMyHelp(token, posterId);
    }


}
