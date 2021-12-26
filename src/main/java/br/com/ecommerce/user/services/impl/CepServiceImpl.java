package br.com.ecommerce.user.services.impl;

import br.com.ecommerce.user.models.Address;
import br.com.ecommerce.user.services.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CepServiceImpl {

    @Value("${uri.via.cep}")
    private String uri;

    @Autowired
    private RestTemplate restTemplate;

    private final CepService service;

    public Address getAddressWithFeignByCep(String cep) {
        return service.findAddressByCep(cep);
    }

    public Address getAddressWithWebClientByCep(String cep) {
        return WebClient.create().get()
                .uri(uri, cep)
                .retrieve()
                .bodyToMono(Address.class)
                .block();
    }

    public Address getAddressWithRestTemplateByCep(String cep) {
        return restTemplate.getForObject(uri, Address.class, Map.of("cep", cep));
    }
}