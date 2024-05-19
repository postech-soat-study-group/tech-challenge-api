package postech.soat.tech.challenge.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import postech.soat.tech.challenge.api.response.ApiResponse;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> sampleEndpoint() {
        return new ApiResponse<>("Hello, World!");
    }
}
