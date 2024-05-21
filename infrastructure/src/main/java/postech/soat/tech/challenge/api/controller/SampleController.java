package postech.soat.tech.challenge.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.entity.Cliente;
import postech.soat.tech.challenge.repository.ClienteRepository;

@RestController
@RequestMapping("/api/sample")
@RequiredArgsConstructor
public class SampleController {


    private final ClienteRepository clienteRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> sampleEndpoint() {
        return new ApiResponse<>("Hello, World!");
    }

    @PostMapping("/cliente")
    public ApiResponse<Cliente> inserirCliente(@RequestBody Cliente cliente) {
        return new ApiResponse<>(clienteRepository.save(cliente));
    }
}
