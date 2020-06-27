package com.fpyrypt.whereisatm.service;

import com.fpyrypt.whereisatm.model.atm.Coordinates;
import com.fpyrypt.whereisatm.model.atm.PostAddress;
import com.fpyrypt.whereisatm.model.response.AtmResponse;
import com.fpyrypt.whereisatm.model.response.ErrorResponse;
import com.fpyrypt.whereisatm.model.atm.ATMDetails;
import com.fpyrypt.whereisatm.model.atm.JSONResponseBankATMDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class AtmService {

    @Autowired
    private RestTemplate sslRestTemplate;

    public ResponseEntity<?> getAtmDetails(Integer deviceId) {
        ResponseEntity<JSONResponseBankATMDetails> response = doRequest();
        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.hasBody()) {
                JSONResponseBankATMDetails atmDetails = response.getBody();
                Optional<ATMDetails> optionalATMDetails = atmDetails.getData().getAtms().stream()
                        .filter(a -> a.getDeviceId().compareTo(deviceId) == 0)
                        .findFirst();

                if (optionalATMDetails.isPresent()) {
                    ATMDetails details = optionalATMDetails.get();
                    return ResponseEntity.ok(buildResponse(details));
                }
            }
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("atm not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    public ResponseEntity<?> getNearestAtm(String latitude, String longitude, Boolean payments) {
        ResponseEntity<JSONResponseBankATMDetails> response = doRequest();
        if (response.getStatusCode().is2xxSuccessful()) {
            if (response.hasBody()) {
                JSONResponseBankATMDetails atmDetails = response.getBody();
                List<ATMDetails> atmDetailsList = atmDetails.getData().getAtms().stream()
                        .filter(a -> nonNull(a.getCoordinates()) &&
                                nonNull(a.getCoordinates().getLongitude()) &&
                                nonNull(a.getCoordinates().getLatitude()))
                        .collect(Collectors.toList());

                if (nonNull(payments) && payments) {
                    atmDetailsList = atmDetailsList.stream()
                            .filter(d -> d.getServices().getPayments().equals("Y"))
                            .collect(Collectors.toList());
                }

                Coordinates coordinates = new Coordinates();
                coordinates.setLatitude(latitude);
                coordinates.setLongitude(longitude);

                ATMDetails nearestAtm = null;
                Double nearestDistance = Double.MAX_VALUE;

                for (ATMDetails atmDetail : atmDetailsList) {
                    if (isNull(nearestAtm)) {
                        nearestAtm = atmDetail;
                        nearestDistance = calculateDistance(coordinates, atmDetail.getCoordinates());
                    } else {
                        Coordinates checkingCoordinates = atmDetail.getCoordinates();
                        if (nonNull(checkingCoordinates) &&
                                nonNull(atmDetail.getCoordinates().getLatitude()) &&
                                nonNull(atmDetail.getCoordinates().getLongitude())) {
                            Double checkingDistance = calculateDistance(coordinates, checkingCoordinates);
                            if (nearestDistance.compareTo(checkingDistance) > 0) {
                                nearestDistance = checkingDistance;
                                nearestAtm = atmDetail;
                            }
                        }
                    }
                }
                return ResponseEntity.ok(buildResponse(nearestAtm));
            }
        }
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus("atm not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private ResponseEntity<JSONResponseBankATMDetails> doRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-ibm-client-id", "1ba8be65-3d27-4f68-8633-62ddfebbc60e");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return sslRestTemplate.exchange("https://apiws.alfabank.ru/alfabank/alfadevportal/atm-service/atms",
                HttpMethod.GET,
                entity,
                JSONResponseBankATMDetails.class);

    }

    private Double calculateDistance(Coordinates c1, Coordinates c2) {
        return Math.sqrt(
                Math.pow(Double.parseDouble(c1.getLongitude()) - Double.parseDouble(c2.getLongitude()), 2) +
                        Math.pow(Double.parseDouble(c1.getLatitude()) - Double.parseDouble(c2.getLatitude()), 2));
    }

    private AtmResponse buildResponse(ATMDetails details) {
        AtmResponse atmResponse = new AtmResponse();
        atmResponse.setCity(Optional.of(details.getAddress()).map(PostAddress::getCity).orElse(null));
        atmResponse.setDeviceId(details.getDeviceId());
        atmResponse.setLatitude(details.getCoordinates().getLatitude());
        atmResponse.setLongitude(details.getCoordinates().getLongitude());
        atmResponse.setLocation(details.getAddress().getLocation());
        atmResponse.setPayments(details.getServices().getPayments().equals("Y"));
        return atmResponse;
    }

}
