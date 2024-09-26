package br.edu.pweb2.incruise.controllers;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/* @EnableWebMvc
 */
@ControllerAdvice
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    // Tratar exceções de página não encontrada
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePageNotFound(final NoHandlerFoundException ex, final Model model, HttpServletRequest request) {
        logger.error("Página não encontrada", ex);
        String errorMessage = String.format("Você tentou acessar a página: %s.", request.getRequestURI());
        model.addAttribute("errorMessage", errorMessage);
        return "system/not-found";  // Remova a barra inicial
    }

    // Tratar exceções genéricas
   // Tratar exceções genéricas
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    public String handleGenericException(final Throwable throwable, final Model model, HttpServletRequest request, HttpServletResponse response) {
        logger.error("Erro durante execução da aplicação", throwable);

        // Captura o código de status (caso queira definir um, senão pode ser padrão)
        int statusCode = response.getStatus(); 

        if (statusCode == 0) {
            statusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR; // 500
            response.setStatus(statusCode); 
        }

        model.addAttribute("statusCode", statusCode);

        // Obtenha a mensagem da exceção
        String errorMessage = throwable.getMessage();
        model.addAttribute("errorMessage", errorMessage != null ? errorMessage : "Erro interno do servidor.");

        // Captura a stack trace, se disponível
        String stackTrace = throwable.getCause() != null ? throwable.getCause().toString()
                : "Stack trace não disponível.";
        model.addAttribute("stackTrace", stackTrace);

        return "system/error"; 
    }
}
