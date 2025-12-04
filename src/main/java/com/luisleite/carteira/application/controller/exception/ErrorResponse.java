package com.luisleite.carteira.application.controller.exception;

public record ErrorResponse(String msg, int status, String code) {
}
