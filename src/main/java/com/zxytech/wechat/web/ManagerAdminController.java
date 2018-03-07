package com.zxytech.wechat.web;

import com.zxytech.wechat.config.SecurityConfig;
import com.zxytech.wechat.domain.account.Manager;
import com.zxytech.wechat.domain.account.ManagerRepository;
import com.zxytech.wechat.domain.account.UserRoleEnum;
import com.zxytech.wechat.utils.PagePredicateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

import static com.zxytech.wechat.utils.StringUtil.isBlank;

/**
 * @author xwxia
 * @date 2018/3/6 11:30
 */
@Controller
@RequestMapping("/admin/manager")
@RolesAllowed({SecurityConfig.ROLE_USER, SecurityConfig.ROLE_ADMIN})
public class ManagerAdminController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerAdminController.class);

    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ManagerAdminController(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = {"", "/"})
    public String managerPage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "role", required = false) UserRoleEnum role,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "limit", required = false) Integer limit,
                              Model model) {
        page = PagePredicateUtil.getCorrectPage(page);
        limit = PagePredicateUtil.getCorrectSize(limit);

        Sort sortByCreateAtDesc = new Sort(Sort.Direction.DESC, "updateAt");
        Pageable pageable = new PageRequest(page, limit, sortByCreateAtDesc);

        model.addAttribute("managers", managerRepository.findAll(pageable));

        model.addAttribute("name", name);
        model.addAttribute("username", username);
        model.addAttribute("roles", UserRoleEnum.values());
        model.addAttribute("role", role);

        return "pages/admin/manager_list";
    }

    @GetMapping("/add")
    public String addUserPage(Model model) {
        List<UserRoleEnum> roles = Arrays.asList(UserRoleEnum.values());
        model.addAttribute("roles", roles);
        return "pages/admin/create_manager";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name, @RequestParam("username") String username,
                          @RequestParam(value = "phone", required = false) String phone,
                          @RequestParam(value = "email", required = false) String email,
                          @RequestParam("password") String password,
                          @RequestParam(value = "role", required = false) UserRoleEnum role) {
        if (null == role) {
            role = UserRoleEnum.USER;
        }
        Manager manager = new Manager(name, phone, email, username, passwordEncoder.encode(password), role);
        managerRepository.save(manager);

        return "redirect:/admin/manager";
    }

    @GetMapping("/{staffId}")
    public String editUserPage(@PathVariable("staffId") String staffId, Authentication authentication, Model model) {
        Manager manager = managerRepository.findOne(staffId);
        model.addAttribute("manager", manager);
        List<UserRoleEnum> roles = Arrays.asList(UserRoleEnum.values());
        model.addAttribute("roles", roles);

        return "pages/admin/update_manager";
    }

    @PostMapping("/{staffId}")
    public String editUser(@PathVariable("staffId") String staffId,
                           @RequestParam("name") String name,
                           @RequestParam(value = "phone", required = false) @NotNull String phone,
                           @RequestParam(value = "email", required = false) String email,
                           @RequestParam(value = "username") @NotNull String username,
                           @RequestParam(value = "password", required = false) @NotNull String password,
                           @RequestParam(value = "role", required = false) UserRoleEnum role,
                           @RequestParam("active") @NotNull boolean active,
                           Authentication authentication) {
        Manager manager = managerRepository.findOne(staffId);

        /*
         * 当前用户可以修改自己，管理员可以修改全部
         */
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.getRole()));
        if (!isAdmin && (!authentication.getName().equals(manager.getUsername()))) {
            return "redirect:/admin/manager";
        }

        manager.setName(name);
        manager.setPhone(phone);
        manager.setEmail(email);
        if (!isBlank(password)) {
            manager.setPassword(passwordEncoder.encode(password));
        }
        manager.setActive(active);
        manager.setUpdateAt(System.currentTimeMillis());
        if (!isBlank(username)) {
            manager.setUsername(username);
        }
        if (null != role) {
            manager.setRole(role);
        }
        managerRepository.save(manager);

        return "redirect:/admin/manager";
    }

    @GetMapping("/del/{staffId}")
    public String delManager(@PathVariable("staffId") String staffId) {
        Manager manager = managerRepository.findOne(staffId);
        manager.setUpdateAt(System.currentTimeMillis());
        manager.setActive(false);
        managerRepository.save(manager);

        return "redirect:/admin/manager";
    }
}
