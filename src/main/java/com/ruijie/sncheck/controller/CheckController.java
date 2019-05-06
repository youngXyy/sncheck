package com.ruijie.sncheck.controller;

import com.ruijie.sncheck.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * CheckController
 *
 * @author {yuanwei}
 * @date 2019/5/6 22:07
 */
@RestController
public class CheckController {
    @Autowired
    private CheckService checkService;

    /**
     *
     */
}
