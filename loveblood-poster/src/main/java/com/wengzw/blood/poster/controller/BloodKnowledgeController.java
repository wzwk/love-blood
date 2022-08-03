package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.poster.entity.BloodKnowledge;
import com.wengzw.blood.poster.service.poster.BloodKnowledgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 21:44
 */

@RestController
@RequestMapping(BloodKnowledgeController.PREFIX)
@Validated
@RequiredArgsConstructor
@Slf4j
public class BloodKnowledgeController {
    public static final String PREFIX = "/blood/knowledge";

    private final BloodKnowledgeService bloodKnowledgeService;

    /**
     * 根据条件获取献血知识库信息
     * @param bid 区别献血知识库的不同
     * @return
     */
    @GetMapping("/getBloodKnowledgeList/{bid}")
    @ResponseBody
    public ResponseResult getBloodKnowledgeList(@PathVariable int bid) {
        List<BloodKnowledge> bloodKnowledgeList = bloodKnowledgeService.getBloodKnowledgeList(bid);
        return new ResponseResult(RespStatusEnum.SUCCESS,bloodKnowledgeList);
    }

    /**
     * 根据id获取详情
     * @param id 主键
     * @return
     */
    @GetMapping("/getBloodKnowledgeById/{id}")
    @ResponseBody
    public ResponseResult getBloodKnowledgeById(@PathVariable int id) {
        BloodKnowledge bloodKnowledgeById = bloodKnowledgeService.getBloodKnowledgeById(id);
        return new ResponseResult(RespStatusEnum.SUCCESS,bloodKnowledgeById);
    }



}
