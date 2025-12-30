package com.example.couponapi.api.controller;

import com.example.couponapi.application.command.CreateCouponCommand;
import com.example.couponapi.api.dto.request.CreateCouponRequest;
import com.example.couponapi.api.dto.response.CouponResponse;
import com.example.couponapi.api.mapper.CouponMapper;
import com.example.couponapi.application.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService service;

    public CouponController(CouponService service) {
        this.service = service;
    }

    @Operation(summary = "Criar um cupom")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cupom criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<CouponResponse> create(@RequestBody CreateCouponRequest request) {
        CreateCouponCommand command = new CreateCouponCommand(
                request.code(),
                request.description(),
                request.discountValue(),
                request.expirationDate(),
                request.published()
        );

        var coupon = service.create(command);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CouponMapper.toResponse(coupon));
    }

    @Operation(summary = "Buscar cupom por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cupom encontrado"),
            @ApiResponse(responseCode = "404", description = "Cupom não encontrado ou deletado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CouponResponse> get(@PathVariable UUID id) {
        var coupon = service.getById(id);
        return ResponseEntity.ok(
                CouponMapper.toResponse(coupon)
        );
    }

    @Operation(summary = "Deletar cupom por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cupom deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cupom não encontrado"),
            @ApiResponse(responseCode = "409", description = "Cupom já deletado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
