package br.com.delogic.showcase.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.delogic.csa.controller.bean.LabelValueModel;
import br.com.delogic.showcase.entity.CamposEntrada;
import br.com.delogic.showcase.entity.enums.UnidadeFederativa;

@Controller
@RequestMapping("/showcase")
public class ShowcaseController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView getView() {
        return getBeanForm(new CamposEntrada());
    }

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ModelAndView postView(@ModelAttribute CamposEntrada camposEntrada) {
        return getBeanForm(camposEntrada);
    }

    @RequestMapping(value = "/view/{model}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView getViewComUrl(@PathVariable String model, @ModelAttribute CamposEntrada camposEntrada) {
        camposEntrada.setTexto("Nome:" + model);
        return getBeanForm(camposEntrada);
    }

    @RequestMapping(value = "/autocompleteufs", method = RequestMethod.GET)
    @ResponseBody
    public List<LabelValueModel> getUFsPorNome(@RequestParam("query") String query) {
        List<LabelValueModel> ufs = new LinkedList<LabelValueModel>();
        for (UnidadeFederativa uf : UnidadeFederativa.values()) {
            if (uf.getDescricao().toLowerCase().contains(String.valueOf(query).toLowerCase())) {
                ufs.add(new LabelValueModel(uf.getDescricao(), uf.name()));
            }
        }
        return ufs;
    }

    private ModelAndView getBeanForm(CamposEntrada camposEntrada) {
        return new ModelAndView("view", "camposEntrada", camposEntrada)
            .addObject("ufs", UnidadeFederativa.values());
    }

}
