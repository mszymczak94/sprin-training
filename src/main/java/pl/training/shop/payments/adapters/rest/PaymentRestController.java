package pl.training.shop.payments.adapters.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody
// @Controller
@RequestMapping("api/payments")
@RestController
public class PaymentRestController {

    @GetMapping
    public String test() {
        return "ok";
    }

}
