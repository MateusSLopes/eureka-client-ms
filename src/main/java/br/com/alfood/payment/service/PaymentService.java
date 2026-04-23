package br.com.alfood.payment.service;

import br.com.alfood.payment.dto.PaymentDTO;
import br.com.alfood.payment.model.Payment;
import br.com.alfood.payment.model.Status;
import br.com.alfood.payment.repository.PaymentRepository;
import jdk.jshell.Snippet;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private final PaymentRepository repository;

    private final ModelMapper modelMapper;

    public PaymentService(PaymentRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Page<PaymentDTO> getAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(p -> modelMapper.map(p, PaymentDTO.class));
    }

    public PaymentDTO getById(Long id) {
        var payment = repository
                .findById(id)
                .orElse(null);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public PaymentDTO create(PaymentDTO dto) {
        var payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);

        var object = repository.save(payment);
        return modelMapper.map(object, PaymentDTO.class);
    }

    public PaymentDTO update(Long id, PaymentDTO dto) {
        var payment = modelMapper.map(dto, Payment.class);
        payment.setId(id);

        payment = repository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
