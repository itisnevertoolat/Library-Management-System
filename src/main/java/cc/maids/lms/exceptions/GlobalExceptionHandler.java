package cc.maids.lms.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity handleResource(final ResourcesNotFoundException resourcesNotFoundException){

        return new ResponseEntity<>(resourcesNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(JwtException.class)
    public ProblemDetail handleJwtException(JwtException ex){
        ProblemDetail errorDetail = null;
        if(ex instanceof SignatureException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Jwt Signature not valid!");
        }
        if(ex instanceof ExpiredJwtException){
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Jwt token already expired!");
        }
        return errorDetail;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredientialsException(BadCredentialsException ex){
        ProblemDetail errorDetail = null;
        errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "Invalid email or password.");
        return errorDetail;
    }
}
