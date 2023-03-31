package com.codegym.springboot_modul_6.Service.FE_SF_Service;

import com.codegym.springboot_modul_6.Model.FE_SF_Model.Entity.Image;
import com.codegym.springboot_modul_6.Repository.FE_SF_Repository.IImageRepositorySF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepositorySF iImageRepositorySF;

    @Override
    public Iterable<Image> findAll() {
        return null;
    }

    @Override
    public Optional<Image> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Image image) {

    }

    @Override
    public void remove(Long id) {
    }

//    @Override
//    public List<Image> findAllByProductSF(Long productId) {
//        return iImageRepositorySF.findByProductId(productId);
//    }
}
