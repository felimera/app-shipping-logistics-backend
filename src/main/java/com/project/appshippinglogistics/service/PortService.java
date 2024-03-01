package com.project.appshippinglogistics.service;

import com.project.appshippinglogistics.model.Port;

import java.util.List;

public interface PortService {
    List<Port> getAll();

    Port getById(Integer id);

    Port save(Port port);

    List<Port> getPortQuery(String value);
}
