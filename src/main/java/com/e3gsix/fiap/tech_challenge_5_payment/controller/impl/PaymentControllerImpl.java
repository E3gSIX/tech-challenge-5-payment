package com.e3gsix.fiap.tech_challenge_5_payment.controller.impl;

import com.e3gsix.fiap.tech_challenge_5_payment.controller.PaymentController;
import com.e3gsix.fiap.tech_challenge_5_payment.model.dto.request.PaymentRequest;
import com.e3gsix.fiap.tech_challenge_5_payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.e3gsix.fiap.tech_challenge_5_payment.controller.impl.PaymentControllerImpl.URL_PAYMENT;

@Tag(name = "Pagamento", description = "Efetivação de pagamento.")
@RestController
@RequestMapping(URL_PAYMENT)
public class PaymentControllerImpl implements PaymentController {

    public static final String URL_PAYMENT = "/payments";

    private final PaymentService paymentService;

    public PaymentControllerImpl(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(
            summary = "Executa o pagamento",
            description = "Endpoint voltado pagamento de Carrinhos de Compras que estejam integros para conclusão..",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pagamento realizado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Inconsistência nos dados recebidos."),
                    @ApiResponse(responseCode = "401", description = "Token não foi informado ou está inválido."),
                    @ApiResponse(responseCode = "403", description = "Requisitante não tem permissões de administrador."),
                    @ApiResponse(responseCode = "404", description = "Recurso com ID informado não foi encontrado.")
            }
    )
    @Override
    @PostMapping
    public ResponseEntity execute(
            @RequestHeader("Authorization") String authorization,
            @RequestBody PaymentRequest request
    ) {
        this.paymentService.execute(authorization, request);

        return ResponseEntity.noContent().build();
    }
}
