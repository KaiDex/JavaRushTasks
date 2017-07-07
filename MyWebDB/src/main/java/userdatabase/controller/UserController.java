package userdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import userdatabase.model.User;
import userdatabase.service.UserService;

@Controller
public class UserController {

    private UserService userService;
    protected PagedListHolder<User> articles;
    private boolean isInitedList = false;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private void initAllUsers() {
        if (!isInitedList) {
            isInitedList = true;
            articles = new PagedListHolder<User>();
            articles.setSource(userService.listUsers());
            articles.setPageSize(5);
        }
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model){
        initAllUsers();
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.articles.getPageList());

        return "users";
    }

    @RequestMapping("nextPage")
    public String getNextPage() {

        articles.nextPage();

        return "redirect:users";
    }


    @RequestMapping("prevPage")
    public String getPrevPage()  {

        articles.previousPage();

        return "redirect:users";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(@RequestParam("name") String name, Model model){

        model.addAttribute("searchForName", name);

        return "redirect:searchusers";
    }

    @RequestMapping(value = "searchusers", method = RequestMethod.GET)
    public String searchUsersByName(@ModelAttribute("searchForName") String name, Model model){

        articles = new PagedListHolder<User>();
        articles.setSource(this.userService.getUsersByName(name));
        articles.setPageSize(5);

        model.addAttribute("user", new User());
        model.addAttribute("listUsers", this.userService.getUsersByName(name));

        return "searchusers";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user){

        if(user.getId() == 0){
            this.userService.addUser(user);
        }else {
            this.userService.updateUser(user);
        }

        isInitedList = false;

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){

        this.userService.removeUser(id);
        isInitedList = false;

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){

        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("listUsers", this.userService.listUsers());

        isInitedList = false;

        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.getUserById(id));

        return "userdata";
    }



}
