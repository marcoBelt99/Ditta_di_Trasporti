package com.beltra.sistema1.service;

import com.beltra.sistema1.domain.LineeEntity;
import com.beltra.sistema1.repository.LineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LineeService {

    List<LineeEntity> getListaLinee();
}
