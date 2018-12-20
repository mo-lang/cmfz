package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import com.baizhi.cmfz.util.CreateValidateCode;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CreateValidateCode createValidateCode;

    @RequestMapping("/getCode")
    public void getCode(HttpSession s, HttpServletResponse res) throws IOException {
        String code = createValidateCode.getCode();
        s.setAttribute("valCode", code);
        createValidateCode.write(res.getOutputStream());
    }

    @RequestMapping("/login")
    public String login(HttpSession s, Model m, Admin admin) {
        try {
            adminService.login(admin);
            s.setAttribute("admin", admin);
            return "redirect:/main/main.jsp";
        } catch (Exception e) {
            m.addAttribute("message", e.getMessage());
            return "login";
        }
    }

    @RequestMapping("/valCode")
    @ResponseBody
    public String valCode(HttpSession s, String code) {
        String valCode = (String) s.getAttribute("valCode");
        if (valCode.equalsIgnoreCase(code)) {
            return "ok";
        } else {
            return "验证码错误";
        }

    }


    @RequestMapping("/exit")
    public String exit(HttpSession s) {
        s.invalidate();
        return "redirect:/login.jsp";
    }
}
