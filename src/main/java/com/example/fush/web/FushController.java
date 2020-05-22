package com.example.fush.web;

import com.example.fush.dto.FushDto;
import com.example.fush.service.FushService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/fush")
public class FushController {

    private FushService fushService;

    public FushController(FushService fushService) {
        this.fushService = fushService;
    }

    @GetMapping("/home")
    @ResponseBody
    public String home(final Model model) {
        model.addAttribute("fushDto", new FushDto());
        return "home";
    }

    @GetMapping("/get-one")
    @ResponseBody
    public String getOne(final FushDto fushDto, final Model responseModel) {
        responseModel.addAttribute("fushName", fushService.findOne(fushDto.getId()));

        return "get-one";
    }
}
