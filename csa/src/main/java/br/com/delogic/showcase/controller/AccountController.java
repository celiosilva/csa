package br.com.delogic.showcase.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.showcase.entity.Account;
import br.com.delogic.showcase.repository.AccountRepository;
import br.com.delogic.showcase.repository.AccountTypeRepository;

@Controller
@RequestMapping(value = AccountController.ACCOUNT_PATH)
public class AccountController {

    public static final String ACCOUNT_PATH = "/accounts";

    private AccountRepository  repository   = AccountRepository.get();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("accounts", "accounts", repository.findAll());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView createForm() {
        return getBeanForm(new Account());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView read(@PathVariable Long id) {
        return getBeanForm(repository.find(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ModelAndView create(@Valid Account account,
        BindingResult bindingResult) {
        if (account.getUser() == null || account.getUser().getId() == null) {
            bindingResult.addError(new FieldError("acccount", "user.nome", "Cliente não pode ser nulo ou vazio"));
        }
        if (bindingResult.hasErrors()) {
            return getBeanForm(account);
        }
        repository.save(account);
        return new ModelAndView("redirect:" + ACCOUNT_PATH);
    }

    @RequestMapping(value = "/{model}", method = { RequestMethod.POST,
                    RequestMethod.PUT })
    public ModelAndView update(@Valid @ModelAttribute("model") Account account,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return getBeanForm(account);
        }
        repository.save(account);
        return new ModelAndView("redirect:" + ACCOUNT_PATH);
    }

    @RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
    public String delete(@PathVariable Long id) {
        repository.remove(id);
        return "redirect:" + ACCOUNT_PATH;
    }

    public ModelAndView getBeanForm(Account account) {
        return new ModelAndView("account")
            .addObject("account", account)
            .addObject("accountTypes", AccountTypeRepository.get().findAll());
    }

}