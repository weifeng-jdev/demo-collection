package com.amano.mybatisdemo.controller;

import com.amano.common.entity.ResponseEntity;
import com.amano.common.enums.CodeEnums;
import com.amano.mybatisdemo.entity.Corp;
import com.amano.mybatisdemo.entity.dto.CorpPageQuery;
import com.amano.mybatisdemo.service.ICorpService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @className: CorpController
 * @package com.amano.mybatisdemo.controller
 * @description: 租户相关接口，该接口下演示mybatis-plus相关用法和拓展
 * @author: amano
 * @date: 2023/6/28
 **/
@RestController
@RequestMapping("/api/corp")
@RequiredArgsConstructor
public class CorpController {
    private final ICorpService iCorpService;

    @GetMapping
    public ResponseEntity<List<Corp>> listAllCorp() {
        return ResponseEntity.ok(iCorpService.list());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Corp>> listCorpPage(CorpPageQuery query) {
        return ResponseEntity.ok(iCorpService.listCorpPage(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Corp> getCorpDetailById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iCorpService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Corp> createCorp(@RequestBody Corp corp) {
        iCorpService.save(corp);
        return ResponseEntity.ok(corp);
    }

    @PutMapping
    public ResponseEntity<Corp> updateCorp(@RequestBody Corp corp) {
        if (Objects.isNull(iCorpService.getById(corp.getId()))){
            return ResponseEntity.exception(CodeEnums.SUCCESS.getCode(), "租户不存在");
        }
        boolean res = iCorpService.updateById(corp);
        if (!res) {
            return ResponseEntity.exception(CodeEnums.SUCCESS.getCode(), "删除失败");

        }
        return ResponseEntity.ok();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCropById(@RequestBody Corp corp) {
        if (Objects.isNull(iCorpService.getById(corp.getId()))){
            return ResponseEntity.exception(CodeEnums.SUCCESS.getCode(), "租户不存在");
        }
        boolean res = iCorpService.removeById(corp.getId());
        if (!res) {
            return ResponseEntity.exception(CodeEnums.SUCCESS.getCode(), "删除失败");

        }
        return ResponseEntity.ok();
    }
}
