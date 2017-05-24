package ca.fixhold.controller;

import ca.fixhold.form.UserProfileForm;
import ca.fixhold.model.Role;
import ca.fixhold.model.User;
import ca.fixhold.repository.RoleRepository;
import ca.fixhold.service.SecurityService;
import ca.fixhold.service.UserService;
import ca.fixhold.validator.UserProfileFormValidator;
import ca.fixhold.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    private final RoleRepository roleRepository;

    private final UserProfileFormValidator userProfileFormValidator;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, RoleRepository roleRepository, UserProfileFormValidator userProfileFormValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.roleRepository = roleRepository;
        this.userProfileFormValidator = userProfileFormValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Role userRole = roleRepository.findByName("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        user.setActive(true);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());

        return "redirect:/profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute User user, Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = {"/profile/", "/profile"}, method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String editProfile(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("userProfileForm", user);
        return "editUserForm";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String submitEditProfile(@ModelAttribute("userProfileForm") UserProfileForm userProfileForm, BindingResult result, Principal principal) {
        String email = principal.getName();
        userProfileForm.setEmail(email);
        userProfileFormValidator.validate(userProfileForm, result);
        if (result.hasErrors()) {
            return "editUserForm";
        }
        User user = userService.findByEmail(email);
        user.setFirstName(userProfileForm.getFirstName());
        user.setLastName(userProfileForm.getLastName());
        userService.save(user);
        return "redirect:/profile";
    }

}
