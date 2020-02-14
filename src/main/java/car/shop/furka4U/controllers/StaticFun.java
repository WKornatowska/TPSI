package car.shop.furka4U.controllers;

import org.springframework.ui.Model;

class StaticFun {
    public static String generate(Model model, String page) {
        return generate(model, page, "");
    }

    public static String generate(Model model, String page, String title) {
        System.out.println(page);
        model.addAttribute("page", page);
        model.addAttribute("title", title);
        return "layout";
    }

    public static int roundUp(int num, int divisor) {
        if (num == 0)
            return 1;
        return (num + divisor - 1) / divisor;
    }
}