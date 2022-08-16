package dinhquocnhat.service;

import dinhquocnhat.model.Interiors;

import java.util.List;

public interface IInteriorsService {

//    List<Techs> getTechs();

   List<Interiors> getInteriors();

    void add(Interiors newInteriors);

    void updateName(Interiors newInteriors);

    void updatePrice(Interiors newInteriors);

    void updateQuantity(Interiors newInteriors);

    void updateDescription(Interiors newInteriors);

    void remove(Interiors newInteriors);

    boolean existById(long id);

    Interiors getInteriorsById(long id);


    void updateNewQuantity(long id, int quantity);

    boolean isExistByName(String name);
}
