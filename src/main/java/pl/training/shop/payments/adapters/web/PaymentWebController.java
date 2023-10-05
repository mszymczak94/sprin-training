package pl.training.shop.payments.adapters.web;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.training.shop.payments.ports.PaymentService;

@RequestMapping("payments/process")
@Controller
@RequiredArgsConstructor
public class PaymentWebController {

    private final PaymentService paymentService;
    private final PaymentWebMapper mapper;

    @GetMapping
    public String showPaymentForm(Model model) {
        var paymentRequestViewModel = new PaymentRequestViewModel();
        paymentRequestViewModel.setValue("100 PLN");
        model.addAttribute("paymentRequest", paymentRequestViewModel);
        return "payments/payment-form";
    }

    // @Secured("ROLE_MANAGER")
    // @RolesAllowed("ROLE_MANAGER")
    // @PreAuthorize("#paymentRequestViewModel.value > 10")
    // @PostAuthorize("returnObject.contains('ayment-summar')")
    @PostMapping
    public String process(@Valid @ModelAttribute("paymentRequest") PaymentRequestViewModel paymentRequestViewModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "payments/payment-form";
        }
        var paymentRequestDomain = mapper.toDomain(paymentRequestViewModel);
        var paymentDomain = paymentService.process(paymentRequestDomain);
        redirectAttributes.addFlashAttribute("payment", mapper.toViewModel(paymentDomain));
        return "redirect:/payments/payment-summary";
    }

}
