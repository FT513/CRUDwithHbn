package com.sasha.CRUDwithHbn.service;


import com.sasha.CRUDwithHbn.model.Label;
import com.sasha.CRUDwithHbn.repository.LabelRepository;
import com.sasha.CRUDwithHbn.repository.hbn.HBNLabelRepositoryImpl;

import java.util.List;

public class LabelService {

    private LabelRepository labelRepository;

    public LabelService() {
        this.labelRepository = new HBNLabelRepositoryImpl();
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label update(Label label) {
        return labelRepository.update(label);
    }

    public Label getById(Integer id) {
        return labelRepository.getById(id);
    }

    public void deleteById(Integer id) {
        labelRepository.deleteById(id);
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public Label saveNewLabel(Label label) {
        return labelRepository.save(label);
    }

}
