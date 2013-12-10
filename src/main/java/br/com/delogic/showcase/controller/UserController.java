package br.com.delogic.showcase.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.showcase.entity.User;
import br.com.delogic.showcase.entity.enums.Sexo;
import br.com.delogic.showcase.repository.UserRepository;

@Controller
@RequestMapping(value = UserController.PATH)
public class UserController {

    static final String    PATH       = "/users";

    private UserRepository repository = UserRepository.get();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("users", "users", repository.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute User model) {
        return getBeanForm(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView read(@PathVariable Long id) {
        return getBeanForm(repository.find(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute User model,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getBeanForm(model);
        }
        repository.save(model);
        return new ModelAndView("redirect:" + PATH);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.POST,
                    RequestMethod.PUT })
    public ModelAndView update(@Valid @ModelAttribute("id") User model,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getBeanForm(model);
        }
        repository.save(model);
        return new ModelAndView("redirect:" + PATH);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    public String delete(@PathVariable Long id) {
        repository.remove(id);
        return "redirect:" + PATH;
    }

    public ModelAndView getBeanForm(User model) {
        return new ModelAndView("user", "user", model).addObject("generosSexo",
            Sexo.values());
    }

}