package com.builders.clientshandler.service.inputtreatment;

import com.builders.clientshandler.model.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreatmentFactory {

    private List<Treatment> treatmentList;

    @Autowired
    public TreatmentFactory(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public void execute(Client client) {
        this.treatmentList.stream()
                .filter(t -> t.accept(client))
                .forEach(t -> t.execute(client));
    }
}
