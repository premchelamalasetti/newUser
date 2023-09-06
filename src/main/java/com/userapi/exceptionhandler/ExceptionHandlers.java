package com.userapi.exceptionhandler;

import com.userapi.exceptions.EmpytDataFoundException;
import com.userapi.exceptions.NoDataUpdatedException;
import com.userapi.exceptions.NoSufficientDataException;
import com.userapi.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {

        return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmpytDataFoundException.class)
    public ResponseEntity<String> handleNoUsers(EmpytDataFoundException emptyUserException) {
        return new ResponseEntity<String>("No Users are there in the DataBase", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSufficientDataException.class)
    public ResponseEntity<String> handleNoSufficientDataException(NoSufficientDataException noSufficientDataException) {

        return new ResponseEntity<String>("Please provide the data to save in DataBase", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataUpdatedException.class)
    public ResponseEntity<String> handleNodataUpdatedException(NoDataUpdatedException noDataUpdatedException) {

        return new ResponseEntity<String>("No Data is updated to save in DataBase", HttpStatus.BAD_REQUEST);
    }
}
