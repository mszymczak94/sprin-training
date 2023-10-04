package pl.training.shop.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.training.shop.commons.data.Page;
import pl.training.shop.commons.web.ExceptionDto;
import pl.training.shop.commons.web.LocationUri;
import pl.training.shop.commons.web.ResultPageDto;
import pl.training.shop.payments.domain.PaymentNotFoundException;
import pl.training.shop.payments.ports.PaymentService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static pl.training.shop.payments.domain.PaymentStatus.STARTED;

// @ResponseBody
// @Controller
@RequestMapping("api/payments")
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentRestMapper mapper;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> process(@RequestBody PaymentRequestDto paymentRequestDto) {
        var paymentRequest = mapper.toDomain(paymentRequestDto);
        var payment = paymentService.process(paymentRequest);
        var paymentDto = mapper.toDto(payment);
        var locationUri = LocationUri.fromRequest(paymentDto.getId());
        return ResponseEntity.created(locationUri)
                .body(paymentDto);
    }

    @GetMapping("{id:\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}}")
    public ResponseEntity<PaymentDto> getById(@PathVariable String id) {
        var payment = paymentService.getById(id);
        var paymentDto = mapper.toDto(payment);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("started")
    public ResponseEntity<ResultPageDto<PaymentDto>> getStartedPayments(
            @RequestParam(required = false, defaultValue = "0") int pageNumer,
            @RequestParam(required = false, defaultValue = "5") int pageSize) {
        var resultPage = paymentService.getByStatus(STARTED, new Page(pageNumer, pageSize));
        var resultPageDto = mapper.toDto(resultPage);
        return ResponseEntity.ok(resultPageDto);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ExceptionDto> onPaymentNotFoundException(PaymentNotFoundException paymentNotFoundException) {
        return ResponseEntity.status(NOT_FOUND)
                .body(new ExceptionDto("Payment not found"));
    }

}
