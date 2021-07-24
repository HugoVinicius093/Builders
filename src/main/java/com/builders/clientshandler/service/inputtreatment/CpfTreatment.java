package com.builders.clientshandler.service.inputtreatment;

import com.builders.clientshandler.model.entities.Client;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CpfTreatment implements Treatment {

    @Override
    public Boolean accept(Client client) {
        return !StringUtils.isEmpty(client.getCpf());
    }

    @Override
    public void execute(Client client) {
        client.setCpf(client.getCpf().replaceAll("[-._]", StringUtils.EMPTY));
    }
}
