package com.builders.clientshandler.service.inputtreatment;

import com.builders.clientshandler.model.entities.Client;

public interface Treatment {

    Boolean accept(Client client);

    void execute(Client client);
}
