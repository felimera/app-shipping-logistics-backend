package com.project.appshippinglogistics.service.implementation;

import com.project.appshippinglogistics.exception.BusinessException;
import com.project.appshippinglogistics.model.Port;
import com.project.appshippinglogistics.repository.PortRepository;
import com.project.appshippinglogistics.service.PortService;
import com.project.appshippinglogistics.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortServiceImpl implements PortService {
    private PortRepository portRepository;

    @Autowired
    public PortServiceImpl(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @Override
    public List<Port> getAll() {
        return portRepository.findAll();
    }

    @Override
    public Port getById(Integer id) {
        return portRepository.findById(id).orElseThrow(() -> new BusinessException("401", HttpStatus.NOT_FOUND, Constants.MESSAGE_NOT_FOUND));
    }

    @Override
    public Port save(Port port) {
        return portRepository.save(port);
    }
}
