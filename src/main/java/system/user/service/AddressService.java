package system.user.service;

import system.user.model.AddressModel;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressService {

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    public AddressModel getAddressByZipCode(String zipcode) {
        String url = VIA_CEP_URL + zipcode + "/json/";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, AddressModel.class);
    }
}
