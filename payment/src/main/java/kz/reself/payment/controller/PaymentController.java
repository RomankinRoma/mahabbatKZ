package kz.reself.payment.controller;

import kz.reself.dbstruct.model.Payment;
import kz.reself.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @RequestMapping(value = "/v1/create", method = RequestMethod.POST)
    public ResponseEntity<?> createPayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.createPayment(payment));
    }

    @RequestMapping(value = "/v1/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.update(payment));
    }

    @RequestMapping(value = "/v1/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> createPayment(@PathVariable Long id) {
        paymentService.deletePaymentInfo(id);
        return ResponseEntity.ok("DELETED");
    }

    @RequestMapping(value = "/v1/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllIterable() {
        return ResponseEntity.ok(paymentService.getAllPaymentsIterable());
    }

    @RequestMapping(value = "/v1/get/all/pageable", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPageable(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(paymentService.getAllPaymentsPageable(params));
    }

    @RequestMapping(value = "/v1/get/all/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserPaymentsIterable(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getAllUserPaymentsIterable(userId));
    }

    @RequestMapping(value = "/v1/get/all/{userId}/pageable", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserPaymentsPageable(@RequestParam Map<String, String> params, @PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getAllUserPaymentsPageable(params, userId));
    }

}
