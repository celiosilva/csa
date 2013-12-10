package br.com.delogic.showcase.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.showcase.entity.AccountType;
import br.com.delogic.showcase.repository.AccountTypeRepository;

@Controller
@RequestMapping(value = AccountTypeController.PATH)
public class AccountTypeController {

    static final String           PATH       = "/accounttypes";

    private AccountTypeRepository repository = AccountTypeRepository.get();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("accountTypes", "accountTypes", repository.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView createForm(@ModelAttribute AccountType model) {
        return getBeanForm(model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView read(@PathVariable Long id) {
        return getBeanForm(repository.find(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute AccountType model,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getBeanForm(model);
        }
        repository.save(model);
        return new ModelAndView("redirect:" + PATH);
    }

    @RequestMapping(value = "/{model}", method = { RequestMethod.POST,
                    RequestMethod.PUT })
    public ModelAndView update(@Valid @ModelAttribute("model") AccountType model,
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

    public ModelAndView getBeanForm(AccountType model) {
        return new ModelAndView("accountType", "accountType", model);
    }

}