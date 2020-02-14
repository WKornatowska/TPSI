package car.shop.furka4U.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import car.shop.furka4U.myObj.BodyStyle;
import car.shop.furka4U.myObj.CarManufacturer;
import car.shop.furka4U.myObj.CarModel;
import car.shop.furka4U.myObj.FuelType;
import car.shop.furka4U.myObj.Offer;
import car.shop.furka4U.myObj.OfferFilter;
import car.shop.furka4U.services.OffersService;

@Controller
public class HomeController {
    @Autowired
    private OffersService offersService;
    Integer offersPerPage = 5;

    @RequestMapping("/")
    public String home(Model model) {

        List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
        List<CarModel> carModels = offersService.getCarModels();

        List<Offer> offers = offersService.getOffers();

        Offer of = new Offer("testing");
        of.UpdateDate();
        offersService.createOffer(of);

        return "redirect:/offerslist/";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);

        return StaticFun.generate(model, "offerDetails", "Szczegóły furki");
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer) {
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("action", "/newoffer");
        return StaticFun.generate(model, "offerForm", "Dodaj furkę");
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding) {

        if (binding.hasErrors()) {
            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();
            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            model.addAttribute("action", "/newoffer");

            return StaticFun.generate(model, "offerForm", "Dodaj furkę");
        }
        offer.UpdateDate();
        offer = offersService.createOffer(offer);
        return "redirect:/offer/" + offer.getId();
    }

    @GetMapping("/offerslist")
    public String offerList(Model model) {
        return offerList(model, 1);
    }

    @GetMapping("/offerslist/{page}")
    public String offerList(Model model, @PathVariable("page") Integer page) {
        List<Offer> offers = offersService.getOffers(page - 1, offersPerPage);
        model.addAttribute("page_num", (Integer) page);
        model.addAttribute("pages", StaticFun.roundUp(offersService.countOffers(), offersPerPage));
        model.addAttribute("offers", offers);

        return StaticFun.generate(model, "offersList", "Lista furek");
    }

    @GetMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.deleteOffer(id);

        model.addAttribute("offer", offer);
        return StaticFun.generate(model, "deleteOffer", "Skasuj furkę");
    }

    @GetMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = offersService.getOffer(id);
        model.addAttribute("offer", offer);
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("action", "/editoffer/" + id);
        return StaticFun.generate(model, "offerForm", "Edytuj furkę");
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer,
            BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = offersService.getCarModels();
            List<BodyStyle> bodyStyles = offersService.getBodyStyles();
            List<FuelType> fuelTypes = offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return StaticFun.generate(model, "offerForm", "Edytuj furkę");
        }
        offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }

}
